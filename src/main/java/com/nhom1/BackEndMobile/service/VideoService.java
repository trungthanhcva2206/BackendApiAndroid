package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.VideoRequest;
import com.nhom1.BackEndMobile.dto.response.VideoResponse;
import com.nhom1.BackEndMobile.entity.Lesson;
import com.nhom1.BackEndMobile.entity.Video;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.VideoMapper;
import com.nhom1.BackEndMobile.repository.LessonRepository;
import com.nhom1.BackEndMobile.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoService {

    VideoRepository videoRepository;
    LessonRepository lessonRepository;
    VideoMapper videoMapper;

    public VideoResponse createVideo(VideoRequest request) {
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new AppException(ErrorCode.LESSON_NOT_FOUND));

        Video video = videoMapper.toVideo(request, lesson);
        return videoMapper.toVideoResponse(videoRepository.save(video));
    }

    public List<VideoResponse> getAllVideos() {
        return videoRepository.findAll()
                .stream()
                .map(videoMapper::toVideoResponse)
                .collect(Collectors.toList());
    }

    public VideoResponse getVideo(String id) {
        return videoRepository.findById(id)
                .map(videoMapper::toVideoResponse)
                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));
    }

    public void deleteVideo(String id) {
        videoRepository.deleteById(id);
    }
}

