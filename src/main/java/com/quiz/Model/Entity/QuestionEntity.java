package com.quiz.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class QuestionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

    @OneToMany(mappedBy = "question", targetEntity = AnswerEntity.class, orphanRemoval = true)
    @JsonManagedReference
    @Cascade(value = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AnswerEntity> answers;

    @ManyToMany(targetEntity = TestEntity.class, mappedBy = "questions")
    private List<TestEntity> tests;
}
