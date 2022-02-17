<template>
  <div>
    <div class="modal fade" id="passwordModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content bd-blue-4 ms-3">
          <div class="modal-body p-4">
            <div class="input-group pt-1">
              <span class="input-group-text bg-white border-white fw-bold" id="post-password">Password</span>
              <input type="password" class="form-control bd-blue-3" v-model="state.passwordConfirm" placeholder="비밀번호를 입력해주세요">
            </div>
            
            <div class="d-flex justify-content-end pt-4">
              <span id="password-error" v-show="state.showPasswordError" class="t-red-2 text-small my-3 me-auto ms-3">비밀번호가 일치하지 않습니다</span>
              <button @click="confirm" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn">Confirm</button>
              <button @click="cancle" type="button" class="btn btn-outline-dark ms-3 rounded-btn" data-bs-dismiss="modal">Close</button>
            </div>
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

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'PostPasswordForm',
  components: {
  },
  props: ['postId'],
  setup(props) {
    
    const state = ref({
      passwordConfirm: '',
      showPasswordError: false
    })

    const confirm = () => {
      console.log("확인 버튼 클릭됨!")
      console.log('props.postId: ', props.postId)

      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
          
      axios({
        method: 'post',
        url: `${SERVER_HOST}/desk/posts/${props.postId}`,
        data: {
          password: state.value.passwordConfirm
        },
        headers: config
      })
        .then((res) => {
          console.log('비밀번호 일치함. 편집모달로 이동!')
          console.log(res)
      
          // passwordModal 끄기
          const passwordModal = document.querySelector('#passwordModal')
          let modal = Modal.getOrCreateInstance(passwordModal)
          modal.hide()
          
          // detailModal 끄기
          const detailModal = document.querySelector('#detailModal')
          modal = Modal.getOrCreateInstance(detailModal)
          modal.hide()

          // updateModal 켜기
          const updateModal = document.querySelector('#updateModal')
          modal = Modal.getOrCreateInstance(updateModal)
          modal.show()

          state.value.passwordConfirm = ''
        })
        .catch(err => {
          console.error(err)
          console.log('비밀번호가 다름!!!')
          state.value.showPasswordError = true
          // const errorMessage = document.querySelector('#password-error')
          // console.log(errorMessage)
          // errorMessage.classList.remove('d-none')
        })
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨")
      state.value.passwordConfirm = '',
      state.value.showPasswordError = false

      // 모달창 끄기
      const passwordModal = document.querySelector('#passwordModal')
      const modal = Modal.getOrCreateInstance(passwordModal)
      modal.hide()
    }
    return { state, confirm, cancle }
  }
}
</script>
<style scoped>
  .modal-content{
    border-width: 2px;
    border-style: solid;
    border-radius: 1rem;
    width:29rem; 
    height:10rem;
  }
</style>
