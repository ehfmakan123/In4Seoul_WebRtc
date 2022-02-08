package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;
    String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desks_id")
    Desks desk;












}
