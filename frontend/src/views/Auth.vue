<template>
  <div id="auth-container">

    <auth-desk-login-modal/>
    <auth-staff-login-modal/>
    <auth-staff-signup-modal/>

    <div class="d-flex justify-content-center">
      <img src="../assets/logo.png" style="width: 13rem;"/>
      <div class="align-self-center ms-3">
        <h1 class="t-gray-4">In <span class="t-blue-4">4</span> Seoul</h1>
        <h2 class="mt-4 t-gray-4">서울시 화상 관광 안내 서비스</h2>
      </div>
    </div>


    <!--auth-button-container-->
    <div id="auth-button-container" class="container">
      <div class="row">
        <div id="desk-login" class="col-6 d-flex justify-content-center align-items-center" data-bs-toggle="modal" data-bs-target="#desk-login-modal"  @click="deskLoginClick">
          <i class="bi bi-camera" style="font-size:4rem"></i>
          <div class="ms-5">
            <p>안내데스크</p>
            <p class="mt-3">로그인</p>
          </div>
        </div>
        <div id="staff-login-signup" class="col-6">
          <div id="staff-login" class="d-flex justify-content-center align-items-center" data-bs-toggle="modal" data-bs-target="#staff-login-modal" @click="staffLoginClick">
            <i class="bi bi-headset" style="font-size:4rem;"></i>
            <div class="ms-5">
              <p>상담사</p>
              <p class="mt-3">로그인</p>
            </div>
          </div>
          <div id="staff-signup" class="d-flex justify-content-center align-items-center mt-2" data-bs-toggle="modal" data-bs-target="#staff-signup-modal" @click="staffSignupClick">
              <i class="bi bi-pencil-square" style="font-size:4rem;"></i>
              <div class="ms-5">
                <p>상담사</p>
                <p class="mt-3">회원가입</p>
              </div>
          </div>
        </div>
      </div>
    </div><!--auth-button-container 끝-->

    <!-- <div>
      <hr>
      <p style="font-weight: bold; font-size: 2.5rem;" class="my-4 bg-blue-1">임시 이동 버튼입니다</p>
      <button @click="moveToDesk" type="button" class="btn btn-info me-4">Desk 메인페이지로 이동</button>
      <button @click="moveToStaff" type="button" class="btn btn-info me-4">상담사 메인페이지로 이동</button>
      <button @click="moveToAdminLogin" type="button" class="btn btn-info me-4">Admin 로그인 페이지로 이동</button>
      <button @click="moveToAdminStaff" type="button" class="btn btn-info me-4">Admin 상담사관리 페이지로 이동</button>
    </div> -->

  </div>
</template>

<script>
// @ is an alias to /src
// import { ref } from 'vue'
import AuthDeskLoginModal from '@/components/auth/AuthDeskLoginModal.vue'
import AuthStaffLoginModal from '@/components/auth/AuthStaffLoginModal.vue'
import AuthStaffSignupModal from '@/components/auth/AuthStaffSignupModal.vue'
import { useRouter } from 'vue-router'

export default {
  name: 'Auth',
  components: {
    AuthDeskLoginModal,
    AuthStaffLoginModal,
    AuthStaffSignupModal
  },
  setup() {
    const router = useRouter()
    
    const deskLoginClick = () => {
      console.log("desk 로그인 버튼 클릭됨!")
    }

    const staffLoginClick = () => {
      console.log("staff 로그인 버튼 클릭됨!")
    }

    const staffSignupClick = () => {
      console.log("staff 회원가입 버튼 클릭됨!")
    }

    const moveToDesk = () => {
      console.log("데스크 메인페이지 이동 버튼 클릭됨!")
      router.push({ name: 'DeskHome' })
    }
    
    const moveToStaff = () => {
      console.log("상담사 메인페이지 이동 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const moveToAdminLogin = () => {
      console.log("관리자 로그인 페이지 이동 버튼 클릭됨!")
      router.push({ name: 'AdminAuth' })
    }

    const moveToAdminStaff = () => {
      console.log("관리자 상담사관리 페이지 이동 버튼 클릭됨!")
      router.push({ name: 'AdminStaff' })
    }

    return { 
      deskLoginClick,
      staffLoginClick,
      staffSignupClick,
      moveToDesk,
      moveToStaff,
      moveToAdminLogin,
      moveToAdminStaff
    }
  },
  created(){
    const router = useRouter()
    console.log("true", (localStorage.getItem('deskData') != null));
    if(localStorage.getItem('deskData') != null){
      router.push({ name: 'DeskHome' })
    }else if(localStorage.getItem('staffData') !=null ){
      router.push({ name: 'StaffHome' })
    }
  }
}
</script>

<style scoped>
#auth-container {
  text-align: center;
  margin-top: 10vh;
}

#auth-container h1 {
  font-weight: bold;
  font-size: 5rem;
}

#auth-container h2 {
  font-weight: bold;
  font-size: 2.5rem;
}

#auth-button-container {
  margin-top: 7vh;
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
