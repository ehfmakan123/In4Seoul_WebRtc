package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.MeetingLogDto;
import com.ssafy.api.dto.QMeetingLogDto;
import com.ssafy.api.dto.QStaffDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStaff qstaff= QStaff.staff;
    QAreas qareas= QAreas.areas;
    QDesks qdesk=QDesks.desks;
    QPosts qpost=QPosts.posts;
    QMeetingHistory qmeetingHistory =QMeetingHistory.meetingHistory;
    QMeeting qmeeting= QMeeting.meeting;


    //내 정보 조회
    public StaffDto getStaffDto(String id)
    {

        StaffDto result = jpaQueryFactory
                .select(new QStaffDto(qstaff.id, qstaff.staffId.as("userId"), qstaff.name,
                        qstaff.phone, qstaff.email, qstaff.deleteYN, qstaff.approveYN, qareas.id.as("areaId"), qareas.korName.as("areaName"),qstaff.createdAt,qstaff.updatedAt))
                .from(qstaff)
                .leftJoin(qstaff.areas, qareas)
                .where(qstaff.staffId.eq(id))
                .fetchOne();

        return result;
    }





    // 내 상당 목록 조회   가장 최신 상담목록이 상단에 가게끔

    public List<MeetingLogDto> getMeetingLog(int id)
    {

        List<MeetingLogDto> result = jpaQueryFactory
                .select(new QMeetingLogDto(qmeetingHistory.id, qdesk.korName.as("deskName"), qmeetingHistory.startedAt, qmeetingHistory.endedAt, qmeetingHistory.content))
                .from(qmeetingHistory)
                .join(qmeetingHistory.desks, qdesk)
                .where(qmeetingHistory.staff.id.eq(id))
                .orderBy(qmeetingHistory.id.desc())
                .fetch();


        return result;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 화상 상담 관련



    // 알림을 보내줄 상담사들을 선택
    public List<Staff> getStaffList(int areaId)
    {
        List<Staff> result = jpaQueryFactory
                .select(qstaff)
                .from(qstaff)
                .where(qstaff.areas.id.eq(areaId).and(qstaff.matchYN.eq("Y")).and(qstaff.fcmToken.eq("0"
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
                .where(qmeeting.areaId.eq(areaId))
                .fetchCount();
return count;
    }













}
