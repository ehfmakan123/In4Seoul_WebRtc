package com.ssafy.db.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.QPostDto;
import com.ssafy.db.entity.QPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeskRepositorySupport {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QPosts qpost=QPosts.posts;


    public PostDto getPost(Long id)
    {

        PostDto result = jpaQueryFactory
                .select(new QPostDto(qpost.id, qpost.title, qpost.content, qpost.createdAt, qpost.updatedAt))
                .from(qpost)
                .where(qpost.id.eq(id))
                .fetchOne();

        return result;

    }


}
