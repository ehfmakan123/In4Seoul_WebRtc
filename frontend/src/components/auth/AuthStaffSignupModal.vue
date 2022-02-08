<template>
  <div class="modal fade" id="staff-signup-modal" tabindex="-1" aria-labelledby="staff-signup-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content bd-blue-4 px-4 pt-3 pb-4">
        <div id="staff-signup-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <span><i class="bi bi-pencil-square fs-2"></i></span><h5 class="modal-title ms-2 ms-3 fs-5" id="staff-signup-modal-label"><strong class="t-blue-4">상담사</strong> 회원가입</h5>
        </div>
        <div class="modal-body">
          <div class="input-group">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-id">아이디</span>
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-id" aria-describedby="staff-signup-id" v-model="staffSignupCredentials.userId">
            <button class="btn btn-outline-primary bd-blue-3 t-blue-3 ms-3" type="button" id="staff-signup-id-check" @click="staffSignupIdCheck" style="width:25%; border-radius:2rem;">중복확인</button>
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-password">비밀번호</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-password" aria-describedby="staff-signup-password" v-model="staffSignupCredentials.password">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-password-confirm">비밀번호확인</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-password-confirm" aria-describedby="staff-signup-password-confirm" v-model="staffSignupCredentials.passwordConfirmation">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-name">이름</span>
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-name" aria-describedby="staff-signup-name" v-model="staffSignupCredentials.name">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-phone">전화번호</span>
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-phone" aria-describedby="staff-signup-phone" v-model="staffSignupCredentials.phone">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text staff-signup-input-label" id="staff-signup-email" style="border-color: #FFFFFF;">이메일</span>
            <input type="email" class="form-control bd-blue-3" placeholder="" aria-label="staff-signup-email" aria-describedby="staff-signup-email" v-model="staffSignupCredentials.email">
          </div>
          <div class="d-flex justify-content-center mt-5">
            <button type="button" class="btn btn-outline-primary bd-blue-4 t-blue-3 btn-yes-no" @click="staffSignupConfirm">로그인</button>
            <button type="button" class="btn btn-outline-dark btn-yes-no ms-5" data-bs-dismiss="modal" @click="staffSignupCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter} from 'vue-router'
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AuthStaffSignupModal',
  setup() {
    const staffSignupCredentials = ref({ userId: "", password: "", passwordConfirmation: "", name: "", phone: "", email: "" })
    const router = useRouter()

    const staffSignupIdCheck = () => {
      console.log("staff 회원가입 중복확인버튼 클릭됨!")
      console.log(`${SERVER_HOST}/staff/${staffSignupCredentials.value.userId}`)
      axios({
        method: 'get',
        url: `${SERVER_HOST}/staff/${staffSignupCredentials.value.userId}`
      })
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err.response)
        })
    }

    const staffSignupConfirm = () => {
      console.log("staff 회원가입 확인버튼 클릭됨!")
      axios({
        method: 'post',
        url: `${SERVER_HOST}/staff/signup`,
        data: staffSignupCredentials.value
      })      
        .then(res => {
          console.log(res)
          // modal 닫는 부분
          const staffSignupModal = document.querySelector('#staff-signup-modal')
          staffSignupModal.classList.remove("in")
          document.querySelector(".modal-backdrop").remove()
          staffSignupModal.style.display = "none"
          
          router.go()
        })
        .catch(err => {
          console.log('staff 로그인 error발생!')
          console.log(err.response.data)
        })
    }

    const staffSignupCancel = () => {
      staffSignupCredentials.value.userId = ""
      staffSignupCredentials.value.password = ""
      staffSignupCredentials.value.passwordConfirmation = ""
      staffSignupCredentials.value.name = ""
      staffSignupCredentials.value.phone = ""
      staffSignupCredentials.value.email = ""
    }
    
    return { 
      staffSignupCredentials,
      staffSignupIdCheck,
      staffSignupConfirm,
      staffSignupCancel
    }
  }
}
</script>

<style scoped>
#staff-signup-modal .modal-content{
  border-width: 2px;
  border-style: solid;
  border-radius: 1rem;
}

#staff-signup-modal .input-group-text {
  width: 7.5rem;
}

#staff-signup-modal-header {
  height: 4rem;
}

.staff-signup-input-label {
  background-color: white;
  border-color: white;
  font-weight: bold;
}

.btn-yes-no {
  width:25%;
  border-radius:2rem;
}

</style>