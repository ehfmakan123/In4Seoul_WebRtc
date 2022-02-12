<template>
  <div class="d-flex">
    <!-- 글 생성 Modal -->
    <post-form @add-new-post="addPost"></post-form>

    <div class="d-flex flex-column flex-shrink-0 shadow justify-content-center" style="width: 3.5rem;">
      <ul class="nav nav-pills nav-flush flex-column text-center" style="margin-bottom: 20rem;">
        <!-- <li class="nav-item">
          <a href="#" class="nav-link active py-3 border-bottom" aria-current="page" title="Home" data-bs-toggle="tooltip" data-bs-placement="right">
            <svg class="bi" width="24" height="24" role="img" aria-label="Home"><use xlink:href="#home"/></svg>
          </a>
        </li> -->
        <a @click="createPost" class="mx-2 fs-4 t-blue-4" data-bs-toggle="modal" data-bs-target="#createModal">
          <i class="bi bi-plus-circle-fill"></i>
        </a>
      </ul>    
    </div>

    <div class="vh-100">
      <div class="d-flex justify-content-between">
        <div>
          <h1 class="m-3">서울 여행자들의 담벼락</h1>
          <h3 class="t-gray-3 m-3">같이 나누고싶은 서울의 매력, 여행 꿀팁을 공유하고, 함께 돌아다닐 여행 친구도 구해보세요!</h3>
        </div>
        <post-filter :areaList="state.areaList" :deskList="state.deskList"></post-filter>
      </div>
      
      <!-- PostList -->
      <post-list :postList="state.postList"></post-list>
    </div>
    
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import PostForm from '@/components/post/PostForm'
import PostFilter from '@/components/post/PostFilter'
import PostList from '@/components/post/PostList'
import { useStore } from 'vuex'
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'Post',
  components: {
    PostForm,
    PostFilter,
    PostList,
  },
  setup() {
    const store = useStore()

    const state = ref({
      postList: computed(() => store.state.postList),
      areaList: [],
      deskList: []
    })

    const createPost = () => {
      console.log("포스트 작성하기 버튼 클릭됨!")
      console.log("모달창 열기")
    }

    const addPost = (newPost) => {
      state.value.postList.push(newPost)
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
          console.log(res.data.data)
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
          console.log(res.data.data)
          state.value.deskList = res.data.data
        })
        .catch(err => {
          console.error(err)
        })
    }
    
    // created
    store.dispatch('fetchPostList', 3)
    getAreaList()
    getDeskList(14) // 매개변수 수정해야함
    
    return {state, createPost, addPost}
  }
}
</script>
<style>
  .box-small {
    width: 17vw;
    height: 17vw;
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

  .second-modal {
    width: 10rem;
    height: 5rem;
  }
</style>
<style scoped>
  h1 {
    font-size: 3vh!important;
    font-weight: bold!important;
    /* font-weight: 500!important; */
  }
  h3 {
    font-size: 2vh!important;
  }
</style>