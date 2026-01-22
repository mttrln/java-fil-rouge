package edu.esiea.tp_sb.app;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class AuthService {


    private final JwtEncoder jwtEncoder;

    public AuthService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String authenticate(String username, String password) throws AuthenticationException {
        if (!"admin".equals(username))
            throw new AuthenticationException("Username or password invalid.");

        Instant now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(1800)) // 30 min
                .claim("roles", List.of("AUTHOR"))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims)).getTokenValue();
    }
}
