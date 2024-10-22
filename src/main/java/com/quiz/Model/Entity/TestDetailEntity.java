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
@Table(name = "testDetails")
@Getter
@Setter
public class TestDetailEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "testId")
    private String testId;
    @Column(name = "score")
    private Double score;

    @OneToMany(mappedBy = "testDetail", targetEntity = AnswerSelectedEntity.class)
    private List<AnswerSelectedEntity> answerSelected;
}
