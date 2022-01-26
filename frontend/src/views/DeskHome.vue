<template>
  <div id="desk-home-post">
    <img src="@/assets/logo.png" alt="test">
    <button @click="moveToPost">게시판으로 이동!</button>
  </div>
  <hr>
  <div id="desk-home-meeting">
    <h1>서울시 여행 안내 데스크</h1>
    <h1>Seoul Travel Information  Service</h1>
    <h1>화상 상담 연결하기</h1>
    <button @click="moveToMeeting">Go</button><br>
    <p>상담 내용은 모두 저장되며, 상담사에게 폭언 및 장난은 삼가해주세요.</p>
    <p>상담가능시간(평일 10:00~19:00, 주말:10:00~15:00)</p>
    <button @click="logout">로그아웃</button>
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
