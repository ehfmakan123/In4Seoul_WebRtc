package com.ssafy.db.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.*;
import com.ssafy.db.entity.QAreas;
import com.ssafy.db.entity.QDesks;
import com.ssafy.db.entity.QPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeskRepositorySupport {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QDesks qdesk=QDesks.desks;
    QPosts qpost=QPosts.posts;
    QAreas qareas= QAreas.areas;

    public PostDto getPost(Long id)
    {

        PostDto result = jpaQueryFactory
                .select(new QPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt))
                .from(qpost)
                .where(qpost.id.eq(id))
                .fetchOne();

        return result;

    }




    //지역 목록 가져오기


    public List<AreaDto> getAreas()
    {
        List<AreaDto> result = jpaQueryFactory
                .select(new QAreaDto(qareas.id, qareas.korName,qareas.engName))
                .from(qareas)
                .fetch();

        return result;
    }



    //선택 지역에 해당하는 데스크 목록 가져오기

    public List<SelectedDeskDto> getDeskList(int id)
    {
        List<SelectedDeskDto> result = jpaQueryFactory
                .select(new QSelectedDeskDto(qdesk.id, qdesk.korName, qdesk.engName))
                .from(qdesk)
                .where(qdesk.area.id.eq(id))
                .fetch();


        return result;
    }
}