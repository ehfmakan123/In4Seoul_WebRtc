package com.ssafy.api.service;


import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.SelectedDeskDto;
import com.ssafy.api.request.PostReq;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.Posts;
import com.ssafy.db.repository.AdminRepository;
import com.ssafy.db.repository.DeskRepositorySupport;
import com.ssafy.db.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskServiceImpl implements DeskService{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    DeskRepositorySupport deskRepositorySupport;




    @Override
    public Desks findByDeskId(String id) {


        Optional<Desks> result = adminRepository.findByDeskId(id);



        if(result.isPresent())
        {
            return result.get();
        }

        else return null;

    }

    @Override
    public boolean registerPost(String id, PostReq dto) {


        Optional<Desks> result = adminRepository.findByDeskId(id);


        Posts posts = new Posts();
        posts.setContent(dto.getContent());
        posts.setTitle(dto.getTitle());
        posts.setPassword(dto.getPassword());
        posts.setDesk(result.get());


        Posts post = postRepository.save(posts);



        return true;
    }

    @Override
    public PostDto getPost(long id) {


        return deskRepositorySupport.getPost(id);

    }

    @Override
    public boolean updatePost(long id, PostReq dto) {
        Posts post = postRepository.findById(id).get();

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Posts save = postRepository.save(post);


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
        Optional<Posts> byId = postRepository.findById(id);

        Posts posts = byId.get();


        return posts.getPassword();
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

            return deskRepositorySupport.getPostList(desk,page);

    }
}
