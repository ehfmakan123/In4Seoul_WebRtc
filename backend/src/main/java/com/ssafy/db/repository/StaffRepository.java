package com.ssafy.db.repository;

import com.ssafy.db.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findById(int id);

    Optional<Staff> findByStaffId(String StaffId);

}
