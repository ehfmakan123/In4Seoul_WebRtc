package com.ssafy.db.repository;

import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {


    @Transactional
    Optional<WaitingList> deleteByDesk(Desk desk);
}
