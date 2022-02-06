<template>
  <div>
    <!-- 글 생성 Modal -->
    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <p class="w-100"><input class="w-100 input-title" type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요" required autofocus></p>
          </div>
          <div class="modal-body" style="height: 27rem;">
            <p><input required class="input-content" type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요"></p>
            <p class="mb-0" style="font-size:13px;">※ 개인정보는 남기지 마세요.</p>
            <p style="font-size:13px;">※ 등록한 비밀번호는 포스트를 수정/삭제할 때 사용됩니다.</p>
            <p>비밀번호: <input required class="w-50" type="password" v-model="state.myPost.password" placeholder="비밀번호를 입력해주세요"></p>
            <p class="mb-0">비밀번호 확인: <input required class="w-50" type="password" v-model="state.myPost.passwordConfirm" placeholder="비밀번호를 입력해주세요"></p>
            <p id="password-error" class="d-none alert alert-danger mb-2" role="alert">비밀번호가 일치하지 않습니다.</p>
          </div>
          <div class="modal-footer">
            <button @click="savePost" type="submit" class="btn btn-primary">저장</button>
            <button @click="cancle" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
          </div>
        </div>
      </div>
    </div>

    <!-- <h3>포스트 작성 폼</h3>
    <p><input type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
    <p><input type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요"></p>
    <p>개인정보는 남기지 마세요</p>
    <p>등록한 비밀번호는 포스트를 수정/삭제할 때 사용됩니다.</p>
    <p>비밀번호: <input type="password" v-model="state.myPost.password" placeholder="내용을 입력해주세요"></p>
    <p>비밀번호 확인: <input type="password" v-model="state.myPost.passwordConfirm" placeholder="내용을 입력해주세요"></p>
    <p>
      <button @click="savePost">저장</button>
      <button @click="cancle">취소</button>
    </p> -->
  </div>
</template>

<script>
// import axios from 'axios'
import { ref } from 'vue'

export default {
  name: 'PostForm',
  components: {
  },
  setup(props, { emit }) {
    const state = ref({
      myPost: {
        title: '',
        content: '',
        password: '',
        passwordConfirm: ''
      }
    })

    // const tokenHeader = () => {
    //   const token = localStorage.getItem('token')
    //   return {
    //     Authorization: `Token ${token}`
    //   }
    // }

    const savePost = () => {
      console.log("저장 버튼 클릭됨!")
      console.log(state.value.myPost)

      if (state.value.myPost.password === state.value.myPost.passwordConfirm) {
        console.log('비밀번호 일치함')
        // axios({
        //   method: 'post',
        //   url: `http://127.0.0.1:8000/board/posts`,
        //   headers: tokenHeader(),
        //   data: {
        //     deskId: 1,
        //     areaId: 1,
        //     title: state.value.myPost.title,
        //     content: state.value.myPost.content,
        //     password: state.value.myPost.password,
        //   }
        // })
        //   .then(() => {
        //     // fetchPostList
        //     state.value.myPost = {
        //       title: '',
        //       content: '',
        //       password: '',
        //       passwordConfirm: ''
        //     }
        //   })
        //   .catch(err => console.error(err))      
      } else {
        console.log('비밀번호가 다름!!!')
        const errorMessage = document.querySelector('#password-error')
        errorMessage.classList.remove('d-none')
      }

      // post를 추가하기 위한 임시 emit 코드
      emit('add-new-post', {
        "id":"1",
        "title":state.value.myPost.title,
        "content":state.value.myPost.content,
        "createdAt":"2022-01-01 11:20:00",
        "updatedAt":"2022-01-01 11:20:00"
      })
      state.value.myPost = {
            title: '',
            content: '',
            password: '',
            passwordConfirm: ''
          }
      //
    }

    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
      console.log("모달창 닫기")
    }
    return {state, savePost, cancle}
  }
}
</script>
