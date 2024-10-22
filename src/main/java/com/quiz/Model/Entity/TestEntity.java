package com.quiz.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
public class TestEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = QuestionEntity.class)
    @JoinTable(name = "question_test",
    joinColumns = @JoinColumn(name = "testId", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "questionId", nullable = false))
    @Cascade(value = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<QuestionEntity> questions;
}
