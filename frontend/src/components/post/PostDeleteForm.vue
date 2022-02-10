<template>
  <div>
    <div class="modal fade" id="deleteModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content ms-2" style="width:30rem; height:9rem">
          <div class="modal-body">
            <p>글을 삭제하시겠습니까?</p>
            <p class="d-flex justify-content-end">
              <button @click="deletePost" type="button" class="btn btn-primary" data-bs-dismiss="modal">삭제</button>
              <button type="button" class="btn btn-secondary"  data-bs-dismiss="modal">취소</button>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { Modal } from 'bootstrap'

export default {
  name: 'PostDeleteForm',
  components: {
  },
  props: ['postId'],
  setup(props) {
    const deletePost = () => {
      console.log("삭제")

      // updateModal도 닫기
      const updateModal = document.querySelector('#updateModal')
      let modal = Modal.getOrCreateInstance(updateModal)
      modal.hide()

      axios({
        method: 'delete',
        url: `http://127.0.0.1:8000/board/posts/${props.postId}`,
        // headers: this.tokenHeader()
      })
        .then(res => {
          console.log(res)
          // this.fetchReviewList(this.movieDetail.id)
        })
        .catch(err => console.error(err))
    }
    return { deletePost }
  }
}
</script>
