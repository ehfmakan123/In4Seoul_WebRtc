package com.ssafy.api.service;


import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.SelectedDeskDto;
import com.ssafy.api.request.PostReq;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Post;
import com.ssafy.db.entity.WaitingList;
import com.ssafy.db.repository.DeskRepository;
import com.ssafy.db.repository.DeskRepositorySupport;
import com.ssafy.db.repository.PostRepository;
import com.ssafy.db.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskServiceImpl implements DeskService{

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    DeskRepositorySupport deskRepositorySupport;


    @Autowired
    WaitingListRepository waitingListRepository;



    @Override
    public Desk findByDeskId(String id) {


        Optional<Desk> result = deskRepository.findByDeskId(id);



        if(result.isPresent())
        {
            return result.get();
        }

        else return null;

    }

    @Override
    public boolean registerPost(String id, PostReq dto) {


        Optional<Desk> result = deskRepository.findByDeskId(id);


        Post posts = new Post();
        posts.setContent(dto.getContent());
        posts.setTitle(dto.getTitle());
        posts.setPassword(dto.getPassword());
        posts.setDesk(result.get());


        Post post = postRepository.save(posts);



        return true;
    }

    @Override
    public PostDto getPost(long id) {


        return deskRepositorySupport.getPost(id);

    }

    @Override
    public boolean updatePost(long id, PostReq dto) {
        Post post = postRepository.findById(id).get();

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Post save = postRepository.save(post);


        return true;


    }

    @Override
    public boolean deletePost(long id) {



        /*
          삭제 후에 이미 삭제된 키값으로 또 삭제를 한 경우

          삭제 전에 먼저 해당 키값(id)을 가진 객체가 존재하는 지 확인하고 없다면

        IllegalArgumentException을 던진다


         */


        try {
            postRepository.deleteById(id);

        } catch (Exception e) {
            return false;
        }


        return true;
    }

    @Override
    public String getPostPassword(long id) {
        Optional<Post> byId = postRepository.findById(id);

        Post post = byId.get();


        return post.getPassword();
    }

    @Override
    public List<AreaDto> getAreas() {

        return deskRepositorySupport.getAreas();
    }






    //선택지역에 해당하는 데스크 목록 가져오기
    @Override
    public List<SelectedDeskDto> getDeskList(int id) {
        return deskRepositorySupport.getDeskList(id);
    }



    // 게시글 목록 가져오기
    @Override
    public ListResult<PostDto> getPostList(Integer desk, Integer page) {

        ListResult<PostDto> postList = deskRepositorySupport.getPostList(desk, page);

//페이지네이션 처리

        Long totalCount = postList.getTotalCount();

        int totalPage = totalCount.intValue() / 10; //총 페이지

        if (totalCount % 10 > 0) {
            totalPage++;
        }


        int startPage = ((page - 1) / 5) * 5 + 1; //시작 페이지 번호
        int endPage = startPage + 4;


        if (endPage > totalPage) endPage = totalPage;

        boolean pre = false;

        boolean start = false;


        if (page - 5 >= 1) {
            pre = true;
        }


        if (startPage > 1) {
            start = true;
        }


        boolean next = false;

        boolean end = false;

        if (page + 5 <= totalPage || endPage < totalPage) {
            next = true;

        }

        if (endPage < totalPage) {
            end = true;
        }


        // 값 세팅

        postList.setStartPage(startPage);
        postList.setEndPage(endPage);
        postList.setNowPage(page);
        postList.setNext(next);
        postList.setPre(pre);
        postList.setStart(start);
        postList.setEnd(end);
        postList.setTotalPage(totalPage);
        return postList;
        
    }




    @Override
    public void deleteWaitingList(long deskPk) {



            System.out.println("오긴 오나요?"+deskPk);


            Optional<Desk> byId = deskRepository.findById((int)deskPk);;
            Desk desk = byId.get();
            System.out.println("불러온 기본키값="+desk.getDeskId());
            Optional<WaitingList> waitingList = waitingListRepository.deleteByDesk(desk);

            if(waitingList.isPresent())
            {
                System.out.println("삭제된거");
            }



    }
}
