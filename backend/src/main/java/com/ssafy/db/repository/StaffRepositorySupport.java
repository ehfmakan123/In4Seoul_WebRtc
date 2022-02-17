package com.ssafy.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.MeetingLogDto;
import com.ssafy.api.dto.QMeetingLogDto;
import com.ssafy.api.dto.QStaffDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStaff qstaff= QStaff.staff;
    QArea qareas= QArea.area;
    QDesk qdesk=QDesk.desk;
    QPost qpost=QPost.post;
    QMeetingLog qmeetingLog =QMeetingLog.meetingLog;
    QWaitingList qmeeting= QWaitingList.waitingList;


    //내 정보 조회
    public StaffDto getStaffDto(String id)
    {

        StaffDto result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"),qstaff.createdAt,qstaff.updatedAt))
                .from(qstaff)
                .leftJoin(qstaff.area, qareas)
                .where(qstaff.staffId.eq(id))
                .fetchOne();

        return result;
    }





    // 내 상당 목록 조회   가장 최신 상담목록이 상단에 가게끔

    public ListResult<MeetingLogDto> getMeetingLog(int id, Integer page)
    {

        QueryResults<MeetingLogDto> result = jpaQueryFactory
                .select(new QMeetingLogDto(qmeetingLog.id, qdesk.korName.as("deskName"), qmeetingLog.startedAt, qmeetingLog.endedAt, qmeetingLog.content))
                .from(qmeetingLog)
                .join(qmeetingLog.desk, qdesk)
                .where(qmeetingLog.staff.id.eq(id))
                .orderBy(qmeetingLog.id.desc())
                .offset((page-1) * 10)
                .limit(10)
                .fetchResults();


        long count = result.getTotal();
        List<MeetingLogDto> results = result.getResults();
        ListResult<MeetingLogDto> listResult = new ListResult<>(200, "성공", results);
            listResult.setTotalCount(count);

        return listResult;


    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 화상 상담 관련



    // 알림을 보내줄 상담사들을 선택
    public List<Staff> getStaffList(int areaId)
    {
        List<Staff> result = jpaQueryFactory
                .select(qstaff)
                .from(qstaff)
                .where(qstaff.area.id.eq(areaId).and(qstaff.matchYN.eq("N")).and(qstaff.fcmToken.eq("0"
                ).not()))
                .fetch();

        return result;
    }



    //지역에 해당하는 상담 대기 목록 가져오기
    public long getMeeting(int areaId)
    {

        long count = jpaQueryFactory
                .select(qmeeting)
                .from(qmeeting)
                .where(qmeeting.area.id.eq(areaId))
                .fetchCount();
return count;
    }



    public WaitingList MeetingConnect(int areaId)
    {

        WaitingList result = jpaQueryFactory
                .select(qmeeting)
                .from(qmeeting)
                .where(qmeeting.area.id.eq(areaId))
                .orderBy(qmeeting.id.asc())
                .limit(1)
                .fetchOne();

        return result;





    }














}
