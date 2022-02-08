package com.ssafy.api.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostReq {



    String title;
    String content;
    String password;

    public PostReq() {
    }


}
