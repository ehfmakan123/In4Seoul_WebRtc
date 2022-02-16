<template>
  <div class="d-flex">
    <!-- 글 생성 Modal -->
    <post-form></post-form>

    <!-- 왼쪽 사이드바 -->
    <!-- <div class="d-flex flex-column flex-shrink-0 shadow justify-content-center" style="width: 3.5rem;">
      <ul class="nav nav-pills nav-flush flex-column text-center" style="margin-bottom: 20rem;">
        <a @click="createPost" class="mx-2 fs-4 t-blue-4" data-bs-toggle="modal" data-bs-target="#createModal">
          <i class="bi bi-plus-circle-fill"></i>
        </a>
      </ul>    
    </div> -->

    <!-- 오른쪽 구역 -->
    <div class="min-vh-100 w-100">
      <div class="d-flex justify-content-between">
        <div id="go-deskhome" class="p-3">
          <p @click="moveToDeskHome" class=" t-gray-3 text-small">돌아가기</p>
          <p @click="moveToDeskHome" class="arrow-button t-gray-3 text-center mt-2">
            <i class="bi bi-arrow-left-circle"></i>
          </p>
        </div>
        <div class="me-auto">
          <h1 class="m-3">서울 여행자들의 담벼락</h1>
          <h3 class="t-gray-3 m-3">같이 나누고싶은 서울의 매력, 여행 꿀팁을 공유하고, 함께 돌아다닐 여행 친구도 구해보세요!</h3>
        </div>

        <!-- 필터들 -->
        <div class="d-flex align-items-center me-4">
          <span @click="createPost" class="t-blue-4 me-3 fw-bold" data-bs-toggle="modal" data-bs-target="#createModal">글 남기기</span>
          <a @click="createPost" class="fs-1 t-blue-4 me-5" data-bs-toggle="modal" data-bs-target="#createModal">
            <i class="bi bi-plus-circle-fill"></i>
          </a>
          <!-- 지역 선택 필터 -->
          <div class="dropdown me-3">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
              {{ state.nowAreaName }}
            </a>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <li
                v-for="area in state.areaList"
                :key="area.id"
              >
                <a class="dropdown-item" @click="selectArea(area.id, area.korName)">{{ area.korName }}</a>
              </li>
            </ul>
          </div>
          <!-- 데스크 선택 필터 -->
          <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
              {{ state.nowDeskName }}
            </a>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <li
                v-for="desk in state.deskList"
                :key="desk.id"
              >
                <a class="dropdown-item" @click="selectDesk(desk.id, desk.korName)">{{ desk.korName }}</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      
      <!-- PostList -->
      <post-list :postList="state.postList"></post-list>
    </div>
    
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import PostForm from '@/components/post/PostForm'
import PostList from '@/components/post/PostList'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'Post',
  components: {
    PostForm,
    PostList,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const deskData = JSON.parse(localStorage.getItem('deskData'))
    console.log('LocalStorage deskData: ', deskData)

    const state = ref({
      postList: computed(() => store.state.postList),
      nowAreaName: deskData.areaKorName,
      nowDeskName: deskData.deskKorName,
      areaList: [],
      deskList: []
    })

    const moveToDeskHome = () => {
      console.log("게시판으로 이동 버튼 클릭됨!")
      router.push({ name: 'DeskHome' })
    }

    const createPost = () => {
      console.log("포스트 작성하기 버튼 클릭됨!")
      console.log("모달창 열기")
    }

    const getAreaList = () => {
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }

      axios({
        method: 'get',
        url: `${SERVER_HOST}/desk/areas`,
        headers: config
      })
        .then((res) => {
          console.log('지역 목록 가져오기 성공')
          // console.log(res.data.data)
          state.value.areaList = res.data.data
        })
        .catch(err => {
          console.error(err)
        })
    }

    const getDeskList = (areaId) => {
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }

      axios({
        method: 'get',
        url: `${SERVER_HOST}/desk/areas/${areaId}`,
        headers: config
      })
        .then((res) => {
          console.log('데스크 목록 가져오기 성공')
          // console.log(res.data.data)
          state.value.deskList = res.data.data
        })
        .catch(err => {
          console.error(err)
        })
    }

    const selectArea = (areaId, areaName) => {
      state.value.nowAreaName = areaName
      state.value.nowDeskName = '선택'
      getDeskList(areaId)
    }

    const selectDesk = (deskId, deskName) => {
      state.value.nowDeskName = deskName
      console.log("fetch1")
      //store.dispatch('fetchPostList', deskId, 1)
      store.dispatch('fetchPostList',  1 ,deskId)
    }
    
    // created
    //store.dispatch('fetchPostList', , 1)
    console.log("fetch2")
    console.log(deskData.deskPk)
    const obj = {deskId:deskData.deskPk, nowPage: 1 }
    store.dispatch('fetchPostList', obj)
    getAreaList()
    getDeskList(deskData.areaPk)
    
    return {state, moveToDeskHome, createPost, selectArea, selectDesk}
  },
  create() {
      const router = useRouter()

      if(!localStorage.getItem('deskData')){
        router.push({ name: 'Auth' })
      }   
  }
}
</script>
<style>
  .arrow-button {
    font-size: 2rem;
  }
  .box-small {
    width: 13vw;
    height: 13vw;
    border-radius: 1.5rem!important;
  }
  .box-big {
    border-radius: 1.5rem!important;
  }
  .rounded-btn {
    border-radius: 2rem!important;
  }
  .text-small {
    font-size: 0.85em;
  }
  .bg-yellow{
    background-color: #FFEEAC!important;
  }

  .post-input {
    border: 0px!important;
    background-color: rgba(251, 250, 250, 0.924)!important;
  }

  .input-title {
    height: 2.5rem;
    
  }

  .input-content {
    width: 100%;
    height: 15rem;
  }

</style>
<style scoped>
  h1 {
    font-size: 3.5vh!important;
    font-weight: bold!important;
    /* font-weight: 500!important; */
  }
  h3 {
    font-size: 2vh!important;
  }
</style>