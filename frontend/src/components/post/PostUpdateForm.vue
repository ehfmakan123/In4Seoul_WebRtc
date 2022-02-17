<template>
  <div>
    <!-- 글 수정 Modal -->
    <div class="modal fade" id="updateModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content bg-yellow box-big p-3">
          <div class="d-flex align-items-center justify-content-between pt-3 px-3 pb-2">
            <p class="w-100"><input id="update-title" class="w-100 post-input" style="height: 2.5rem;" type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
          </div>
          <div class="modal-body" style="height: 24rem;">
            <textarea id="update-content" v-model="state.myPost.content" type="text" class="form-control post-input" style="height: 88%; resize: none;"></textarea>
            <p class="mt-3 text-small">※ 개인정보는 남기지 마세요.</p>
          </div>
          <div class="d-flex justify-content-end p-2 px-3">
            <button @click="tryDelete" type="button" class="btn btn btn-outline-danger t-red-1 bd-red-1 rounded-btn me-auto">Delete</button>
            <button @click="savePost" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn">Save</button>
            <button @click="cancle" type="button" class="btn btn-outline-dark ms-3 rounded-btn">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { computed, ref } from 'vue'
import { Modal } from 'bootstrap'
// import { useStore } from 'vuex'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'PostUpdateForm',
  components: {
  },
  props:["post"],
  setup(props, {emit}) {
    // const store = useStore()
    // const deskData = JSON.parse(localStorage.getItem('deskData'))

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
      console.log(document.querySelector('#update-title').value)
      console.log(document.querySelector('#update-content').value)

        
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }

      axios({
        method: 'put',
        url: `${SERVER_HOST}/desk/posts/${props.post.id}`,
        headers: config,
        data: {
          title: document.querySelector('#update-title').value,
          content: document.querySelector('#update-content').value
        }
      })
        .then((res) => {
          console.log('업데이트 성공!')
          console.log(res.data)
          // fetchPostList
          // store.dispatch('fetchPostList', deskData.deskPk)
          emit("post-updated")

          // 모달창 끄기
          const updateModal = document.querySelector('#updateModal')
          let modal1 = Modal.getOrCreateInstance(updateModal)
          modal1.hide()
        })
        .catch(err => console.error(err))

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
