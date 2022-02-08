<template>
  <div class="desk-background">
    <div class="foggy-background">
      <div id="desk-content">
        <h1 class="heading text-white">서울시 여행 안내 데스크</h1>
        <h2 class="heading text-white mb-5">Seoul Travel Information Service</h2>

        <div id="go-meeting" @click="moveToMeeting" class="bg-light shadow">
          <h3 class="heading">화상 상담 연결하기</h3>
          <p class="text-small text-secondary my-3 mt-4">상담 내용은 모두 저장되며, 상담사에게 폭언 및 장난은 삼가해주세요.</p>
          <p class="fw-bold t-blue-5">상담가능시간(평일 10:00~19:00, 주말:10:00~15:00)</p>
        </div>
      </div>

      
      <p id="desk-logout" @click="logout" class="t-gray-2 mt-auto">logout</p>

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
// @ is an alias to /src
import { useRouter } from 'vue-router'

export default {
  name: 'DeskHome',
  components: {
  },
  setup() {
    const router = useRouter()

    const moveToPost = () => {
      console.log("게시판으로 이동 버튼 클릭됨!")
      router.push({ name: 'Post' })
    }

    const moveToMeeting = () => {
      console.log("화상 상담 연결하기 버튼 클릭됨!")
      router.push({ name: 'Meeting' })
    }

    const logout = () => {
      console.log("로그아웃 버튼 클릭됨!")
      router.push({ name: 'Auth' })
    }

    return {
      moveToPost,
      moveToMeeting,
      logout,
    }
  }
}
</script>
<style scoped>
  h1 {
    font-size: 3rem;
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
    background-image: url('../assets/seoul_place/경복궁낮_cut3.jpg');
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

  #desk-content {
    padding-top: 15vw;
    padding-left: 15vw;
  }

  #go-post {
    position: absolute;
    top: 13vw; left: 87vw;
  }

  #desk-logout {
    position: absolute;
    top: 96vh; left: 20px;
  }
</style>