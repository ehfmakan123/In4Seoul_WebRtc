package com.ssafy.db.repository;

import com.querydsl.core.Tuple;
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
    QMeetingHistory qmeeting=QMeetingHistory.meetingHistory;



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
                .select(new QMeetingLogDto(qmeeting.id, qdesk.korName.as("deskName"), qmeeting.startedAt, qmeeting.endedAt, qmeeting.content))
                .from(qmeeting)
                .join(qmeeting.desks, qdesk)
                .where(qmeeting.staff.id.eq(id))
                .orderBy(qmeeting.id.desc())
                .fetch();


        return result;
    }
}
