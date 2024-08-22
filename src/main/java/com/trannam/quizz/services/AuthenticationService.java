package com.trannam.quizz.services;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.trannam.quizz.dto.request.AuthenticationRequest;
import com.trannam.quizz.dto.response.AuthenticationResponse;
import com.trannam.quizz.dto.response.IntrospectResponse;
import com.trannam.quizz.entity.User;
import com.trannam.quizz.exception.AppException;
import com.trannam.quizz.exception.ErrorCode;
import com.trannam.quizz.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.signerKey}")
    private String secketKey;

    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .token(token)
                .build();
    }

    public IntrospectResponse verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(secketKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(verifier);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .val(verified && expityTime.after(new Date()))
                .build();
    }

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("tranvannam.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            JWSSigner jwsSigner = new MACSigner(secketKey.getBytes());
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            log.error("cannot create Token", e);
            throw new RuntimeException(e);
        }

        return signedJWT.serialize();
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
