package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoResponse {
    String id;
    String title;
    String videoUrl;
    String lessonId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
