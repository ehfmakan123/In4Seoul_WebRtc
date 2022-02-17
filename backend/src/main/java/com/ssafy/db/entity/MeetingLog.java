package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Setter
@Getter
@Table(name = "meetingLog")
@EntityListeners(AuditingEntityListener.class) //MeetingLog 클래스에 Auditing 기능을 포함시킴
public class MeetingLog  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @CreatedDate   // Entity가 생성되어 저장될때 시간이 자동 저장됨
    @Column(name = "started_at")
   String startedAt;


    @LastModifiedDate  //조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    @Column(name = "ended_at")
    String endedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    Staff staff;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desks_id")
    Desk desk;



    String content;


    @PrePersist // 엔티티 insert이전에 실행
    public void onPrePersist()
    {
        this.startedAt= LocalDateTime.now().plusHours(9).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.endedAt=this.startedAt;

    }

    @PreUpdate //엔티티 update이전 실행
    public void onPreUpdate()
    {
        this.endedAt=LocalDateTime.now().plusHours(9).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

}
