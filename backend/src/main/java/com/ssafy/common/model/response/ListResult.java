package com.ssafy.common.model.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ListResult<T>  extends BaseResponseBody{



    private int totalPage;//총 페이지 개수(10개 단위)
    private Long totalCount;  // 전체 글 개수
    private int nowPage; //현재 페이지 번호
    private int startPage; // 시작 페이지 번호
    private int endPage; // 마지막 페이지 번호
    private boolean pre; // 이전페이지 사용가능 여부
    private boolean next; // 다음페이지 사용가능 여부
    private boolean start; //처음 페이지로 이동
    private boolean end;  //마지막 페이지로 이동



    private List<T> data;




    public ListResult(Integer statusCode, String message, List<T> data) {
        super(statusCode, message);
        this.data = data;
    }
}
