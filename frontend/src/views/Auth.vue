<template>
  <div id="auth-container">

    <AuthDeskLoginModal/>
    <AuthStaffLoginModal/>
    <AuthStaffSignupModal/>

    <h1>서울시 여행 안내 데스크</h1>
    <h1 class="mt-3">Seoul Travel Information Service</h1>

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
              <p class="mt-3">로그인</p>
            </div>
          </div>
        </div>
        <div class="col-6">
          <div id="staff-login" class="d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#staff-login-modal" @click="staffLoginClick">
            <div class="row">
              <div class="col-3 d-flex flex-column justify-content-center">
                <i class="bi bi-headset" style="font-size:4rem;"></i>
              </div>
              <div class="col-9 d-flex flex-column justify-content-center">
                <p>상담사</p>
                <p class="mt-3">로그인</p>
              </div>
            </div>
          </div>
          <div id="staff-signup" class="d-flex justify-content-center mt-2" data-bs-toggle="modal" data-bs-target="#staff-signup-modal" @click="staffSignupClick">
            <div class="row">
              <div class="col-3 d-flex flex-column justify-content-center">
                <i class="bi bi-pencil-square" style="font-size:4rem;"></i>
              </div>
              <div class="col-9 d-flex flex-column justify-content-center">
                <p>상담사</p>
                <p class="mt-3">회원가입</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--auth-button-container 끝-->
  </div>
</template>

<script>
// @ is an alias to /src
// import { ref } from 'vue'
import AuthDeskLoginModal from '@/components/auth/AuthDeskLoginModal.vue'
import AuthStaffLoginModal from '@/components/auth/AuthStaffLoginModal.vue'
import AuthStaffSignupModal from '@/components/auth/AuthStaffSignupModal.vue'


export default {
  name: 'Auth',
  components: {
    AuthDeskLoginModal,
    AuthStaffLoginModal,
    AuthStaffSignupModal
  },
  setup() {
    
    const deskLoginClick = () => {
      console.log("desk 로그인 버튼 클릭됨!")
    }

    const staffLoginClick = () => {
      console.log("staff 로그인 버튼 클릭됨!")
    }

    const staffSignupClick = () => {
      console.log("staff 회원가입 버튼 클릭됨!")
    }

    return { 
      deskLoginClick,
      staffLoginClick,
      staffSignupClick,
    }
  }
}
</script>

<style scoped>
#auth-container {
  text-align: center;
  margin-top: 15vh;
}

#auth-container h1 {
  font-weight: bold;
  font-size: 50px;
}


#auth-button-container {
  margin-top: 10vh;
}

#auth-button-container p {
  font-size: 40px;
  margin: 0px;
}

#desk-login {
  border: 1px solid #007DC3;
  border-radius: 20px;
  height: 36vh;
  user-select:none;
}

#desk-login:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

#staff-login {
  border: 1px solid #0d6efd;
  border-radius: 20px;
  height: 17.8vh;
  user-select:none;
}

#staff-login:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

#staff-signup {
  border: 1px solid #0d6efd;
  border-radius: 20px;
  height: 17.5vh;
  user-select:none;
}

#staff-signup:hover {
  color: white;
  background-color: #0d6efd;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}
</style>
