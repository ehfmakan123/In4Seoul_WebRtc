package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "desk")
@Entity
@Getter
@Setter
public class Desk extends BaseTimeEntity {


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
    Area area;


    public Desk() {
    }

    public Desk(int id) {
        this.id = id;
    }
}
