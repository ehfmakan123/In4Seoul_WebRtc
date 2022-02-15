package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "eng_name")
    String engName;
    @Column(name = "kor_name")
    String korName;

    public Area() {
    }

    public Area(int id) {
        this.id = id;
    }

    public Area(int id, String engName, String korName) {
        this.id = id;
        this.engName = engName;
        this.korName = korName;
    }
}
