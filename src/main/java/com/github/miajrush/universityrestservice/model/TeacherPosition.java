package com.github.miajrush.universityrestservice.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object representing a teacher position.
 */
@EqualsAndHashCode(of = "", callSuper = true)
@Entity
@Table(name = "teacher_positions")
public class TeacherPosition extends NamedEntity {
}
