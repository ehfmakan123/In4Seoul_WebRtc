package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Getter
@Setter
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desks_id")
    Desk desk;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="areas_id")
    Area area;



}
