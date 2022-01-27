package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "desks")
@Entity
@Getter
@Setter
public class Desks extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "desk_id")
    String deskId;


    String password;

    @Column(name = "eng_name")
    String engName;
    @Column(name = "kor_name")
    String korName;
    String latitude;
    String altitude;
//    @Column(name = "created_at")
//    String createdAt;
//    @Column(name = "updated_at")
//    String updatedAt;

    @Column(name = "delete_yn")
    String deleteYN;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "areas_id")
    Areas area;
}
