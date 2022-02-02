<template>
  <div>
    <hr>
    <h1>PostDetail</h1>
    <p><strong>{{ selectedPost.title }}</strong></p>
    <p>{{ selectedPost.updatedAt }}</p>
    <p>{{ selectedPost.content }}</p>
    <p>
      <button @click="tryEdit">편집</button>
      <button @click="cancle">닫기</button>
    </p>
    <post-password-form @password-correct="editPost" v-if="state.showPasswordForm"></post-password-form>
    <post-update-form @try-delete="tryDelete" @try-unsave-close="tryUnsaveClose" v-if="state.showUpdateForm" :post="selectedPost"></post-update-form>
    <post-delete-form v-if="state.showDeleteForm"></post-delete-form>
    <post-close-form v-if="state.showCloseForm"></post-close-form>
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
  props:["selectedPost"],
  setup() {
    const state = ref({
      showPasswordForm: false,
      showUpdateForm: false,
      showDeleteForm: false,
      showCloseForm: false,
    })
    const tryEdit = () => {
      console.log("편집 버튼 클릭됨!")
      console.log("비밀번호 입력 모달 열림")
      state.value.showPasswordForm = !state.value.showPasswordForm
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
      console.log("상세조회 모달창 꺼짐")
    }
    const editPost = () => {
      console.log('비밀번호가 옳습니다.')
      console.log('편집 폼 켜짐')
      state.value.showUpdateForm = !state.value.showUpdateForm
      console.log(state.value.showUpdateForm)
    }

    const tryDelete = () => {
      state.value.showDeleteForm = !state.value.showDeleteForm
    }
    const tryUnsaveClose = () => {
      state.value.showCloseForm = !state.value.showCloseForm
    }


    return {state, tryEdit, cancle, editPost, tryDelete, tryUnsaveClose}
  }
}
</script>
