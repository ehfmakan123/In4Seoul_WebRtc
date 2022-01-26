package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStaff;
import com.ssafy.db.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class AdminRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStaff qstaff= QStaff.staff;


    public Optional<Staff> findStaffById(String userId) {


        System.out.println("userId = " + userId);
        Staff staff = jpaQueryFactory.select(qstaff).from(qstaff)
                .where(qstaff.staffId.eq(userId)).fetchOne();



        System.out.println("staff = " + staff);
        if( staff== null) return Optional.empty();
        return Optional.ofNullable(staff);
    }
}
