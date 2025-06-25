package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.DocumentRequest;
import com.nhom1.BackEndMobile.dto.response.DocumentResponse;
import com.nhom1.BackEndMobile.entity.Document;
import com.nhom1.BackEndMobile.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public Document toEntity(DocumentRequest request, Lesson lesson) {
        return Document.builder()
                .title(request.getTitle())
                .fileUrl(request.getFileUrl())
                .lesson(lesson)
                .build();
    }

    public DocumentResponse toResponse(Document document) {
        return DocumentResponse.builder()
                .id(document.getId())
                .title(document.getTitle())
                .fileUrl(document.getFileUrl())
                .lessonId(document.getLesson().getId())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }
}
