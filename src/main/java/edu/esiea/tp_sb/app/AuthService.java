package edu.esiea.tp_sb.app;

import edu.esiea.tp_sb.domain.entity.ThemeEntity;
import edu.esiea.tp_sb.domain.repository.LessonRepository;
import edu.esiea.tp_sb.domain.repository.ThemeRepository;
import edu.esiea.tp_sb.domain.repository.UserRepository;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;
import edu.esiea.tp_sb.dto.lesson.LessonPostDto;
import edu.esiea.tp_sb.dto.mappers.LessonMapper;
import edu.esiea.tp_sb.dto.mappers.ThemeMapper;
import edu.esiea.tp_sb.dto.theme.ThemeDto;
import edu.esiea.tp_sb.dto.theme.ThemePostDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        // 1. Vérif password (Mock pour l'instant)
        if (!"admin".equals(username)) throw new RuntimeException("Bad creds");

        // 2. Création Claims
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(username)
                .claim("roles", List.of("ADMIN")) // Rôle important !
                .build();

        // 3. Signature
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
