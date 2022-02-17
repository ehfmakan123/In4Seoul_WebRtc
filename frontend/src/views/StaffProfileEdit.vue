<template>
  <div class="row flex-nowrap bg-gray-1 mw-100">
    <staff-sidebar/>
      <div class="w-80 p-5 staff-profile">
              <h2 class="text-start mt-3 fw-bold">프로필조회 상담사페이지</h2>
          <br>
      <div class="bg-white shadow">
        <div class="text-start p-3 fw-bold bd-bt ">프로필 조회>프로필수정</div>
          <table hover responsive class="p-3 mt-3 bg-body rounded"><tbody>
              <thead class="th-calss">
                  <tr class="tr-info"><th><br></th><th><br></th></tr>
              </thead>        
              <tr class="tr-info" data-bs-placement="top" >
                <td>유저 아이디 </td>
                <td>{{staffedits.userId}}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이름 </td>
                <td><input class="form-control form-25" v-model="staffedits.name"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>휴대폰번호 </td>
                <td><input class="form-control form-25" v-model="staffedits.phone"> </td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이메일 </td>
                <td><input class="form-control form-25" v-model="staffedits.email"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역코드 </td>
                <td>{{staffedits.areaId}} {{staffedits.areaName}}</td>
              </tr>
            </tbody>
          </table>
          <div class="bd-top">
            <div class="d-flex flex-row-reverse bd-highlight">
              <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#staffupdatecancelModal" >취소</button>
              <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#staffupdateconfirmModal" >완료</button>
            </div>
            
          </div>
          
          </div>
      </div>
    


  </div>
  <!-- confirmModal -->
    <div class="modal fade" id="staffupdateconfirmModal" tabindex="-1" aria-labelledby="staffupdateconfirmModal" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div id="staff-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5 text-center" id="staff-update-confirm-label">변경된 내용을<strong class="t-blue-3"><br>저장</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn  btn-outline-primary bd-blue-4 btn-yes-no " data-bs-dismiss="modal" @click="AdminStaffEidtUpdate">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
      <!-- cancel modal -->
      <div class="modal fade" id="staffupdatecancelModal" tabindex="-1" aria-labelledby="staffupdatecancelModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5 text-center" id="staff-update-cancel-label">내용을<strong class="t-blue-3">저장하지 않고</strong><br>나가시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="staffProfileEditCancle">네</button>
              <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
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
import StaffSidebar from '@/components/staff/StaffSidebar.vue'
import axios from 'axios'

// @ is an alias to /src
const SERVER_HOST = process.env.VUE_APP_SERVER_HOST


export default {
  name: 'StaffProfileEdit',
  components: {
    StaffSidebar
  },
  data () {
    return {
      staffedits: [],
    }
  },
  setup() {
    const router = useRouter()
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

    const staffProfileEditCancle = () => {
      console.log("스태프 프로필수정 취소 버튼 클릭됨!")
      router.push({ name: 'StaffProfile' })
    }

    // const staffProfileEditConfirm = () => {
    //   console.log("스태프 프로필수정 완료 버튼 클릭됨!")
    //   //router.push({ name: 'StaffProfile' })
    // }

    return {
      //testObj,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
      logout,
      staffProfileEditCancle,
      //staffProfileEditConfirm,
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
      this.staffedits = response.data.data;
      // console.log("staffedits: ")
      // console.log(this.staffedits)
      // console.log("staffedits.data: ")
      // console.log(this.staffedits.data)
    })
    .catch(err => {
      console.log(err);
    });
  },
  methods: {

      AdminStaffEidtUpdate(){
        
        const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }

      
        axios({
          method: 'put',
          url: `${SERVER_HOST}/staff/me/`,
          headers: config,
          data: {
              name: this.staffedits.name,
              phone: this.staffedits.phone,
              email: this.staffedits.email,

          }
        })
          .then((response) => {
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'StaffProfile' })
            
          })
          .catch((err) => {
            console.log("put실패!")
            console.error(err)
            }
          )   

      },

    }
}
</script>
