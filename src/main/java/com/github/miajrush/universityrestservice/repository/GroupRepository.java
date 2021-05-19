package com.github.miajrush.universityrestservice.repository;

import com.github.miajrush.universityrestservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * {@link JpaRepository} for handling with {@link Group} entity.
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
	@Modifying
	@Query("UPDATE Group g SET g.name = :name, g.studentsNumber = :studentsNumber " +
	       "WHERE g.id = :id")
	void update(Integer id, String name, Integer studentsNumber);
}
