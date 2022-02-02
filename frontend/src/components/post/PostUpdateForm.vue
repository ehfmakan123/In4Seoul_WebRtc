<template>
  <div>
    <hr>
    <h3>PostUpdateForm</h3>
    <p><input type="text" v-model="state.myPost.title" placeholder="제목을 입력해주세요"></p>
    <p><input type="text" v-model="state.myPost.content" placeholder="내용을 입력해주세요"></p>
    <p>
      <button @click="deletePost">삭제</button>
      <button @click="savePost">저장</button>
      <button @click="cancle">닫기</button>
    </p>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'PostUpdateForm',
  components: {
  },
  props:["post"],
  setup(props, { emit }) {
    const state = ref({
      myPost: {
        title: props.post.title,
        content: props.post.content
      }
    })

    const deletePost = () => {
      console.log("삭제 버튼 클릭됨!")
      emit('try-delete')
    }

    const savePost = () => {
      console.log("저장 버튼 클릭됨!")
      console.log(state.value.myPost)
    }

    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
      emit('try-unsave-close')
    }

    return {state, deletePost, savePost, cancle}
  }
}
</script>
