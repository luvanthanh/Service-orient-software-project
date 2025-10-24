package Myproject.user_service.service;

import Myproject.user_service.dto.reponse.AuthenticationResponse;
import Myproject.user_service.dto.reponse.IntrospectResponse;
import Myproject.user_service.dto.request.AuthenticationRequest;
import Myproject.user_service.dto.request.IntrospectRequest;
import Myproject.user_service.exception.AppException;
import Myproject.user_service.exception.ErrorCode;
import Myproject.user_service.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service

public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.signerKey}")
    private String SINGER_KEY;

//    ckeck xem có đăng nhập đuungs không
    public AuthenticationResponse login(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFIND));


        boolean checkPassword = passwordEncoder.matches(request.getPassword(), user.getUserPassword());


        if(!checkPassword) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        else{
            var token = generateToken(request.getUserName());
            return AuthenticationResponse.builder()
                    .token(token)
                    .checkLogin(checkPassword)
                    .build();

        }
    }

    private String generateToken(String name)
    {
        JWSHeader jwsHeader= new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(name)
                .issuer("Lu Van Thanh hahhahha")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim","cusTom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try
            {
                jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
                return  jwsObject.serialize();
            }
        catch (JOSEException exception)
            {
                log.error("can't generate token",exception);
                throw new RuntimeException(exception);
            }
    }

    public IntrospectResponse introspect( IntrospectRequest request)
           throws JOSEException, ParseException
    {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .checkToken(verified && expiryTime.after(new Date()))
                .build();
    }


}
