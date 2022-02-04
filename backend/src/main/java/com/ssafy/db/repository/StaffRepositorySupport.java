package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.QStaffDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.db.entity.QAreas;
import com.ssafy.db.entity.QDesks;
import com.ssafy.db.entity.QPosts;
import com.ssafy.db.entity.QStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStaff qstaff= QStaff.staff;
    QAreas qareas= QAreas.areas;
    QDesks desk=QDesks.desks;
    QPosts qpost=QPosts.posts;




    //내 정보 조회
    public StaffDto getStaffDto(String id)
    {

        StaffDto result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName")))
                .from(qstaff)
                .leftJoin(qstaff.areas, qareas)
                .where(qstaff.staffId.eq(id))
                .fetchOne();

        return result;
    }


}
