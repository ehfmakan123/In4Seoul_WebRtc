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
      <h1 class="m-3">서울 여행자들의 담벼락</h1>
      <h3 class="t-gray-3 m-3">같이 나누고싶은 서울의 매력, 여행 꿀팁을 공유하고, 함께 돌아다닐 여행 친구도 구해보세요!</h3>
      <post-filter></post-filter>
      
      <!-- PostList -->
      <post-list :postList="state.postList"></post-list>
    </div>
    
  </div>
</template>

<script>
import { ref } from 'vue'
import PostForm from '@/components/post/PostForm'
import PostFilter from '@/components/post/PostFilter'
import PostList from '@/components/post/PostList'
// import { computed } from 'vue'
// import { useStore } from 'vuex'

export default {
  name: 'Post',
  components: {
    PostForm,
    PostFilter,
    PostList,
  },
  setup() {
    const state = ref({
      showPostForm: false,
      postList: [
        {
          "id":"1",
          "title":"동행 구해요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"2",
          "title":"남산 같이 가요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"3",
          "title":"뮤지컬 프랑켄슈타인",
          "content":"블루스퀘어에서 하고있어요 괴물 너무 마음 아파 ㅠㅠ",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"1",
          "title":"동행 구해요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"2",
          "title":"남산 같이 가요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"3",
          "title":"뮤지컬 프랑켄슈타인",
          "content":"블루스퀘어에서 하고있어요 괴물 너무 마음 아파 ㅠㅠ",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"1",
          "title":"동행 구해요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        },
        {
          "id":"2",
          "title":"남산 같이 가요",
          "content":"동행 구합니다!!!",
          "createdAt":"2022-01-01 11:20:00",
          "updatedAt":"2022-01-01 11:20:00"
        }
      ]
    })

    const createPost = () => {
      console.log("포스트 작성하기 버튼 클릭됨!")
      console.log("모달창 열기")
      console.log(state.value.showPostForm)
      state.value.showPostForm = !state.value.showPostForm
      console.log(state.value.showPostForm)
    }

    const addPost = (newPost) => {
      state.value.postList.push(newPost)
    }


    // const deskId = 1
    // const areaId = 1
    // const postId = 1

    // const store = useStore();

    // const postList = computed(() => store.state.postList)
    // const post = computed(() => store.state.post)

    // const fetchPostList = () => store.actions.fetchPostList
    // const fetchPost = () => store.actions.fetchPost
    
    // onBeforeMount(() => {
    //   fetchPostList(deskId, areaId)
    //   fetchPost(postId)
    // })
    
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