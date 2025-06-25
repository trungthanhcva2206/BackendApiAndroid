package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.DocumentRequest;
import com.nhom1.BackEndMobile.dto.response.DocumentResponse;
import com.nhom1.BackEndMobile.entity.Document;
import com.nhom1.BackEndMobile.entity.Lesson;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.DocumentMapper;
import com.nhom1.BackEndMobile.repository.DocumentRepository;
import com.nhom1.BackEndMobile.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DocumentService {

    DocumentRepository documentRepository;
    LessonRepository lessonRepository;
    DocumentMapper documentMapper;

    public DocumentResponse create(DocumentRequest request) {
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new AppException(ErrorCode.LESSON_NOT_FOUND));
        Document document = documentMapper.toEntity(request, lesson);
        return documentMapper.toResponse(documentRepository.save(document));
    }

    public List<DocumentResponse> getAll() {
        return documentRepository.findAll().stream()
                .map(documentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DocumentResponse getById(String id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DOCUMENT_NOT_FOUND));
        return documentMapper.toResponse(document);
    }

    public void delete(String id) {
        documentRepository.deleteById(id);
    }
}
