package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "meetinghistory")
public class MeetingHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "started_at")
    LocalDateTime startedAt;

    @Column(name = "ended_at")
    LocalDateTime endedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    Staff staff;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desks_id")
    Desks desks;

}
