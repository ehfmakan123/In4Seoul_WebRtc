<template>
  <div>
    <hr>
    <h1>PostDetail</h1>
    <p><strong>{{ post.title }}</strong></p>
    <p>{{ post.updatedAt }}</p>
    <p>{{ post.content }}</p>
    <p>
      <button @click="editPost">편집</button>
      <button @click="cancle">닫기</button>
    </p>
    <post-password-form v-if="state.showPasswordForm"></post-password-form>
    <post-update-form></post-update-form>
    <post-close-form></post-close-form>
    <post-delete-form></post-delete-form>
  </div>
</template>

<script>
import { ref } from 'vue'
import PostPasswordForm from '@/components/post/PostPasswordForm'
import PostUpdateForm from '@/components/post/PostUpdateForm'
import PostCloseForm from '@/components/post/PostCloseForm'
import PostDeleteForm from '@/components/post/PostDeleteForm'


export default {
  name: 'PostDetail',
  components: { PostPasswordForm, PostUpdateForm, PostCloseForm, PostDeleteForm },
  props:["post"],
  setup() {
    const state = ref({
      showPasswordForm: false
    })
    const editPost = () => {
      console.log("편집 버튼 클릭됨!")
      console.log("비밀번호 입력 모달 열림")
      state.value.showPasswordForm = !state.value.showPasswordForm
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
      console.log("상세조회 모달창 꺼짐")
    }
    return {state, editPost, cancle}
  }
}
</script>
