<template>
  <div class="modal fade" id="desk-login-modal" tabindex="-1" aria-labelledby="desk-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content bd-blue-4 px-4 pt-3 pb-4">
        <div id="desk-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <span><i class="bi bi-camera fs-2"></i></span><h5 class="modal-title ms-3 fs-5" id="desk-login-modal-label"><strong class="t-blue-4">안내데스크</strong> 로그인</h5>
        </div>
        <div class="modal-body">
          <div class="input-group">
            <span class="input-group-text bg-white border-white fw-bold" id="desk-login-id">아이디</span>
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="desk-login-id" aria-describedby="desk-login-id" v-model="deskLoginCredentials.userId">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text bg-white border-white fw-bold" id="desk-login-password">비밀번호</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="desk-login-password" aria-describedby="desk-login-password" v-model="deskLoginCredentials.password" @keypress.enter="deskLoginConfirm">
          </div>
          <div class="pt-3" style="height: 3rem;">
            <p id="login-error" v-show="state.showError" class="modal-error-message t-red-2">아이디 또는 비밀번호가 일치하지 않습니다.</p>
          </div>
          <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" @click="deskLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal" @click="deskLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Desk Login modal 끝-->
</template>

<script>
import { ref } from 'vue'
import { useRouter} from 'vue-router'
import { useStore } from 'vuex';
import axios from 'axios'
import { Modal } from 'bootstrap'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AuthDeskLoginModal',
  setup() {
    const deskLoginCredentials = ref({ userId: "", password: "" })
    const router = useRouter()
    const store = useStore()

    const state = ref({
      showError: false,
    })

    const deskLoginConfirm = () => {
      console.log("desk 로그인 확인버튼 클릭됨!")
      console.log(deskLoginCredentials.value)

      // 로그인 axios 요청
      axios({
        method: 'post',
        url: `${SERVER_HOST}/desk/login`,
        data: deskLoginCredentials.value
      })      
        .then(res => {
          console.log(res)
          state.value.showError = false
          // jwt토큰 저장 & 스토어 갱신
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('deskData', JSON.stringify(res.data))
          store.dispatch("desk_login")

          // modal 닫는 부분
          const deskLoginModal = document.querySelector('#desk-login-modal')
          let modal = Modal.getOrCreateInstance(deskLoginModal)
          modal.hide()
          
          router.push({ name: 'DeskHome' })
        })
        .catch(err => {
          console.log('desk 로그인 error발생!')
          console.log(err.response.data)
          state.value.showError = true
          // const statusCode = err.response.data.statusCode
          // if (statusCode === 401) {
          //   deskLoginCredentials.value.password = ''
          // }
          // else if (statusCode === 404) {
          //   deskLoginCredentials.value.userId = ''
          //   deskLoginCredentials.value.password = ''
          // }
        })
    }
    
    const deskLoginCancel = () => {
      console.log("desk 로그인 취소버튼 클릭됨!")
      deskLoginCredentials.value.userId = ""
      deskLoginCredentials.value.password = ""
      state.value.showError = false
    }

    return {
      state,
      deskLoginCredentials,
      deskLoginConfirm,
      deskLoginCancel,
    }
  }
}
</script>

<style scoped>
#desk-login-modal .modal-content{
  border-width: 2px;
  border-style: solid;
  border-radius: 1rem;
}
#desk-login-modal .input-group-text {
  width: 6rem;
}

#desk-login-modal-header {
  height: 4rem;
}

.btn-yes-no {
  width:25%;
  border-radius:2rem;
}

.modal-error-message {
  font-size: 0.85em;
}
</style>