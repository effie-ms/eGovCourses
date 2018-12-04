package com.egov.elearning.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.egov.elearning.domain.enumeration.Level;

@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_price")
    private Long coursePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_level")
    private Level courseLevel;

    @OneToMany(mappedBy = "course")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Lesson> lessons = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Course courseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
        return this;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public Course courseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        return this;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Long getCoursePrice() {
        return coursePrice;
    }

    public Course coursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
        return this;
    }

    public void setCoursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Level getCourseLevel() {
        return courseLevel;
    }

    public Course courseLevel(Level courseLevel) {
        this.courseLevel = courseLevel;
        return this;
    }

    public void setCourseLevel(Level courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public Course lessons(Set<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }

    public Course addLessons(Lesson lesson) {
        this.lessons.add(lesson);
        lesson.setCourse(this);
        return this;
    }

    public Course removeLessons(Lesson lesson) {
        this.lessons.remove(lesson);
        lesson.setCourse(null);
        return this;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", courseTitle='" + getCourseTitle() + "'" +
            ", courseDescription='" + getCourseDescription() + "'" +
            ", coursePrice=" + getCoursePrice() +
            ", courseLevel='" + getCourseLevel() + "'" +
            "}";
    }
}
