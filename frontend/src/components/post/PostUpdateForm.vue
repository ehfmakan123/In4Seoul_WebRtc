<template>
  <div>
    <!-- 글 수정 Modal -->
    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <p class="w-100"><input class="w-100 input-title" type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
          </div>
          <div class="modal-body" style="height: 27rem;">
            <textarea rows="5" v-model="state.myPost.content" type="text" class="form-control"></textarea>
            <!-- <p><input class="input-content" type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요"></p> -->
            <p class="mb-0" style="font-size:13px;">※ 개인정보는 남기지 마세요.</p>
          </div>
          <div class="modal-footer">
            <button @click="tryDelete" type="submit" class="btn">삭제</button>
            <button @click="savePost" type="submit" class="btn btn-primary">저장</button>
            <button data-bs-toggle="modal" data-bs-target="#closeModal" @click="cancle" type="button" class="btn btn-secondary">닫기</button>
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
