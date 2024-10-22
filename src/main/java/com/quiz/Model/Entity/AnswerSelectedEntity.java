package com.quiz.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answerSelected")
@Getter
@Setter
public class AnswerSelectedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne(targetEntity = AnswerEntity.class)
    @JoinColumn(name = "answerId", referencedColumnName = "id")
    private AnswerEntity answer;

    @ManyToOne(targetEntity = TestDetailEntity.class)
    @JoinColumn(name = "testDetailId", referencedColumnName = "id")
    private TestDetailEntity testDetail;

    @Column(name = "selected")
    private String selected;
}
