package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.ConsultantDto;
import com.ssafy.api.dto.QConsultantDto;
import com.ssafy.db.entity.QAreas;
import com.ssafy.db.entity.QStaff;
import com.ssafy.db.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class AdminRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStaff qstaff= QStaff.staff;
    QAreas qareas= QAreas.areas;

    public Optional<Staff> findStaffById(String userId) {


        System.out.println("userId = " + userId);
        Staff staff = jpaQueryFactory.select(qstaff).from(qstaff)
                .where(qstaff.staffId.eq(userId)).fetchOne();



        System.out.println("staff = " + staff);
        if( staff== null) return Optional.empty();
        return Optional.ofNullable(staff);
    }

    public List<ConsultantDto> getConsultantList()
    {

        List<ConsultantDto> result = jpaQueryFactory
                .select(new QConsultantDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName")))
                .from(qstaff)
                .join(qstaff.areas, qareas)
                .orderBy(qstaff.id.desc())
                .fetch();

return result;

    }


    public ConsultantDto getConsultant(int id)
    {

        ConsultantDto result = jpaQueryFactory
                .select(new QConsultantDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName")))
                .from(qstaff)
                .join(qstaff.areas, qareas)
                .where(qstaff.id.eq(id))
                .fetchOne();

        return result;
    }

//
//    public int updateConsultant(ConsultantDto dto)
//    {
//        jpaQueryFactory.update(qstaff)
//                .set(qstaff.deleteYN,dto.getDeleteYN())
//                .set(qstaff.approveYN,dto.getApproveYN())
//                .set(qstaff.areas.id,dto.getAreaId())
//                .set(qstaff.updatedAt,dto.get)
//
//
//
//    }





}
