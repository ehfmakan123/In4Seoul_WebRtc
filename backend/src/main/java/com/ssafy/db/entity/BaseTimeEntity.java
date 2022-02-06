package com.ssafy.db.entity;


/*
JPA Auditing이란?

JPA가 제공하는 기능으로  데이터가 입력, 수정 될때마다 감시해 자동으로 시간을 입력해주는 기능을 한다

https://sgcomputer.tistory.com/284
https://mchch.tistory.com/165
https://kimseungjae.tistory.com/13

생성시간 수정시간 자동 등록을 위한 클래스

 */


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass  // JPA Entity클래스들이 BaseTimeEntity를 상속할 때 입력한 필드변수가 칼럼으로 인식되도록 함
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing 기능을 포함시킴
public class BaseTimeEntity {


    @CreatedDate   // Entity가 생성되어 저장될때 시간이 자동 저장됨
    @Column(name = "created_at")
    private String createdAt;

    @LastModifiedDate  //조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    @Column(name = "updated_at")
    private String updatedAt;



    @PrePersist // 엔티티 insert이전에 실행
    public void onPrePersist()
    {
        this.createdAt= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.updatedAt=this.createdAt;

    }

    @PreUpdate //엔티티 update이전 실행
    public void onPreUpdate()
    {
        this.updatedAt=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }
}
