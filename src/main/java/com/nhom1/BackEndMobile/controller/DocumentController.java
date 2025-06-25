package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.DocumentRequest;
import com.nhom1.BackEndMobile.dto.response.DocumentResponse;
import com.nhom1.BackEndMobile.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DocumentController {

    DocumentService documentService;

    @PostMapping("/upload")
    public DocumentResponse uploadDocument(
            @RequestParam("title") String title,
            @RequestParam("lessonId") String lessonId,
            @RequestPart("file") MultipartFile file) {

        // Bước 1: Lưu file
        String fileUrl = saveFileAndGetUrl(file); // 🔧 Viết hàm này ở dưới

        // Bước 2: Gọi lại service cũ để tạo bản ghi
        DocumentRequest request = DocumentRequest.builder()
                .title(title)
                .fileUrl(fileUrl)
                .lessonId(lessonId)
                .build();

        return documentService.create(request);
    }


    @PostMapping
    public DocumentResponse create(@RequestBody DocumentRequest request) {
        return documentService.create(request);
    }

    @GetMapping
    public List<DocumentResponse> getAll() {
        return documentService.getAll();
    }

    @GetMapping("/{id}")
    public DocumentResponse getById(@PathVariable String id) {
        return documentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        documentService.delete(id);
        return "Deleted successfully";
    }

    private String saveFileAndGetUrl(MultipartFile file) {
        try {
            // Tạo thư mục nếu chưa có
            String uploadDir = "uploads/documents/";
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);
            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }

            // Tạo đường dẫn file đích
            String originalFileName = file.getOriginalFilename();
            String fileName = java.util.UUID.randomUUID() + "_" + originalFileName;
            java.nio.file.Path filePath = uploadPath.resolve(fileName);
            java.nio.file.Files.copy(file.getInputStream(), filePath);

            // Trả về URL ảo (bạn cần cấu hình static resource mapping nếu dùng local)
            return "http://14.225.207.221:6060/mobile/uploads/documents/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Không thể lưu file: " + e.getMessage(), e);
        }
    }

}
