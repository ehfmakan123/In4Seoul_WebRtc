package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;
    String password;
    @Column(name = "created_at")
    String createdAt;
    @Column(name = "updated_at")
    String updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desks_id")
    Desks desk;












}
