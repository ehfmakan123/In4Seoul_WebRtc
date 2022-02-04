<template>
  <div class="modal fade" id="staff-signup-modal" tabindex="-1" aria-labelledby="staff-signup-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-pencil-square"></i></span><h5 class="modal-title ms-2" id="staff-signup-modal-label">상담사 회원가입</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="deskLoginCancel"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-signup-id">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-signup-id" aria-describedby="staff-signup-id" v-model="staffSignupCredentials.id">
            <button class="btn btn-outline-primary" type="button" id="staff-signup-id-check" @click="staffSignupIdCheck">중복확인</button>
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-signup-password">비밀번호</span>
            <input type="password" class="form-control" placeholder="" aria-label="staff-signup-password" aria-describedby="staff-signup-password" v-model="staffSignupCredentials.password">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-signup-password-confirm">비밀번호확인</span>
            <input type="password" class="form-control" placeholder="" aria-label="staff-signup-password-confirm" aria-describedby="staff-signup-password-confirm" v-model="staffSignupCredentials.passwordConfirm">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-signup-name">이름</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-signup-name" aria-describedby="staff-signup-name" v-model="staffSignupCredentials.name">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-signup-phone">전화번호</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-signup-phone" aria-describedby="staff-signup-phone" v-model="staffSignupCredentials.phone">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text bg-white" id="staff-signup-email" style="border-color: #FFFFFF;">이메일</span>
            <input type="email" class="form-control ms-1" placeholder="" aria-label="staff-signup-email" aria-describedby="staff-signup-email" v-model="staffSignupCredentials.email" style="border-color: #0d6efd;">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="staffSignupConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="staffSignupCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'AuthStaffSignupModal',
  setup() {
    const staffSignupCredentials = ref({ id: "", password: "", passwordConfirm: "", name: "", phone: "", email: "" })

    const staffSignupIdCheck = () => {
      console.log("staff 회원가입 중복확인버튼 클릭됨!")
      console.log(staffSignupCredentials.value.id)
    }
    const staffSignupConfirm = () => {
      console.log("staff 회원가입 확인버튼 클릭됨!")
      console.log(staffSignupCredentials.value.id)
      console.log(staffSignupCredentials.value.password)
      console.log(staffSignupCredentials.value.passwordConfirm)
      console.log(staffSignupCredentials.value.name)
      console.log(staffSignupCredentials.value.phone)
      console.log(staffSignupCredentials.value.email)
    }
    const staffSignupCancel = () => {
      staffSignupCredentials.value.id = ""
      staffSignupCredentials.value.password = ""
      staffSignupCredentials.value.passwordConfirm = ""
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
  border: 1px solid #007DC3;
}

#staff-signup-modal .input-group-text {
  width: 7.5rem;
}

</style>