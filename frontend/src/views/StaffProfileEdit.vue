<template>
  <div>
    <div class="staff-side-bar">
      <button @click="moveToStaffHome">상담기록</button> <tb/>
      <button @click="clickAlarm">상담알림</button> <tb/>
      <button @click="moveToStaffProfile">프로필정보</button> <tb/>   
      <button @click="logout">logout</button>
    </div>
    <div class="staff-profile">
      <h2>프로필 정보>프로필조회</h2>
      <hr>
      <p>유저아이디: {{ testObj.staffId }}</p>
      <span>이름: </span><input type="text" title="이름" v-model="testObj.userName"><br/>
      <span>휴대폰번호: </span><input type="text" v-model="testObj.phone"><br/>
      <span>이메일: </span><input type="text" v-model="testObj.email"><br/>
      <p>지역코드: {{ testObj.areaId }} {{ testObj.areaName }}</p>
    </div>
    <button @click="staffProfileEditCancle">취소</button>
    <button @click="staffProfileEditConfirm">완료</button>
  </div>
</template>

<script>
// @ is an alias to /src
import { useRouter} from 'vue-router'
import { ref } from 'vue'

export default {
  name: 'StaffProfileEdit',
  components: {
  },
  setup() {
    const router = useRouter()
    let testObj = ref({
      "statusCode":"200",
      "message":"로그인 성공",
      "staffId": "hongildong",
      "userName":"홍길동",
      "phone":"010-1234-1234",
      "email":"h1@example.com",
      "areaId":"01",
      "areaName":"강남구"
    })

    const moveToStaffHome = () => {
      console.log("상담기록으로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const clickAlarm = () => {
      console.log("알람버튼 클릭됨!")
    }

    const moveToStaffProfile = () => {
      console.log("스태프 프로필로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffProfile' })
    }

    const logout = () => {
      console.log("로그아웃 버튼 클릭됨!")
      router.push({ name: 'Auth' })
    }

    const staffProfileEditCancle = () => {
      console.log("스태프 프로필수정 취소 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const staffProfileEditConfirm = () => {
      console.log("스태프 프로필수정 완료 버튼 클릭됨!")
      router.push({ name: 'StaffProfile' })
    }

    return {
      testObj,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
      logout,
      staffProfileEditCancle,
      staffProfileEditConfirm,
    }
  }
}
</script>
