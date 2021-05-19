package com.github.miajrush.universityrestservice.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object representing a lesson type.
 */
@EqualsAndHashCode(of = "", callSuper = true)
@Entity
@Table(name = "lesson_types")
public class LessonType extends NamedEntity {
}
