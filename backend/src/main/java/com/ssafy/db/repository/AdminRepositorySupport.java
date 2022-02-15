package com.ssafy.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.*;
import com.ssafy.common.model.response.ListResult;
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
    QArea qareas= QArea.area;
    QDesk desk=QDesk.desk;
    QPost qpost=QPost.post;


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
    public ListResult<StaffDto> getConsultantList(Integer page)
    {

        QueryResults<StaffDto>  qureyResults = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"), qstaff.createdAt, qstaff.updatedAt))
                .from(qstaff)
                .leftJoin(qstaff.area, qareas)
                .where(qstaff.deleteYN.eq("N"))   // 삭제 상태인 상담원은 목록에 보이지 않는다
                .orderBy(qstaff.id.desc())
                .offset((page - 1) * 10)
                .limit(10)
                .fetchResults();


        long count = qureyResults.getTotal();
        List<StaffDto> results = qureyResults.getResults();

        ListResult<StaffDto> listResult = new ListResult<>(200, "성공", results);

        listResult.setTotalCount(count);

        return listResult;

    }



    //상담사 정보
    public StaffDto getConsultant(int id)
    {

        StaffDto result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"),qstaff.createdAt, qstaff.updatedAt))
                .from(qstaff)
                .leftJoin(qstaff.area, qareas)
                .where(qstaff.id.eq(id))
                .fetchOne();

        return result;
    }




// 데스크 정보
    public DeskDto getDesk(int id)
    {


        DeskDto result = jpaQueryFactory
                .select(new QDeskDto(desk.id, desk.deskId, desk.korName, desk.engName, desk.latitude, desk.altitude, qareas.id.as("areaId"), qareas.korName.as("areaName"),
                        desk.createdAt, desk.updatedAt, desk.deleteYN))
                .from(desk)
                .join(desk.area, qareas)
                .where(desk.id.eq(id))
                .fetchOne();


        return result;
    }


// 데스크 목록
    public ListResult<DeskDto> getDeskList(Integer page)
    {





        QueryResults<DeskDto> queryResults = jpaQueryFactory
                .select(new QDeskDto(desk.id, desk.deskId, desk.korName, desk.engName, desk.latitude, desk.altitude, qareas.id.as("areaId"), qareas.korName.as("areaName"),
                        desk.createdAt, desk.updatedAt, desk.deleteYN))
                .from(desk)
                .join(desk.area, qareas)
                .where(desk.deleteYN.eq("N"))   // 삭제 상태인 데스크는 목록에서 보이지 않는다
                .orderBy(desk.id.desc())
                .offset((page - 1) * 10)
                .limit(10)
                .fetchResults();


        List<DeskDto> results = queryResults.getResults();
        long count = queryResults.getTotal();

        ListResult<DeskDto> result = new ListResult<>(200, "성공", results);

        result.setTotalCount(count);

        return result;

    }





    // 게시글 목록 조회
    public ListResult<AdminPostDto> getPostList(Integer page)
    {
        QueryResults<AdminPostDto> queryResults = jpaQueryFactory
                .select(new QAdminPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt, qareas.korName.as("areaName"),desk.korName.as("deskName") ))
                .from(qpost)
                .join(qpost.desk,desk)
                .join(desk.area,qareas)
                .orderBy(qpost.id.desc())
                .offset((page - 1) * 10)
                .limit(10)
                .fetchResults();


         List<AdminPostDto> results = queryResults.getResults();
        long count = queryResults.getTotal();
        ListResult<AdminPostDto> result = new ListResult<>(200, "성공", results);
        result.setTotalCount(count);

        return result;


    }


    //게시글 조회
     public AdminPostDto getPost(long id)
     {

         AdminPostDto result = jpaQueryFactory
                 .select(new QAdminPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt, qareas.korName.as("areaName"),desk.korName.as("deskName") ))
                 .from(qpost)
                 .join(qpost.desk,desk)
                 .join(desk.area,qareas)
                 .where(qpost.id.eq(id))
                 .fetchOne();

         return result;

     }



     //지역목록 조회
        public List<AreaDto> getAreas()
        {
            List<AreaDto> result = jpaQueryFactory
                    .select(new QAreaDto(qareas.id, qareas.korName,qareas.engName))
                    .from(qareas)
                    .fetch();

            return result;
        }





}
