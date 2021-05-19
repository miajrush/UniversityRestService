package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.model.TeacherPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * {@link JpaRepository} for handling with {@link Teacher} entity.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	@Modifying
	@Query("UPDATE Teacher t SET t.name = :name, t.phoneNumber = :phoneNumber, t.position = :position " +
	       "WHERE t.id = :id")
	void update(Integer id, String name, String phoneNumber, TeacherPosition position);
}
