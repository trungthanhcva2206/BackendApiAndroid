package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.VideoRequest;
import com.nhom1.BackEndMobile.dto.response.VideoResponse;
import com.nhom1.BackEndMobile.entity.Lesson;
import com.nhom1.BackEndMobile.entity.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public Video toVideo(VideoRequest request, Lesson lesson) {
        return Video.builder()
                .title(request.getTitle())
                .videoUrl(request.getVideoUrl())
                .lesson(lesson)
                .build();
    }

    public VideoResponse toVideoResponse(Video video) {
        return VideoResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .videoUrl(video.getVideoUrl())
                .lessonId(video.getLesson().getId())
                .createdAt(video.getCreatedAt())
                .updatedAt(video.getUpdatedAt())
                .build();
    }
}
