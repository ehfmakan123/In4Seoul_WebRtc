<template>
  <div class="modal fade" id="desk-login-modal" tabindex="-1" aria-labelledby="desk-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-camera fs-4"></i></span><h5 class="modal-title ms-2" id="desk-login-modal-label">안내데스크 로그인</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="deskLoginCancel"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="desk-login-id">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="desk-login-id" aria-describedby="desk-login-id" v-model="deskLoginCredentials.id">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="desk-login-password">비밀번호</span>
            <input type="password" class="form-control" placeholder="" aria-label="desk-login-password" aria-describedby="desk-login-password" v-model="deskLoginCredentials.password">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="deskLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="deskLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Desk Login modal 끝-->
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'AuthDeskLoginModal',
  setup() {
    const deskLoginCredentials = ref({ id: "", password: "" })

    const deskLoginConfirm = () => {
      console.log("desk 로그인 확인버튼 클릭됨!")
      console.log(deskLoginCredentials.value.id)
      console.log(deskLoginCredentials.value.password)
    }
    const deskLoginCancel = () => {
      console.log("desk 로그인 취소버튼 클릭됨!")
      deskLoginCredentials.value.id = ""
      deskLoginCredentials.value.password = ""
    }

    return {
      deskLoginCredentials,
      deskLoginConfirm,
      deskLoginCancel,
    }
  }
}
</script>

<style scoped>
#desk-login-modal .modal-content{
  border: 1px solid #007DC3;
}
#desk-login-modal .input-group-text {
  width: 6rem;
}
</style>