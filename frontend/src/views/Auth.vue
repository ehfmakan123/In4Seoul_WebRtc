<template>
  <div id="desk-login">
    <h3>안내데스크 로그인</h3>
    <input type="text" placeholder="아이디" v-model="deskLoginCredentials.id"><br/>
    <input type="password" placeholder="비밀번호" v-model="deskLoginCredentials.password"><br/>
    <button @click="deskLoginConfirm">확인</button>
    <button @click="deskLoginCancel">취소</button>
  </div>
  <hr>
  <div id="staff-login">
    <h3>상담사 로그인</h3>
    <input type="text" placeholder="아이디" v-model="staffLoginCredentials.id"><br/>
    <input type="password" placeholder="비밀번호" v-model="staffLoginCredentials.password"><br/>
    <button @click="staffLoginConfirm">확인</button>
    <button @click="staffLoginCancel">취소</button>
  </div>
  <hr>
  <div id="staff-signup">
    <h3>상담사 회원가입</h3>
    <input type="text" placeholder="아이디" v-model="staffSignupCredentials.id"><button @click="staffSignupIdCheck">중복확인</button><br/>
    <input type="password" placeholder="비밀번호" v-model="staffSignupCredentials.password"><br/>
    <input type="password" placeholder="비밀번호확인" v-model="staffSignupCredentials.passwordConfirm"><br/>
    <input type="text" placeholder="이름" v-model="staffSignupCredentials.name"><br/>
    <input type="text" placeholder="전화번호" v-model="staffSignupCredentials.phone"><br/>
    <input type="text" placeholder="이메일" v-model="staffSignupCredentials.email"><br/>
    <button @click="staffSignupConfirm">확인</button>
    <button @click="staffSignupCancel">취소</button>
  </div>  
</template>

<script>
// @ is an alias to /src
import { ref } from 'vue'

export default {
  name: 'Auth',
  components: {
  },
  setup() {

    const deskLoginCredentials = ref({ id: "", password: "" })
    const staffLoginCredentials = ref({ id: "", password: "" })
    const staffSignupCredentials = ref({ id: "", password: "", passwordConfirm: "", name: "", phone: "", email: "" })

    const deskLoginConfirm = () => {
      console.log("desk 로그인 확인버튼 클릭됨!")
      console.log(deskLoginCredentials.value.id)
      console.log(deskLoginCredentials.value.password)
    }
    const deskLoginCancel = () => {
      console.log("desk 로그인 취소버튼 클릭됨!")
    }
    const staffLoginConfirm = () => {
      console.log("staff 로그인 확인버튼 클릭됨!")
      console.log(staffLoginCredentials.value.id)
      console.log(staffLoginCredentials.value.password)
    }
    const staffLoginCancel = () => {
      console.log("staff 로그인 확인버튼 클릭됨!")
    }
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
      console.log("staff 회원가입 확인버튼 클릭됨!")
    }

    return { 
      deskLoginCredentials,
      staffLoginCredentials,
      staffSignupCredentials,
      deskLoginConfirm,
      deskLoginCancel,
      staffLoginConfirm,
      staffLoginCancel,
      staffSignupIdCheck,
      staffSignupConfirm,
      staffSignupCancel
    }
  }
}
</script>
