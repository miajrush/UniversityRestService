package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	@Modifying
	@Query("UPDATE Subject s SET s.name = :name, s.hoursNumber = :hoursNumber " +
	       "WHERE s.id = :id")
	void update(Integer id, String name, Integer hoursNumber);
}
