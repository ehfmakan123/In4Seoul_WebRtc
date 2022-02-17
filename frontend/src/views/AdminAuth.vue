<template>
  <div id="admin-login-container" class="d-flex justify-content-center bg-gray-4">
      <div id="admin-login" class="bd-blue-5 p-3 bg-gray-2">
        <div id="admin-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
          <h3 class="fs-4"><strong class="">관리자</strong> 로그인</h3>
        </div>        
        <div class="input-group mt-3">
          <span class="input-group-text" id="admin-login-id">아이디</span>
          <input type="text" class="form-control" placeholder="" aria-label="admin-login-id" aria-describedby="admin-login-id" v-model="adminLoginCredentials.userId" autofocus>
        </div>
        <div class="input-group mt-3">
          <span class="input-group-text" id="admin-login-password">비밀번호</span>
          <input 
            type="password"
            class="form-control"
            placeholder=""
            aria-label="admin-login-password"
            aria-describedby="admin-login-password"
            v-model="adminLoginCredentials.password"
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
import { useRouter } from 'vue-router'
import { useStore } from 'vuex';
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AdminAuth',
  components: {
  },
  setup() {
    const adminLoginCredentials = ref({ userId: "", password: ""})
    const router = useRouter()
    const store = useStore()

    const adminLoginConfirm = () => {
      console.log("admin 로그인 확인버튼 클릭됨!")
      console.log(adminLoginCredentials.value)

      axios({
        method: 'post',
        url: `${SERVER_HOST}/admin/login`,
        data: adminLoginCredentials.value
      })      
        .then(res => {
          console.log(res)
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('adminData', JSON.stringify(res.data))

          router.push({ name: 'AdminStaff' })
          store.dispatch("admin_login")

          // modal 닫는 부분
        })
        .catch(err => {
          console.log('admin 로그인 error발생!')
          console.log(err.response.data)
          // const statusCode = err.response.data.statusCode
          // if (statusCode === 401) {
          //   adminLoginCredentials.value.password = ''
          // }
          // else if (statusCode === 404) {
          //   adminLoginCredentials.value.userId = ''
          //   adminLoginCredentials.value.password = ''
          // }
        })
    }

    return {
      adminLoginCredentials,
      adminLoginConfirm,
    }
  },
  created(){
    const router = useRouter()
    if(localStorage.getItem('adminData') != null){
      router.push({ name: 'AdminStaff' })
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