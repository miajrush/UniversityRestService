package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * {@link JpaRepository} for handling with {@link LessonType} entity.
 */
public interface LessonTypeRepository extends JpaRepository<LessonType, Integer> {
	@Modifying
	@Query("UPDATE LessonType lt SET lt.name = :name " +
	       "WHERE lt.id = :id")
	void update(Integer id, String name);
}
