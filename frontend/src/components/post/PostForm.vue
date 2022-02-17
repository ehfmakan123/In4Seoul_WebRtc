<template>
  <div>
    <!-- 글 생성 Modal -->
    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content bg-yellow box-big p-3">

          <div class="d-flex align-items-center justify-content-between pt-3 px-3 pb-2">
            <p class="w-100"><input class="w-100 post-input" style="height: 2.5rem;" type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요 Title"></p>
          </div>
          <div class="modal-body pb-0">
            <textarea v-model="state.myPost.content" placeholder="내용을 입력해주세요 Content" type="text" class="form-control post-input" style="height: 63%; resize: none;"></textarea>
            <p class="mt-3 text-small">※ 개인정보는 남기지 마세요.</p>
            <p class="mt-2 text-small">※ 등록한 비밀번호는 포스트를 수정/삭제할 때 사용됩니다.</p>

            <div class="input-group pt-1 mt-3">
              <span class="input-group-text border-0 bg-yellow fw-bold" id="post-password">Password</span>
              <input type="password" class="form-control post-input" v-model="state.myPost.password" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="input-group pt-1">
              <span class="input-group-text border-0 bg-yellow fw-bold" id="post-password-confirm">Repeat Password</span>
              <input type="password" class="form-control post-input" v-model="state.myPost.passwordConfirm" placeholder="비밀번호를 입력해주세요">
            </div>
            <p id="password-error" class="d-none t-red-2 text-small mt-3 ms-3">비밀번호가 일치하지 않습니다.</p>
          </div>
          <div class="d-flex justify-content-end p-2 px-3">
            <button @click="savePost" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn">Save</button>
            <button @click="cancle" type="button" class="btn btn-outline-dark ms-3 rounded-btn" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import { ref } from 'vue'
import { Modal } from 'bootstrap'
// import { useStore } from 'vuex'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'PostForm',
  components: {
  },
  setup(props, {emit}) {
    // const store = useStore()
    // const deskData = JSON.parse(localStorage.getItem('deskData'))

    const state = ref({
      myPost: {
        title: '',
        content: '',
        password: '',
        passwordConfirm: ''
      }
    })

    const savePost = () => {
      console.log("저장 버튼 클릭됨!")
      console.log(state.value.myPost)

      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }

      if (state.value.myPost.password === state.value.myPost.passwordConfirm) {
        console.log('비밀번호 일치함')

        axios({
          method: 'post',
          url: `${SERVER_HOST}/desk/posts`,
          headers: config,
          data: {
            title: state.value.myPost.title,
            content: state.value.myPost.content,
            password: state.value.myPost.password,
          }
        })
          .then(res => {
            console.log(res)
            // fetchPostList
            // store.dispatch('fetchPostList', deskData.deskPk)
            emit("post-created")
            
            state.value.myPost = {
              title: '',
              content: '',
              password: '',
              passwordConfirm: ''
            }
            // 모달창 끄기
            const createModal = document.querySelector('#createModal')
            let modal = Modal.getOrCreateInstance(createModal)
            modal.hide()
          })
          .catch(err => console.error(err))      
      
      } else {
        console.log('비밀번호가 다름!!!')
        const errorMessage = document.querySelector('#password-error')
        errorMessage.classList.remove('d-none')
      }
    }

    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
      console.log("모달창 닫기")
    }
    return {state, savePost, cancle}
  }
}
</script>
<style scoped>
  .modal-content{
    /* border-width: 2px;
    border-style: solid;
    border-radius: 1rem; */
    /* width:29rem;  */
    height: 670px;
  }
</style>