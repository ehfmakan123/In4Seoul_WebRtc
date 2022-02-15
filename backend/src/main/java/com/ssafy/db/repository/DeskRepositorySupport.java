package com.ssafy.db.repository;


import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.*;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.QArea;
import com.ssafy.db.entity.QDesk;
import com.ssafy.db.entity.QPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeskRepositorySupport {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QDesk qdesk=QDesk.desk;
    QPost qpost=QPost.post;
    QArea qareas= QArea.area;

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


    //게시글 목록 가져오기


    public ListResult<PostDto> getPostList(Integer desk, Integer page)
    {

        QueryResults<PostDto> postDtoQueryResults = jpaQueryFactory
                .select(new QPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt))
                .from(qpost)
                .where(qpost.desk.id.eq(desk))
                .orderBy(qpost.id.desc())
                .offset((page - 1) * 10)
                .limit(10)
                .fetchResults();


        List<PostDto> results = postDtoQueryResults.getResults();
        long count = postDtoQueryResults.getTotal();

        ListResult<PostDto> result = new ListResult<>(200, "성공", results);
        result.setTotalCount(count);


        return result;
    }
}
