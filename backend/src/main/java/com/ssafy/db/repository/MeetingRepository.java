package com.ssafy.db.repository;

import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
