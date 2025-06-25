package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentResponse {
    String id;
    String title;
    String fileUrl;
    String lessonId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
