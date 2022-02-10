<template>
  <div>
    <div class="modal fade" id="passwordModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content bd-blue-4 px-4 pt-3 pb-4" style="width:30rem; height:9rem">
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
  </div>
</template>

<script>
// import axios from 'axios'
import { ref } from 'vue'
import { Modal } from 'bootstrap'

export default {
  name: 'PostPasswordForm',
  components: {
  },
  props: ['postId'],
  setup() {
    
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
          let modal = Modal.getOrCreateInstance(passwordModal)
          modal.hide()
          
          // detailModal 끄기
          const detailModal = document.querySelector('#detailModal')
          modal = Modal.getOrCreateInstance(detailModal)
          modal.hide()

          // updateModal 켜기
          const updateModal = document.querySelector('#updateModal')
          modal = Modal.getOrCreateInstance(updateModal)
          modal.show()

          state.value.passwordConfirm = ''
        // })
        // .catch(err => {
        //   console.error(err)
        //   console.log('비밀번호가 다름!!!')
        //   const errorMessage = document.querySelector('#password-error')
        //   errorMessage.classList.remove('d-none')
        // })
    }
    const cancle = () => {
      console.log("닫기 버튼 클릭됨")
      // 모달창 끄기
      const passwordModal = document.querySelector('#passwordModal')
      const modal = Modal.getOrCreateInstance(passwordModal)
      modal.hide()
    }
    return { state, confirm, cancle }
  }
}
</script>
<style scoped>
  #passwordModal .modal-content{
    border-width: 2px;
    border-style: solid;
    border-radius: 1rem;
  }
</style>
