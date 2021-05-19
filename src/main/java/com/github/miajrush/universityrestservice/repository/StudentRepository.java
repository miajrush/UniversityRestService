package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * {@link JpaRepository} for handling with {@link Student} entity.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Modifying
	@Query("UPDATE Student s " +
	       "SET s.name = :name, s.course = :course, s.phoneNumber = :phoneNumber, s.email = :email,s.group = :group " +
	       "WHERE s.id = :id")
	void update(Integer id, String name, Integer course, String phoneNumber, String email, Group group);
}
