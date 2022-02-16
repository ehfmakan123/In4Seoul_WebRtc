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
                <td>{{ staffprofiles.userId }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이름 </td>
                <td> {{ staffprofiles.name }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>휴대폰번호 </td>
                <td>{{ staffprofiles.phone }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이메일 </td>
                <td>{{ staffprofiles.email }}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역코드 </td>
                <td>{{ staffprofiles.areaId }} {{ staffprofiles.areaName }}</td>
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
//import { ref } from 'vue'
import axios from 'axios'
import StaffSidebar from '@/components/staff/StaffSidebar.vue'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'StaffProfile',
  components: {
    StaffSidebar
  },
  data () {
    return {
      staffprofiles: [],
    }
  },
  setup() {
    const router = useRouter()
    // // const store = useStore()

    // let testObj = ref({
    //   "statusCode":"200",
    //   "message":"로그인 성공",
    //   "staffId": "hongildong",
    //   "userName":"홍길동",
    //   "phone":"010-1234-1234",
    //   "email":"h1@example.com",
    //   "areaId":"01",
    //   "areaName":"강남구"
    // })

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

    // const axiosProfile = () => {
    //   console.log("프로필 axios 버튼 클릭됨!")

    //   const token = localStorage.getItem('token')
    //   const config = {
    //     Authorization: `Bearer ${token}`
    //   }

    //   axios({
    //     method: 'get',
    //     url: 'http://127.0.0.1:8080/staff/me',
    //     headers: config
    //   })      
    //     .then(res => {
    //       console.log(res)
          
    //     })
    //     .catch(err => {
    //       console.log(err.response.data)
    //     })

    // }

    return {
      //testObj,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
      logout,
      staffProfileCancle,
      staffProfileEdit,
      //axiosProfile
    }
  },
  created () {
    const router = useRouter()

    if(!localStorage.getItem('staffData')){
        router.push({ name: 'Auth' })
    }    
    const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
    axios.get(`${SERVER_HOST}/staff/me`, {headers: config})
    .then(response => {
      console.log(response.data);
      this.staffprofiles = response.data.data;
      
      // console.log("staffprofiles: ")
      // console.log(this.staffprofiles)
      // console.log("staffprofiles.data: ")
      // console.log(this.staffprofiles.data)
    })
    .catch(err => {
      console.log(err);
    });
  },
}
</script>
