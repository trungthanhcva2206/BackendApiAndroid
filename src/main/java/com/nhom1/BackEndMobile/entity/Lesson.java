package com.nhom1.BackEndMobile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    List<Document> documents;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
    Video video;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}

