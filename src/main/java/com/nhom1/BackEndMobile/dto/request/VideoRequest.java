package com.nhom1.BackEndMobile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoRequest {
    String title;
    String videoUrl;
    String lessonId;
}
