<template>
  <div>
    <h3>포스트 작성 폼</h3>
    <p><input type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
    <p><input type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요"></p>
    <p>개인정보는 남기지 마세요</p>
    <p>등록한 비밀번호는 포스트를 수정/삭제할 때 사용됩니다.</p>
    <p>비밀번호: <input type="password" v-model="state.myPost.password" placeholder="내용을 입력해주세요"></p>
    <p>비밀번호 확인: <input type="password" v-model="state.myPost.passwordConfirm" placeholder="내용을 입력해주세요"></p>
    <p>
      <button @click="savePost">저장</button>
      <button @click="cancle">취소</button>
    </p>
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
