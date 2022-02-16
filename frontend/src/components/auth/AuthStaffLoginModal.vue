<template>
  <div class="modal fade" id="staff-login-modal" tabindex="-1" aria-labelledby="staff-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content bd-blue-4 px-4 pt-3 pb-4">
        <div id="staff-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <span><i class="bi bi-headset fs-2"></i></span><h5 class="modal-title ms-3 fs-5" id="staff-login-modal-label"><strong class="t-blue-3">상담사</strong> 로그인</h5>
        </div>
        <div class="modal-body">
          <div class="input-group">
            <span class="input-group-text bg-white border-white fw-bold" id="staff-login-id">아이디</span>
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="staff-login-id" aria-describedby="staff-login-id" v-model="staffLoginCredentials.userId">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text bg-white border-white fw-bold" id="staff-login-password">비밀번호</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="staff-login-password" aria-describedby="staff-login-password" v-model="staffLoginCredentials.password" @keypress.enter="staffLoginConfirm">
          </div>
          <div class="pt-3" style="height: 3rem;">
            <p id="login-error" v-show="state.showError" class="modal-error-message t-red-2" >아이디 또는 비밀번호가 일치하지 않습니다.</p>
            <p id="login-error" v-show="state.showError2" class="modal-error-message t-red-2" >아직 승인되지 않은 계정입니다.</p>
          </div>
          <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" @click="staffLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal" @click="staffLoginCancel">취소</button>
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

import firebase from 'firebase/app'
import 'firebase/messaging'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AuthStaffLoginModal',

  setup() {
    const messaging = firebase.messaging()
    const staffLoginCredentials = ref({ userId: "", password: "" })
    const router = useRouter()
    const store = useStore()

    const state = ref({
      showError: false,
       showError2: false,
    })

    const staffLoginConfirm = () => {
      console.log("staff 로그인 확인버튼 클릭됨!")

      axios({
        method: 'post',
        url: `${SERVER_HOST}/staff/login`,
        data: staffLoginCredentials.value
      })      
        .then(res => {
          // console.log(res)
          state.value.showError = false
          // jwt토큰 저장 & 스토어 갱신
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('staffData', JSON.stringify(res.data))
          const jwtToken = localStorage.getItem('token')
          store.dispatch("staff_login")
          console.log("상담사 로그인후 store 확인: ", store.state.isStaff)
        
          // modal 닫는 부분
          const staffLoginModal = document.querySelector('#staff-login-modal')
          let modal = Modal.getOrCreateInstance(staffLoginModal)
          modal.hide()

          // fcm 토큰 가져오기 -------------- 시작
          messaging.getToken({vapidKey: "BK_qGmvUaOH-PiEnOcd5Or0eLaUIcLsucSVPOC8mySPxp1MEAroR9K6btphRoGo3Q7OLYd9iFT_O0u-8OdSSxAU"})
            .then((res) => {
              // console.log(res)
              localStorage.setItem('fcmtoken', res)
              const fcmToken = localStorage.getItem('fcmtoken')
              console.log('fcmToken: ',fcmToken)

              // 토큰을 받아오면 토큰값 서버에 보내기
              axios({
                method: 'post',
                url: `${SERVER_HOST}/staff/fcmtoken` ,
                headers: {
                  Authorization: `Bearer ${jwtToken}`
                },
                data: {
                  token: fcmToken
                }
              })
                .then((res) => {
                  console.log('fcm토큰 저장 res: ',res)
                })
                .catch((err) => console.log(err) )
            })
            .catch((err) => console.log(err))
            // fcm 토큰 가져오기 -------------- 끝

          router.push({ name: 'StaffHome' })
        })
        .catch(err => {
          console.log('staff 로그인 error발생!')
          console.log(err.response.data)
         
         const statusCode = err.response.data.statusCode
         if (statusCode === 401 || statusCode===404) {
             staffLoginCredentials.value.password = ''
             state.value.showError = true
             state.value.showError2 = false
           }
           else if (statusCode === 403) {
                 state.value.showError = false
             state.value.showError2 = true
             staffLoginCredentials.value.userId = ''
             staffLoginCredentials.value.password = ''
           }
        })
    }
    
    const staffLoginCancel = () => {
      console.log("staff 로그인 취소버튼 클릭됨!")
      staffLoginCredentials.value.userId = ""
      staffLoginCredentials.value.password = ""
      state.value.showError = false
    }

    return {
      state,
      staffLoginCredentials,
      staffLoginConfirm,
      staffLoginCancel,
    }
  }
}
</script>

<style scoped>
#staff-login-modal .modal-content{
  border-width: 2px;
  border-style: solid;
  border-radius: 1rem;
}

#staff-login-modal .input-group-text {
  width: 6rem;
}

#staff-login-modal-header {
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