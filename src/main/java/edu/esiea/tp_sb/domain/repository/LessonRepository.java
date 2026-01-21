package edu.esiea.tp_sb.domain.repository;

import edu.esiea.tp_sb.domain.entity.LessonEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    @EntityGraph(attributePaths = "theme")
    @NonNull
    Page<LessonEntity> findAll(@NonNull Pageable pageable);
}
