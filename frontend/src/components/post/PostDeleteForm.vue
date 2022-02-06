<template>
  <div>
    <div class="modal fade" id="deleteModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content" style="width:30rem; height:9rem">
          <div class="modal-body">
            <p>글을 삭제하시겠습니까?</p>
            <p class="d-flex justify-content-end">
              <button @click="deletePost" type="button" class="btn btn-primary" data-bs-dismiss="modal">삭제</button>
              <button @click="cancle" type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal">취소</button>
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- <hr>
    <p>글을 삭제하시겠습니까?</p>
    <p>
      <button @click="deletePost">삭제</button>
      <button @click="cancle">취소</button>
    </p> -->
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PostDeleteForm',
  components: {
  },
  props: ['postId'],
  setup(props) {
    const deletePost = () => {
      console.log("삭제")
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
    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
    }
    return { deletePost, cancle }
  }
}
</script>
