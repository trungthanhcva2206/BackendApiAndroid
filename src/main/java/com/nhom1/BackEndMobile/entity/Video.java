package com.nhom1.BackEndMobile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String videoUrl;

    @OneToOne
    @JoinColumn(name = "lesson_id", unique = true)
    Lesson lesson;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
