<template>
  <div>
    <!-- 글 생성 Modal -->
    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h3><input type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></h3>
          </div>
          <div class="modal-body" style="height: 27rem;">
            <p><input type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요" style="width:27rem;height:15rem;"></p>
            <p class="mb-0" style="font-size:13px;">※ 개인정보는 남기지 마세요.</p>
            <p style="font-size:13px;">※ 등록한 비밀번호는 포스트를 수정/삭제할 때 사용됩니다.</p>
            <p>비밀번호: <input type="password" v-model="state.myPost.password" placeholder="비밀번호를 입력해주세요"></p>
            <p class="mb-0">비밀번호 확인: <input type="password" v-model="state.myPost.passwordConfirm" placeholder="비밀번호를 입력해주세요"></p>
          </div>
          <div class="modal-footer">
            <button @click="savePost" type="button" class="btn btn-primary">저장</button>
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
import { ref } from 'vue'

export default {
  name: 'PostForm',
  components: {
  },
  setup() {
    const state = ref({
      myPost: {
        title: '',
        content: '',
        password: '',
        passwordConfirm: ''
      }
    })

    const savePost = () => {
      console.log("저장 버튼 클릭됨!")
      console.log(state.value.myPost)
      state.value.myPost = {
        title: '',
        content: '',
        password: '',
        passwordConfirm: ''
      }
    }

    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
      console.log("모달창 닫기")
    }
    return {state, savePost, cancle}
  }
}
</script>
