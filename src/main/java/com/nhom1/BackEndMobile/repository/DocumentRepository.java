package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
}
