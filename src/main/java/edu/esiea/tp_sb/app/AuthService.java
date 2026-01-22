package edu.esiea.tp_sb.app;

import edu.esiea.tp_sb.domain.entity.UserEntity;
import edu.esiea.tp_sb.domain.repository.LessonRepository;
import edu.esiea.tp_sb.domain.repository.UserRepository;
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

    private final UserRepository userRepository;

    private final JwtEncoder jwtEncoder;

    public AuthService(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    public String authenticate(String username, String password) throws AuthenticationException {
        UserEntity user = userRepository.findByLogin(username).orElseThrow(() -> new AuthenticationException("Username or password invalid."));

        Instant now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(1800)) // 30 min
                .claim("roles", List.of(user.getRole()))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims)).getTokenValue();
    }
}
