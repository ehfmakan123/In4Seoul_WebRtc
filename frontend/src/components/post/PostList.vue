<template>
  <div>
    <hr>
    <!-- detail Modal -->
    <post-detail :selectedPost="state.selectedPost"></post-detail>
    <!-- password modal -->
    <post-password-form :postId="state.selectedPost.postId"></post-password-form>
    <!-- update modal -->
    <post-update-form @try-delete="tryDelete" @try-unsave-close="tryUnsaveClose" :post="state.selectedPost"></post-update-form>
    <!-- close modal -->
    <post-close-form></post-close-form>
    <!-- delete modal -->
    <post-delete-form :postId="state.selectedPost.postId"></post-delete-form>


    <div class="row p-5">
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
import PostUpdateForm from '@/components/post/PostUpdateForm'
import PostCloseForm from '@/components/post/PostCloseForm'
import PostDeleteForm from '@/components/post/PostDeleteForm'

export default {
  name: 'PostList',
  components: { PostListItem, PostDetail, PostPasswordForm, PostUpdateForm, PostCloseForm, PostDeleteForm },
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

    const tryDelete = () => {
      // state.value.showDeleteForm = !state.value.showDeleteForm
    }
    const tryUnsaveClose = () => {
      // state.value.showCloseForm = !state.value.showCloseForm
    }

    return {state, selectPost, tryDelete, tryUnsaveClose}
  }
}
</script>
