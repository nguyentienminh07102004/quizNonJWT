package com.quiz.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "numsOfRating")
    private Long numsOfRating;

    @OneToMany(mappedBy = "category", targetEntity = QuestionEntity.class)
    private List<QuestionEntity> questions;
}
