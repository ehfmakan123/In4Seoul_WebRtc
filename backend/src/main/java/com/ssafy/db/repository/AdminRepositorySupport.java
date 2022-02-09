package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.*;
import com.ssafy.db.entity.*;
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
    QDesks desk=QDesks.desks;
    QPosts qpost=QPosts.posts;


    //admin 로그인 시 정보 조회
    public Optional<Staff> findStaffById(String userId) {


        System.out.println("userId = " + userId);
        Staff staff = jpaQueryFactory.select(qstaff).from(qstaff)
                .where(qstaff.staffId.eq(userId)).fetchOne();



        System.out.println("staff = " + staff);
        if( staff== null) return Optional.empty();
        return Optional.ofNullable(staff);
    }


    // 상담사 목록 가져오기
    public List<StaffDto> getConsultantList()
    {

        List<StaffDto> result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"),qstaff.createdAt, qstaff.updatedAt))
                .from(qstaff)
                .join(qstaff.areas, qareas)
                .where(qstaff.deleteYN.eq("N"))   // 삭제 상태인 상담원은 목록에 보이지 않는다
                .orderBy(qstaff.id.desc())
                .fetch();

return result;

    }



    //상담사 정보
    public StaffDto getConsultant(int id)
    {

        StaffDto result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"),qstaff.createdAt, qstaff.updatedAt))
                .from(qstaff)
                .join(qstaff.areas, qareas)
                .where(qstaff.id.eq(id))
                .fetchOne();

        return result;
    }




// 데스크 정보
    public DeskDto getDesk(int id)
    {


        DeskDto result = jpaQueryFactory
                .select(new QDeskDto(desk.id, desk.deskId, desk.korName, desk.engName, desk.password, desk.latitude, desk.altitude, qareas.id.as("areaId"), qareas.korName.as("areaName"),
                        desk.createdAt, desk.updatedAt, desk.deleteYN))
                .from(desk)
                .join(desk.area, qareas)
                .where(desk.id.eq(id))
                .fetchOne();


        return result;
    }


// 데스크 목록
    public List<DeskDto> getDeskList()
    {


        List<DeskDto> result = jpaQueryFactory
                .select(new QDeskDto(desk.id, desk.deskId, desk.korName, desk.engName, desk.password, desk.latitude, desk.altitude, qareas.id.as("areaId"), qareas.korName.as("areaName"),
                        desk.createdAt, desk.updatedAt, desk.deleteYN))
                .from(desk)
                .join(desk.area, qareas)
                .orderBy(desk.id.desc())
                .fetch();


        return result;

    }





    // 게시글 목록 조회
    public List<PostDto> getPostList()
    {
        List<PostDto> result = jpaQueryFactory
                .select(new QPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt))
                .from(qpost)
                .orderBy(qpost.id.desc())
                .fetch();



        return result;


    }


    //게시글 조회
     public PostDto getPost(long id)
     {

         PostDto result = jpaQueryFactory
                 .select(new QPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt))
                 .from(qpost)
                 .where(qpost.id.eq(id))
                 .fetchOne();

         return result;

     }


        public List<AreaDto> getAreas()
        {
            List<AreaDto> result = jpaQueryFactory
                    .select(new QAreaDto(qareas.id, qareas.korName,qareas.engName))
                    .from(qareas)
                    .fetch();

            return result;
        }





}
