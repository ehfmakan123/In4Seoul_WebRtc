<template>
  <div>
    <hr>
    <p>비밀번호: <input type="password" v-model="state.passwordConfirm" placeholder="비밀번호를 입력해주세요"></p>
    <p>
      <button @click="confirm">확인</button>
      <button @click="cancle">닫기</button>
    </p>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'PostPasswordForm',
  components: {
  },
  setup(props, {emit}) {
    const state = ref({
      passwordConfirm: ''
    })
    const confirm = () => {
      console.log("확인 버튼 클릭됨!")
      emit('password-correct')
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
    }
    return { state, confirm, cancle }
  }
}
</script>
