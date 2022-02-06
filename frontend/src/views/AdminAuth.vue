<template>
  <div id="admin-login-container" class="d-flex justify-content-center bg-gray-4">
      <div id="admin-login" class="bd-blue-5 p-3 bg-gray-2">
        <div id="admin-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <h3 class="fs-4"><strong class="">관리자</strong> 로그인</h3>
        </div>        
        <div class="input-group mt-3">
          <span class="input-group-text" id="admin-login-id">아이디</span>
          <input type="text" class="form-control" placeholder="" aria-label="admin-login-id" aria-describedby="admin-login-id" v-model="adminCredentials.id" autofocus>
        </div>
        <div class="input-group mt-3">
          <span class="input-group-text" id="admin-login-password">비밀번호</span>
          <input 
            type="password"
            class="form-control"
            placeholder=""
            aria-label="admin-login-password"
            aria-describedby="admin-login-password"
            v-model="adminCredentials.password"
            @keypress.enter="adminLoginConfirm"
            >
        </div>
        <div class="d-flex justify-content-end mt-4">
          <button class="btn btn btn-dark" @click="adminLoginConfirm">로그인</button>
        </div>
      </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { ref } from 'vue'

export default {
  name: 'AdminAuth',
  components: {
  },
  setup() {

    const adminCredentials = ref({ id: "", password: ""})

    const adminLoginConfirm = () => {
      console.log("admin 로그인 확인버튼 클릭됨!")
      console.log("아이디: ",adminCredentials.value.id)
      console.log("비밀번호: ",adminCredentials.value.password)
    }

    return {
      adminCredentials,
      adminLoginConfirm,
    }
  }
}
</script>

<style scoped>
#admin-login-container {
  height: 100vh;
}

#admin-login {
  /* text-align: center; */
  margin-top: 30vh;
  border-width: 1px;
  border-style: solid;
  border-radius: 10px;
  width: 400px;
  height: 300px;
}

.input-group-text {
  width: 6rem;
}

#admin-login-modal-header {
  height: 4rem;
}

</style>