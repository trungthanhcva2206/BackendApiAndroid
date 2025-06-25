package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findAllByOrderByCreatedAtAsc();
}
