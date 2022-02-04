<template>
  <div id="auth-container">
    <h1>서울시 여행 안내 데스크</h1>
    <h1>Seoul Travel Information Service</h1>

    <!--auth-button-container-->
    <div id="auth-button-container" class="container">
      <div class="row">
        <div id="desk-login" class="col-6 d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#desk-login-modal"  @click="deskLoginClick">
          <div class="row">
            <div class="col-3 d-flex flex-column justify-content-center">
              <div>
                <i class="bi bi-camera" style="font-size:4rem"></i>
              </div>
            </div>
            <div class="col-9 d-flex flex-column justify-content-center">
              <p>안내데스크</p>
              <p>로그인</p>
            </div>
          </div>
        </div>
        <div class="col-6">
          <div id="staff-login" class="d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#staff-login-modal" @click="staffLoginClick">
            <div class="row">
              <div class="col-4 d-flex flex-column justify-content-center">
                <i class="bi bi-headset" style="font-size:4rem;"></i>
              </div>
              <div class="col-8 d-flex flex-column justify-content-center">
                <p>상담사</p>
                <p>로그인</p>
              </div>
            </div>
          </div>
          <div id="staff-signup" class="d-flex justify-content-center mt-2" data-bs-toggle="modal" data-bs-target="#staff-signup-modal" @click="staffSignupClick">
            <div class="row">
              <div class="col-4 d-flex flex-column justify-content-center">
                <i class="bi bi-pencil-square" style="font-size:4rem;"></i>
              </div>
              <div class="col-8 d-flex flex-column justify-content-center">
                <p>상담사</p>
                <p>회원가입</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--auth-button-container 끝-->
  </div>

  <!-- Desk Login modal -->
  <div class="modal fade" id="desk-login-modal" tabindex="-1" aria-labelledby="desk-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-camera fs-4"></i></span><h5 class="modal-title ms-2" id="desk-login-modal-label">안내데스크 로그인</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="deskLoginCancel"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="desk-id" aria-describedby="basic-addon1" v-model="deskLoginCredentials.id">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">비밀번호</span>
            <input type="password" class="form-control" placeholder="" aria-label="desk-password" aria-describedby="basic-addon1" v-model="deskLoginCredentials.password">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="deskLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="deskLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Desk Login modal 끝-->

  <!-- Staff Login modal -->
  <div class="modal fade" id="staff-login-modal" tabindex="-1" aria-labelledby="staff-login-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-headset fs-4"></i></span><h5 class="modal-title ms-2" id="staff-login-modal-label">상담사 로그인</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-id" aria-describedby="basic-addon1" v-model="staffLoginCredentials.id">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">비밀번호</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-password" aria-describedby="basic-addon1" v-model="staffLoginCredentials.password">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="staffLoginConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="staffLoginCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Staff Login modal 끝-->

  <!-- Staff Signup modal -->
  <div class="modal fade" id="staff-signup-modal" tabindex="-1" aria-labelledby="staff-signup-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <span><i class="bi bi-pencil-square"></i></span><h5 class="modal-title ms-2" id="staff-signup-modal-label">상담사 회원가입</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="deskLoginCancel"></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">아이디</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-id" aria-describedby="basic-addon1" v-model="staffSignupCredentials.id">
            <button class="btn btn-outline-primary" type="button" id="button-addon2" @click="staffSignupIdCheck">중복확인</button>
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">비밀번호</span>
            <input type="password" class="form-control" placeholder="" aria-label="staff-password" aria-describedby="basic-addon1" v-model="staffSignupCredentials.password">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">비밀번호확인</span>
            <input type="password" class="form-control" placeholder="" aria-label="staff-password-confirm" aria-describedby="basic-addon1" v-model="staffSignupCredentials.passwordConfirm">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">이름</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-name" aria-describedby="basic-addon1" v-model="staffSignupCredentials.name">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">전화번호</span>
            <input type="text" class="form-control" placeholder="" aria-label="staff-phone" aria-describedby="basic-addon1" v-model="staffSignupCredentials.phone">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">이메일</span>
            <input type="email" class="form-control" placeholder="" aria-label="staff-phone" aria-describedby="basic-addon1" v-model="staffSignupCredentials.email">
          </div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary" @click="staffSignupConfirm">로그인</button>
            <button type="button" class="btn btn-outline-secondary ms-2" data-bs-dismiss="modal" @click="staffSignupCancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div><!-- Staff Signup modal 끝-->
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

    const deskLoginClick = () => {
      console.log("desk 로그인 버튼 클릭됨!")
    }

    const staffLoginClick = () => {
      console.log("staff 로그인 버튼 클릭됨!")
    }

    const staffSignupClick = () => {
      console.log("staff 회원가입 버튼 클릭됨!")
    }

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
      deskLoginCredentials,
      staffLoginCredentials,
      staffSignupCredentials,
      deskLoginClick,
      staffLoginClick,
      staffSignupClick,
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

<style scoped>
h1 {
  font-weight: bold;
  font-size: 50px;
}

#auth-container {
  margin-top: 15vh;
}

#auth-button-container {
  margin-top: 100px;
}

#auth-button-container p {
  font-size: 40px;
  margin: 0px;
}

#desk-login {
  /* background-color: #007DC3; */
  /* border-color: #007DC3; */
  border: 1px solid #007DC3;
  border-radius: 20px;
  user-select:none;
  height: 36vh;
}

#desk-login:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

#desk-login-modal .modal-content{
  border: 1px solid #007DC3;
}

#desk-login-modal .input-group-text {
  width: 6rem;
}

#staff-login {
  /* background-color: #3BA2DB; */
  height: 50%;
  border: 1px solid #0d6efd;
  border-radius: 20px;
  height: 17.8vh;
}

#staff-login:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

#staff-login-modal .modal-content{
  border: 1px solid #007DC3;
}

#staff-login-modal .input-group-text {
  width: 6rem;
}

#staff-signup {
  /* background-color: #3BA2DB; */
  height: 50%;
  border: 1px solid #0d6efd;
  border-radius: 20px;
  height: 17.5vh;
}

#staff-signup:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

#staff-signup-modal .modal-content{
  border: 1px solid #007DC3;
}

#staff-signup-modal .input-group-text {
  width: 7.5rem;
}
</style>
