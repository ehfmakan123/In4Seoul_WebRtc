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
      <p>이름: {{ testObj.userName }}</p>
      <p>휴대폰번호: {{ testObj.phone }}</p>
      <p>이메일: {{ testObj.email }}</p>
      <p>지역코드: {{ testObj.areaId }} {{ testObj.areaName }}</p>
    </div>
    <button @click="staffProfileCancle">취소</button>
    <button @click="staffProfileEdit">수정</button>
  </div>
</template>

<script>
// @ is an alias to /src
import { useRouter} from 'vue-router'
import { ref } from 'vue'

export default {
  name: 'StaffProfile',
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

    const staffProfileCancle = () => {
      console.log("스태프 프로필 취소 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const staffProfileEdit = () => {
      console.log("스태프 프로필 수정 버튼 클릭됨!")
      router.push({ name: 'StaffProfileEdit' })
    }

    return {
      testObj,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
      logout,
      staffProfileCancle,
      staffProfileEdit,
    }
  }
}
</script>
