<template>
  <div>
    <div class="modal fade" id="deleteModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content bd-red-1 ms-3">
          <div class="modal-body p-4">
            <p class="fw-bold m-4" style="font-size: 1.1rem;">글을 <span class="t-red-1">삭제</span>하시겠습니까?</p>
            <div class="d-flex justify-content-end pt-2 mt-4">
              <button @click="deletePost" type="button" class="btn btn-outline-danger t-red-1 bd-red-1 rounded-btn" data-bs-dismiss="modal">삭제</button>
              <button @click="cancle" type="button" class="btn btn-outline-dark ms-3 rounded-btn" data-bs-dismiss="modal">취소</button>
            </div>
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
<style scoped>
  .modal-content{
    border-width: 1.5px;
    border-style: solid;
    border-radius: 1rem;
    width:29rem; 
    height:10rem;
  }
</style>