<template>
  <div>
    <hr>
    <!-- Detail Modal -->
    <post-detail :selectedPost="state.selectedPost"></post-detail>
    <!-- password modal -->
    <post-password-form></post-password-form>


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
import PostPasswordForm from '@/components/post/PostPasswordForm'


export default {
  name: 'PostList',
  components: {
    PostListItem,
    PostDetail,
    PostPasswordForm
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
