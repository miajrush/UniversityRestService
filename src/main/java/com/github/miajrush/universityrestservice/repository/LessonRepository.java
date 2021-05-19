package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.LessonType;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * {@link JpaRepository} for handling with {@link Lesson} entity.
 */
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	@Modifying
	@Query("UPDATE Lesson l SET l.subject = :subject, l.teacher = :teacher, l.lessonType = :lessonType " +
	       "WHERE l.id = :id")
	void update(Integer id, Subject subject, Teacher teacher, LessonType lessonType);
}
