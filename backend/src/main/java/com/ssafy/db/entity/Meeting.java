package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "Meeting")
@Entity
@Getter
@Setter
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "desk_id")
    String deskId;
    @Column(name = "area_id")
    int areaId;



}
