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
            <input type="text" class="form-control bd-blue-3" placeholder="" aria-label="staff-login-id" aria-describedby="staff-login-id" v-model="staffLoginCredentials.id">
          </div>
          <div class="input-group mt-4">
            <span class="input-group-text bg-white border-white fw-bold" id="staff-login-password">비밀번호</span>
            <input type="password" class="form-control bd-blue-3" placeholder="" aria-label="staff-login-password" aria-describedby="staff-login-password" v-model="staffLoginCredentials.password">
          </div>
          <div class="d-flex justify-content-center mt-5">
            <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" @click="staffLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal" @click="staffLoginCancel">취소</button>
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
</style>