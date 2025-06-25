package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
}
