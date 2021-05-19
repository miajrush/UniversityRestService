package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.TeacherPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TeacherPositionRepository extends JpaRepository<TeacherPosition, Integer> {
	@Modifying
	@Query("UPDATE TeacherPosition t SET t.name = :name " +
	       "WHERE t.id = :id")
	void update(Integer id, String name);
}
