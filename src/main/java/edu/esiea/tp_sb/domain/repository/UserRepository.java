package edu.esiea.tp_sb.domain.repository;

import edu.esiea.tp_sb.domain.entity.UserEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = {"role"})
    Page<@NonNull UserEntity> findAll(@NonNull Pageable pageable);

    Optional<UserEntity> findByLogin(@NonNull String login);
}
