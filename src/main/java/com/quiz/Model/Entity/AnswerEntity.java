package com.quiz.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class AnswerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;
    @Column(name = "isTrue")
    private Boolean isTrue;

    @ManyToOne(targetEntity = QuestionEntity.class)
    @JoinColumn(name = "questionId", referencedColumnName = "id")
    @JsonBackReference
    private QuestionEntity question;

    @OneToMany(mappedBy = "answer")
    private List<AnswerSelectedEntity> answerSelected;
}
