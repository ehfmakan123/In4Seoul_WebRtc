<template>
  <div>
    <!-- 글 수정 Modal -->
    <div class="modal fade" id="updateModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content bg-yellow box-big p-3">
          <div class="d-flex align-items-center justify-content-between pt-3 px-3 pb-2">
            <p class="w-100"><input class="w-100 post-input" style="height: 2.5rem;" type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
          </div>
          <div class="modal-body" style="height: 24rem;">
            <textarea v-model="state.myPost.content" type="text" class="form-control post-input" style="height: 88%; resize: none;"></textarea>
            <p class="mt-3 text-small">※ 개인정보는 남기지 마세요.</p>
          </div>
          <div class="d-flex justify-content-end p-2 px-3">
            <button @click="tryDelete" type="button" class="btn btn btn-outline-danger t-red-1 bd-red-1 rounded-btn me-auto">삭제</button>
            <button @click="savePost" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn">저장</button>
            <button @click="cancle" type="button" class="btn btn-outline-dark ms-3 rounded-btn">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { computed, ref } from 'vue'
import { Modal } from 'bootstrap'

export default {
  name: 'PostUpdateForm',
  components: {
  },
  props:{
    post: Object,
  },
  setup(props) {
    const state = ref({
      myPost: {
        title: computed(() => props.post.title),
        content: computed(() => props.post.content)
      }
    })

    const tryDelete = () => {
      console.log("삭제 버튼 클릭됨!")
      const deleteModal = document.querySelector('#deleteModal')
      let modal = Modal.getOrCreateInstance(deleteModal)
      modal.show()
    }

    const savePost = () => {
      console.log("저장 버튼 클릭됨!")
      console.log(state.value.myPost)
      // axios({
      //   method: 'put',
      //   url: `http://127.0.0.1:8000/board/posts/${props.post.postId}/`,
      //   // headers: this.tokenHeader(),
      //   data: {
      //     title: state.value.myPost.title,
      //     content: state.value.myPost.content
      //   }
      // })
      //   .then(() => {
      //     console.log('업데이트 성공!')
          // this.fetchReviewList(this.movieDetail.id)
          // 모달창 끄기
          const updateModal = document.querySelector('#updateModal')
          let modal = Modal.getOrCreateInstance(updateModal)
          modal.hide()
        // })
        // .catch(err => console.error(err))

    }

    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
      const closeModal = document.querySelector('#closeModal')
      let modal = Modal.getOrCreateInstance(closeModal)
      modal.show()
    }

    return {state, tryDelete, savePost, cancle}
  }
}
</script>
