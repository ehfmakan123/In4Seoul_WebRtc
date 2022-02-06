package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name ="staff")
public class Staff  extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "staff_id")
    String staffId;
    String password;
    String name;
    String phone;
    String email;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="areas_id")
    private Areas areas;
    @Column(name = "admin_yn")
    String adminYN;
    @Column(name = "delete_yn")
    String deleteYN;
    @Column(name = "approve_yn")
    String approveYN;

}
