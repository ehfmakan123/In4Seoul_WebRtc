<template>
  <div>
    <h1>게시판 페이지</h1>
    <button @click="createPost">포스트 작성하기</button>
    <post-form v-if="state.showPostForm"></post-form>
    <post-filter></post-filter>
    <post-list :postList="state.postList"></post-list>
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
    
    return {state, createPost}
  }
}
</script>
