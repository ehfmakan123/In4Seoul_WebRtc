package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

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
    private Area area;
    @Column(name = "admin_yn")
    String adminYN;
    @Column(name = "delete_yn")
    String deleteYN;
    @Column(name = "approve_yn")
    String approveYN;



    //FCM 토큰 값  로그아웃 시 0으로 바뀜   로그인하면 토큰값을 저장
    @Column(name = "fcm_token")
    String fcmToken;

    // 현재 상담중인지 여부
    @Column(name="match_yn")
    String matchYN;


    public Staff() {
    }

    public Staff(int id) {
        this.id = id;
    }
}
