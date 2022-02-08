<template>
  <div class="row flex-nowrap bg-gray-1">
    <staff-sidebar/>
      <div class="w-80 p-5 staff-profile">
              <h2 class="text-start mt-3 fw-bold">프로필조회 상담사페이지</h2>
          <br>
      <div class="bg-white shadow">
        <div class="text-start p-3 fw-bold bd-bt ">프로필 조회>프로필조회</div>
          <table hover responsive class="p-3 mt-3 bg-body rounded"><tbody>
              <thead class="th-calss">
                  <tr class="tr-info"><th><br></th><th><br></th></tr>
              </thead>        
              <tr class="tr-info" data-bs-placement="top" >
                <td>유저 아이디 </td>
                <td>{{ testObj.staffId }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이름 </td>
                <td> {{ testObj.userName }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>휴대폰번호 </td>
                <td>{{ testObj.phone }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이메일 </td>
                <td>{{ testObj.email }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역코드 </td>
                <td>{{ testObj.areaId }} {{ testObj.areaName }}</td>
              </tr>
            </tbody>
          </table>
          <div class="bd-top">
            <div class="d-flex flex-row-reverse bd-highlight">
              <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button" @click="staffProfileCancle">취소</button>
              <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" @click="staffProfileEdit">수정</button>
            </div>
            
          </div>
          
          </div>
      </div>
    
  </div>

</template>

<script>
// @ is an alias to /src
import { useRouter} from 'vue-router'
import { ref } from 'vue'
import StaffSidebar from '@/components/staff/StaffSidebar.vue'

export default {
  name: 'StaffProfile',
  components: {
    StaffSidebar
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
