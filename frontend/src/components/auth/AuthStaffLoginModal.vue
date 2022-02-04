<template>
  <div class="modal fade" id="staff-login-modal" tabindex="-1" aria-labelledby="staff-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-headset fs-4"></i></span><h5 class="modal-title ms-2" id="staff-login-modal-label">상담사 로그인</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-login-id">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-login-id" aria-describedby="staff-login-id" v-model="staffLoginCredentials.id">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="staff-login-password">비밀번호</span>
            <input type="password" class="form-control" placeholder="" aria-label="staff-login-password" aria-describedby="staff-login-password" v-model="staffLoginCredentials.password">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="staffLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="staffLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Staff Login modal 끝-->  
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'AuthStaffLoginModal',
  setup() {
    const staffLoginCredentials = ref({ id: "", password: "" })
    const staffLoginConfirm = () => {
      console.log("staff 로그인 확인버튼 클릭됨!")
      console.log(staffLoginCredentials.value.id)
      console.log(staffLoginCredentials.value.password)
    }
    const staffLoginCancel = () => {
      console.log("staff 로그인 확인버튼 클릭됨!")
      staffLoginCredentials.value.id = ""
      staffLoginCredentials.value.password = ""
    }

    return {
      staffLoginCredentials,
      staffLoginConfirm,
      staffLoginCancel,
    }
  }
}
</script>

<style scoped>
#staff-login-modal .modal-content{
  border: 1px solid #007DC3;
}

#staff-login-modal .input-group-text {
  width: 6rem;
}
</style>