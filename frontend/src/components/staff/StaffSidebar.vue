<template>
        <div class="col-auto col-xl-1-2 px-sm-2 px-0 bg-blue-4">
            <div class="d-flex flex-column align-items-center px-3 pt-2 text-white min-vh-100">
              
              <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline"></span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-center" id="menu">
                    <li class="nav-item text-center">
                      <div type="button" @click="moveToStaffHome" class="nav-link text-white align-middle"> 
                        <span class=" d-none d-sm-inline">
                            <i class="bi bi-headset fs-0"></i>
                          <p>상담기록</p>
                        </span>
                        
                      </div>
                    </li>
                    <li class="nav-item text-center">
                      <div type="button" @click="clickAlarm" class="nav-link text-white align-middle"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i id="alarm-icon" class="bi bi-bell-fill fs-0"></i>
                          <p>상담 알림</p>
                          <p class="bg-blue-2 mt-1 d-inline-block alarm-number py-1">{{ waitingMeetingCount }}</p>
                          <!-- v-bind:value="waitingMeetingCount.value" -->
                        </span>
                      </div>
                    </li>
                    <li class="nav-item text-center">
                      <div type="button" @click="moveToStaffProfile" class="nav-link text-white align-middle"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-person-fill fs-0"></i>
                          <p>프로필정보</p>
                        </span>
                      </div>
                    </li>
                    <li class="nav-item text-center mt-2 ms-1">
                      <div class="d-flex justify-content-center  align-items-center text-white text-decoration-none" type="button"  data-bs-toggle="modal"  data-bs-target="#staffModal"> 
                          <span class="d-none d-sm-inline mx-1 text-center">
                              <i class="bi bi-box-arrow-right fs-0"></i>
                              <p>Logout</p>
                              </span>
                      </div>
                    </li>

                </ul>
                <hr>
                
                

            </div>
              <!-- <div class="pb-4">
                  <div class="d-flex justify-content-center  align-items-center text-white text-decoration-none" type="button"  data-bs-toggle="modal"  data-bs-target="#staffModal"> 
                      <span class="d-none d-sm-inline mx-1 text-center">
                          <i class="bi bi-box-arrow-right fs-0"></i>
                          <p>Logout</p>
                          </span>
                  </div>
                  
              </div>             -->
      </div>
      <!-- Modal -->
      <div class="modal fade" id="staffModal" tabindex="-1" aria-labelledby="staffModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="staff-login-modal-label"><strong class="t-blue-3">로그아웃</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" @click="logout" class="btn btn-outline-primary bd-blue-4 btn-yes-no">네</button>
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
import { ref } from 'vue'
import axios from 'axios'
// firebase
import firebase from 'firebase/app'
import 'firebase/messaging'
import { Modal } from 'bootstrap'
const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'StaffHome',
  components: {
  },

  setup() {
    const router = useRouter()
    let waitingMeetingCount = ref(0)
    const messaging = firebase.messaging()

    // FCM 메시지 수신
    messaging.onMessage(payload => {
      console.log("StaffSidebar 메시지 수신!!")
      console.log(payload)
      getWaitingMeeting()
      // console.log("제목: ", payload.notification.title)
      // console.log("제목: ", payload.data.title)
      // alert('상담 요청이 들어왔습니다!')
    })

    const moveToStaffHome = () => {
      console.log("상담기록으로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const clickAlarm = () => {
      console.log("알람버튼 클릭됨!")
      const jwtToken = localStorage.getItem('token')
      axios({
        method: 'post',
        url: `${SERVER_HOST}/staff/meeting`,
        headers : {
          Authorization: `Bearer ${jwtToken}` 
        }
      })
        .then((res) => {
          console.log('상담연결버튼 response: ',res.data)
          localStorage.setItem('ovToken', res.data.data.token)
          localStorage.setItem('ovSessionId', res.data.data.sessionId)
          axios({
            method: 'post',
            url: `${SERVER_HOST}/staff/meeting/start`,
            headers : {
              Authorization: `Bearer ${jwtToken}` 
            },
            data : {
              sessionId: res.data.data.sessionId
            }
          })
          .then((res) => {
            localStorage.setItem('meetingLogId', res.data.data.meetingLogId)
            console.log(res)
            router.push({ name: 'Meeting'})
          })
          .catch((err) => console.log(err))
        })
        .catch((err) => {
          const statusCode = err.response.data.statusCode
          if (statusCode === 409) {
            waitingMeetingCount.value = 0
            getWaitingMeeting()
            alert('연결할 상담이 없습니다.')
          } else {
            console.log(err)
          }
        })
    }

    const moveToStaffProfile = () => {
      console.log("스태프 프로필로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffProfile' })
    }

    const logout = () => {
      console.log("로그아웃 버튼 클릭됨!")
     
       // 로그아웃 axios 
      const jwtToken = localStorage.getItem('token')
      axios({
        method: 'post',
        url: `${SERVER_HOST}/staff/logout`,
        headers : {
          Authorization: `Bearer ${jwtToken}` 
        }
      })
        .then((res) => {
          // console.log('알람개수 갱신:', res.data.data.count)
          // console.log(typeof res.data.data.count)
          console.log(res.data)

          localStorage.clear();
          router.push({ name: 'Auth' })

          const staffModal = document.querySelector('#staffModal')
          let modal = Modal.getOrCreateInstance(staffModal)
          modal.hide()

            })
            .catch((err) => console.log(err))

    }

    const getWaitingMeeting = () => {
      // 대기 알람개수 세는 axios 
      const jwtToken = localStorage.getItem('token')
      console.log('getWaitingMeeting jwtToken: ', jwtToken)
      axios({
        method: 'get',
        url: `${SERVER_HOST}/staff/meeting`,
        headers : {
          Authorization: `Bearer ${jwtToken}` 
        }
      })
        .then((res) => {
          // console.log('알람개수 갱신:', res.data.data.count)
          // console.log(typeof res.data.data.count)
          waitingMeetingCount.value = res.data.data.count
          console.log(`대기 상담수 갱신!!: ${waitingMeetingCount.value}`)

          const alarmIcon = document.querySelector('#alarm-icon')
          if (waitingMeetingCount.value) {
            alarmIcon.classList.add('alarm-yellow')
          } else {
            alarmIcon.classList.remove('alarm-yellow')
          }

        })
        .catch((err) => {console.log(err)})
    }

    getWaitingMeeting()

    return {
      waitingMeetingCount,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
      logout,
    }
  },
}
</script>


<style>

  .fs-0 {
    font-size: 3.5rem!important;
  }
  /* @media (min-width: 1200px) */
  .col-xl-1-2 {
      flex: 0 0 auto;
      width: 10.666667% important;
  }

  .alarm-yellow {
    color: #fbfb43;
  }

  .alarm-number {
    border-radius: 10px;
    width: 2rem;
  }
</style>