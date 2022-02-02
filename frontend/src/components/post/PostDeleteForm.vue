<template>
  <div>
    <hr>
    <p>글을 삭제하시겠습니까?</p>
    <p>
      <button @click="deletePost">삭제</button>
      <button @click="cancle">취소</button>
    </p>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PostDeleteForm',
  components: {
  },
  setup() {
    const deletePost = () => {
      console.log("삭제")
    }
    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
    }
    return { deletePost, cancle }
  }
}
</script>
