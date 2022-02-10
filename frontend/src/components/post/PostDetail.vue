<template>
  <div>
    <!-- 모달들 먼저 선언 -->
    <post-password-form></post-password-form>
    <post-update-form :post="selectedPost"></post-update-form>
    <post-delete-form></post-delete-form>
    <post-close-form></post-close-form>

    <!-- 상세조회 Modal -->
    <div class="modal fade" id="detailModal" style="z-index: 1055;" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content postit bg-yellow">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">{{ selectedPost.title }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" style="height: 21rem;">
            <p>수정일: {{ selectedPost.updatedAt }}</p>
            {{ selectedPost.content }}
          </div>
          <div class="modal-footer">
            <button @click="tryEdit" type="button" class="btn btn-primary">편집</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import PostPasswordForm from '@/components/post/PostPasswordForm'
import PostUpdateForm from '@/components/post/PostUpdateForm'
import PostCloseForm from '@/components/post/PostCloseForm'
import PostDeleteForm from '@/components/post/PostDeleteForm'
import { Modal } from 'bootstrap'


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
      console.log("편집 버튼 클릭됨. 비밀번호 입력 모달 열림!")
      // passwordModal 열기
      const passwordModal = document.querySelector('#passwordModal')
      const modal = Modal.getOrCreateInstance(passwordModal)
      modal.show()
    }
    return {state, tryEdit}
  }
}
</script>
