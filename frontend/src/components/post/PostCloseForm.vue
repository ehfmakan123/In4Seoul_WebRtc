<template>
  <div>
    <hr>
    <p>내용을 저장하지 않고 닫으시겠습니까?</p>
    <p>
      <button @click="close">확인</button>
      <button @click="cancle">취소</button>
    </p>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PostCloseForm',
  components: {
  },
  setup() {
    const close = () => {
      console.log("확인 버튼 클릭됨!")
    }
    const cancle = () => {
      console.log("취소 버튼 클릭됨!")
    }
    return {close, cancle}
  }
}
</script>
