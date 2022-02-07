package com.ssafy.db.repository;

import com.ssafy.db.entity.Posts;
import com.ssafy.db.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {




}
