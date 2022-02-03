<template>
  <div>
    <!-- Modal -->
    <post-detail :selectedPost="state.selectedPost"></post-detail>
    
    <h2>PostList</h2>
    <hr>
    <div class="row p-4">
      <post-list-item
        
        v-for="post in postList"
        :key="post.id"
        :post="post"
        @click="selectPost(post)"
      >
      </post-list-item>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import PostListItem from '@/components/post/PostListItem'
import PostDetail from '@/components/post/PostDetail'

export default {
  name: 'PostList',
  components: {
    PostListItem,
    PostDetail
  },
  props: ["postList"],
  setup() {
    const state = ref({
      selectedPost: {}
    })
    const selectPost = (post) => {
      console.log(`${post.id}번 포스트 선택됨`)
      state.value.selectedPost = post
      console.log(state.value.selectedPost)
    }
    return {state, selectPost}
  }
}
</script>
