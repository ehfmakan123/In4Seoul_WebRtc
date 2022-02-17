<template>
  <div class="modal fade" id="desk-logout-modal" tabindex="-1" aria-labelledby="desk-logout-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content bd-blue-4 px-4 pt-3 pb-4">
        <div id="desk-logout-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <span><i class="bi bi-camera fs-2"></i></span><h5 class="modal-title ms-3 fs-5" id="desk-login-modal-label"><strong class="t-blue-4">안내데스크</strong> 로그아웃</h5>
        </div>
        <div class="modal-body">
          <div class="input-group">
            <span class="input-group-text bg-white border-white fw-bold" id="desk-login-password">비밀번호</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="desk-login-password" aria-describedby="desk-login-password" v-model="deskLogoutCredentials.password" @keypress.enter="deskLoginConfirm">
          </div>
          <div class="pt-3" style="height: 2rem;">
            <p id="password-error" v-show="state.showPasswordError" class="text-small t-red-2 me-auto ms-3">비밀번호가 일치하지 않습니다</p>
          </div>
          <div class="d-flex justify-content-center mt-4">
            <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" @click="deskLoginConfirm">로그아웃</button>
            <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal" @click="deskLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter} from 'vue-router'
import { useStore } from 'vuex';
import axios from 'axios'
import { Modal } from 'bootstrap'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AuthDeskLogoutModal',
  setup() {
    const deskLogoutCredentials = ref({ password: "" })
    const router = useRouter()
    const store = useStore()

    const state = ref({
      showPasswordError: false
    })

    const deskLoginConfirm = () => {
      console.log("desk 로그인 확인버튼 클릭됨!")
      console.log("deskLogoutCredentials: ",deskLogoutCredentials.value)

      const token = localStorage.getItem('token')
      const config = {
              Authorization: `Bearer ${token}`
            }
      // 로그인 axios 요청
      axios({
     
        method: 'post',
        url: `${SERVER_HOST}/desk/logout`,
        data: deskLogoutCredentials.value,
        headers: config
      })      
        .then(res => {
          console.log(res)
          // 로컬스토리지 삭제
          localStorage.clear()
          store.dispatch("logoutAction")

          // modal 닫는 부분
          const deskLoginModal = document.querySelector('#desk-logout-modal')
          let modal = Modal.getOrCreateInstance(deskLoginModal)
          modal.hide()
          
          router.push({ name: 'Auth' })
        })
        .catch(err => {
          console.log('desk 로그인 error발생!')
          console.log(err.response.data)
          const statusCode = err.response.data.statusCode
          if (statusCode === 403) {
            deskLogoutCredentials.value.password = ''
          }
          else if (statusCode === 401) {
            console.log("비밀번호 일치x");
            state.value.showPasswordError=true;
            deskLogoutCredentials.value.password = ''
          }
        })
    }
    
    const deskLoginCancel = () => {
      console.log("desk 로그인 취소버튼 클릭됨!")
      deskLogoutCredentials.value.password = ""
    }

    return {
      state,
      deskLogoutCredentials,
      deskLoginConfirm,
      deskLoginCancel,
    }
  }
}
</script>

<style scoped>
#desk-logout-modal .modal-content{
  border-width: 2px;
  border-style: solid;
  border-radius: 1rem;
}
#desk-logout-modal .input-group-text {
  width: 6rem;
}

#desk-logout-modal-header {
  height: 5rem;
}

.btn-yes-no {
  width:25%;
  border-radius:2rem;
}
</style>