package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.VideoRequest;
import com.nhom1.BackEndMobile.dto.response.VideoResponse;
import com.nhom1.BackEndMobile.dto.response.ApiResponse;
import com.nhom1.BackEndMobile.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoController {

    VideoService videoService;

    @PostMapping
    ApiResponse<VideoResponse> createVideo(@RequestBody VideoRequest request) {
        ApiResponse<VideoResponse> response = new ApiResponse<>();
        response.setResult(videoService.createVideo(request));
        return response;
    }

    @GetMapping
    List<VideoResponse> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    VideoResponse getVideo(@PathVariable String id) {
        return videoService.getVideo(id);
    }

    @DeleteMapping("/{id}")
    String deleteVideo(@PathVariable String id) {
        videoService.deleteVideo(id);
        return "Video has been deleted";
    }
    @PostMapping("/upload")
    public ApiResponse<VideoResponse> uploadVideoFile(
            @RequestParam("title") String title,
            @RequestParam("lessonId") String lessonId,
            @RequestParam("video") MultipartFile videoFile) {

        // 📦 Tạo tên file duy nhất
        String originalFilename = videoFile.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalFilename;

        // 📂 Lưu vào thư mục uploads/videos
        Path videoDir = Paths.get("/home/uploads/videos/");
        Path savePath = videoDir.resolve(fileName);
        try {
            Files.createDirectories(videoDir);
            Files.copy(videoFile.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Upload video thất bại", e);
        }

        // 🌐 Tạo URL công khai để xem
        String serverBaseUrl = "http://14.225.207.221:6060/mobile";
        String videoUrl = serverBaseUrl + "/uploads/videos/" + fileName;

        // 📝 Tạo video trong DB
        VideoRequest request = new VideoRequest(title, videoUrl, lessonId);
        VideoResponse response = videoService.createVideo(request);

        return new ApiResponse<>(response);
    }

}
