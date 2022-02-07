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
            <p id="password-error" class="d-none alert alert-danger my-3" role="alert">비밀번호가 일치하지 않습니다.</p>
            <p class="d-flex justify-content-end">
              <button @click="confirm" type="button" class="btn btn-primary">확인</button>
              <button @click="cancle" type="button" class="btn btn-secondary">닫기</button>
            </p>
          </div>
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
// import axios from 'axios'
import { ref } from 'vue'

export default {
  name: 'PostPasswordForm',
  components: {
  },
  props: ['postId'],
  setup(props, {emit}) {
    
    const state = ref({
      passwordConfirm: ''
    })

    const confirm = () => {
      console.log("확인 버튼 클릭됨!")
      // axios({
      //   method: 'post',
      //   url: `http://127.0.0.1:8000/board/posts/${props.postId}`,
        // headers: tokenHeader(),
      //   data: {
      //     password: state.value.passwordConfirm
      //   }
      // })
      //   .then(() => {
      //     console.log('비밀번호 일치함. 편집모달로 이동!')
          // passwordModal 끄기
          const passwordModal = document.querySelector('#passwordModal')
          passwordModal.classList.remove("in")
          passwordModal.style.display = "none"
          // detailModal 끄기
          const detailModal = document.querySelector('#detailModal')
          detailModal.classList.remove("show")
          // detailModal.style.display = "none"
          // updateModal 켜기
          const updateModal = document.querySelector('#updateModal')
          updateModal.classList.add("show")
          updateModal.style.display = "block"

          state.value.passwordConfirm = ''
        // })
        // .catch(err => {
        //   console.error(err)
        //   console.log('비밀번호가 다름!!!')
        //   const errorMessage = document.querySelector('#password-error')
        //   errorMessage.classList.remove('d-none')
        // })  
      emit('password-correct')
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨!")
      // 모달창 끄기
      const passwordModal = document.querySelector('#passwordModal')
      passwordModal.classList.remove("in")
      passwordModal.style.display = "none"
    }
    return { state, confirm, cancle }
  }
}
</script>
