package edu.esiea.catalog_service.domain.repository;

import edu.esiea.catalog_service.domain.entity.ThemeEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
    @EntityGraph(attributePaths = "lessons")
    @NonNull
    Page<ThemeEntity> findAll(@NonNull Pageable pageable);
}
