<template>
  <div>
    <!-- 모달들 먼저 선언 -->
    <post-password-form :postId="selectedPost.id"></post-password-form>
    <post-update-form :post="selectedPost" @post-updated="postUpdated"></post-update-form>
    <post-delete-form :postId="selectedPost.id" @post-deleted="postUpdated"></post-delete-form>
    <post-close-form></post-close-form>

    <!-- 상세조회 Modal -->
    <div class="modal fade" id="detailModal" style="z-index: 1055;" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content bg-yellow box-big p-3">
          <div class="d-flex align-items-center justify-content-between p-3">
            <h5 class="modal-title fw-bold" id="exampleModalLabel">{{ selectedPost.title }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body pt-0" style="height: 21rem;">
            <p class="mb-3 t-gray-3 text-small">{{ slicedUpdatedAt }}</p>
            <p class="my-2">{{ selectedPost.content }}</p>
          </div>
          <div class="d-flex justify-content-end p-2">
            <button @click="tryEdit" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn">Edit</button>
            <button type="button" class="btn btn-outline-dark ms-3 rounded-btn" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PostPasswordForm from '@/components/post/PostPasswordForm'
import PostUpdateForm from '@/components/post/PostUpdateForm'
import PostCloseForm from '@/components/post/PostCloseForm'
import PostDeleteForm from '@/components/post/PostDeleteForm'
import { Modal } from 'bootstrap'


export default {
  name: 'PostDetail',
  components: { PostPasswordForm, PostUpdateForm, PostCloseForm, PostDeleteForm },
  props:["selectedPost"],
  computed: {
    // computed getter
    slicedUpdatedAt() {
      return this.selectedPost.updatedAt ? this.selectedPost.updatedAt.slice(0,10) : ''
    }
  },
  setup(props, {emit}) {
    const tryEdit = () => {
      console.log("편집 버튼 클릭됨. 비밀번호 입력 모달 열림!")
      // passwordModal 열기
      const passwordModal = document.querySelector('#passwordModal')
      const modal = Modal.getOrCreateInstance(passwordModal)
      modal.show()
    }

    const postUpdated = () => {
      emit("post-updated")
    }

    return { tryEdit, postUpdated }
  }
}
</script>
