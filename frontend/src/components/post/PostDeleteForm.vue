<template>
  <div>
    <div class="modal fade" id="deleteModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content bd-red-1 ms-3">
          <div class="modal-body p-4">
            <div class="mt-4 ms-4">
              <p class="fw-bold" style="font-size: 1.1rem;">글을 <span class="t-red-1">삭제</span>하시겠습니까?</p>
              <p class="mt-2 fs-6 fw-light t-gray-3">Do you want to delete the Post?</p>

            </div>
            <div class="d-flex justify-content-end pt-3">
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
// import { useStore } from 'vuex'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'PostDeleteForm',
  components: {
  },
  props: ['postId'],
  setup(props, {emit}) {
    // const store = useStore()
    // const deskData = JSON.parse(localStorage.getItem('deskData'))

    const deletePost = () => {
      console.log("글 삭제")

      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }

      axios({
        method: 'delete',
        url: `${SERVER_HOST}/desk/posts/${props.postId}`,
        headers: config
      })
        .then(res => {
          console.log(res)
          // fetchPostList
          // store.dispatch('fetchPostList', deskData.deskPk)
          emit("post-deleted")
        })
        .catch(err => console.error(err))

      // updateModal도 닫기
      const updateModal = document.querySelector('#updateModal')
      let modal = Modal.getOrCreateInstance(updateModal)
      modal.hide()

      
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