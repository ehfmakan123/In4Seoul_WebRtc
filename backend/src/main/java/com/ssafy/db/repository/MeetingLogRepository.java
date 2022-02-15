package com.ssafy.db.repository;


import com.ssafy.db.entity.MeetingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingLogRepository extends JpaRepository<MeetingLog, Long> {

    Optional<MeetingLog> findById(int id);
}
