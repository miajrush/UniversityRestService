package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
	@Modifying
	@Query("UPDATE Progress p SET p.student = :student, p.subject = :subject, p.grade = :grade " +
	       "WHERE p.id = :id")
	void update(Integer id, Student student, Subject subject, Byte grade);
}
