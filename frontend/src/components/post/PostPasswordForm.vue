<template>
  <div>
    <div class="modal fade" id="passwordModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content" style="width:30rem; height:9rem">
          <!-- <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">{{ selectedPost.title }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div> -->
          <div class="modal-body">
            <p>비밀번호: <input type="password" v-model="state.passwordConfirm" placeholder="비밀번호를 입력해주세요" class="w-75"></p>
            <p class="my-3">비밀번호가 틀렸습니다.</p>
            <p class="d-flex justify-content-end">
              <button @click="confirm" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailModal">확인</button>
              <button @click="cancle" type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#detailModal">닫기</button>
            </p>
          </div>
          <!-- <div class="modal-footer">
            <button data-bs-toggle="modal" data-bs-target="#passwordModal" @click="tryEdit" type="button" class="btn btn-primary">편집</button>
            <button @click="cancle" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          </div> -->
        </div>
      </div>
    </div>

    <!-- <hr>
    <p>비밀번호: <input type="password" v-model="state.passwordConfirm" placeholder="비밀번호를 입력해주세요"></p>
    <p>
      <button @click="confirm">확인</button>
      <button @click="cancle">닫기</button>
    </p> -->
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
