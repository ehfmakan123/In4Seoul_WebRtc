package com.ssafy.db.repository;

import com.ssafy.db.entity.Posts;
import com.ssafy.db.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {




}
