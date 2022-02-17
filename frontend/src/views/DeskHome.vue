<template>
  <div class="desk-background">
    <desk-logout-modal></desk-logout-modal>

    <div class="foggy-background">
      <div id="desk-content">
        <h1 class="heading text-white">In 4 Seoul <span style="font-size: 2.5rem">서울시 여행 안내 데스크</span></h1>
        <h2 class="heading text-white mb-5">Seoul Travel Information Service</h2>

        <div id="go-meeting" @click="moveToMeeting" class="bg-light shadow">
          <h3 class="heading">화상 상담 연결하기</h3>
          <p class="text-small text-secondary my-3 mt-4">상담 내용은 모두 저장되며, 상담사에게 폭언 및 장난은 삼가해주세요.</p>
          <p class="fw-bold t-blue-5">상담가능시간(평일 10:00~19:00, 주말:10:00~15:00)</p>
        </div>
      </div>

      
      <p id="desk-logout" @click="logout" class="t-gray-2 mt-auto" data-bs-toggle="modal" data-bs-target="#desk-logout-modal">logout</p>

      <div id="go-post">
        <p @click="moveToPost" class="text-white mb-3">담벼락 보러가기</p>
        <a @click="moveToPost" class="arrow-button ms-4 text-white">
          <i class="bi bi-arrow-right-circle"></i>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import DeskLogoutModal from '@/components/desk/DeskLogoutModal.vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'DeskHome',
  components: { DeskLogoutModal },
  setup() {
    const router = useRouter()

    const moveToPost = () => {
      console.log("게시판으로 이동 버튼 클릭됨!")
      router.push({ name: 'Post' })
    }

    const moveToMeeting = () => {
      console.log("화상 상담 연결하기 버튼 클릭됨!")
      const jwtToken = localStorage.getItem('token')
      axios({
        method: 'post',
        url: `${SERVER_HOST}/desk/meeting`,
        headers : {
          Authorization: `Bearer ${jwtToken}` 
        }
      })
        .then((res) => {
          // console.log('알람개수 갱신:', res.data.data.count)
          // console.log(typeof res.data.data.count)
          console.log(`화상상담 연결!`)
          console.log(res)
          
          // OpenVidu Session Id와 Token(ovTOken)을 로컬스토리지에 저장
          localStorage.setItem('ovToken', res.data.data.token)
          localStorage.setItem('ovSessionId', res.data.data.sessionId)

          router.push({ name: 'Meeting' })
        })
        .catch((err) => {
          const statusCode = err.response.data.statusCode
          if (statusCode === 409) {
            alert('상담 가능한 상담원이 없습니다.')
          } else {
            console.log(err)
          }
        })

    }

    const logout = () => {
      console.log("로그아웃 버튼 클릭됨!")
      // router.push({ name: 'Auth' })
    }

    return {
      moveToPost,
      moveToMeeting,
      logout,
    }
  },
  created(){
      const router = useRouter()

      if(!localStorage.getItem('deskData')){
        router.push({ name: 'Auth' })
      }   

  }
}
</script>
<style scoped>
  h1 {
    font-size: 3.5rem;
    font-weight: 600;
  }
  h2 {
    font-size: 2rem;
    font-weight: 500;
  }
  h3 {
    font-size: 1.5rem;
    font-weight: bold;
  }

  .heading {
    margin-top: 0;
    margin-bottom: 1rem;
    line-height: 1.2;
  }

  .text-small {
    font-size: 75%;
  }

  .desk-background {
    background-image: url('../assets/seoul_place/gyengbokgung.jpg');
    background-repeat : no-repeat;
    background-size: cover;
    height: 100vh;
    /* background-color: bisque; */
  }

  .foggy-background {
    height: 100%;
    width: 100%;
    background-color: black;
    background-color: rgba(0, 0, 0, 0.4);
    /* background-color: black;
    opacity: 0.4; */
  }

  .arrow-button {
    font-size: 4rem;
  }

  #go-meeting {
    width: 50vw;
    height: 10rem;
    padding: 1.8rem;
    /* opacity: 0.8; */ 
  }

  #go-meeting:hover {
  cursor: pointer;
}

  #desk-content {
    padding-top: 15vw;
    padding-left: 15vw;
  }

  #go-post {
    position: absolute;
    top: 13vw; left: 87vw;
  }

  #go-post:hover {
    cursor: pointer;
  }

  #desk-logout {
    position: absolute;
    top: 96vh; left: 20px;
  }
</style>