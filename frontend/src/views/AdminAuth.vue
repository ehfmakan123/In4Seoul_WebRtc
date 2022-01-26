<template>
  <div id="admin-login" class="modal">
    <h3>관리자 로그인</h3>
    <input type="text" placeholder="아이디" v-model="adminCredentials.id"><br/>
    <input type="password" placeholder="비밀번호" v-model="adminCredentials.password"><br/>
    <button @click="adminLoginConfirm">확인</button>
    <button @click="adminLoginCancel">취소</button>
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
    const adminLoginCancel = () => {
      console.log("admin 로그인 취소버튼 클릭됨!")
    }

    return {
      adminCredentials,
      adminLoginConfirm,
      adminLoginCancel
    }
  }
}
</script>
