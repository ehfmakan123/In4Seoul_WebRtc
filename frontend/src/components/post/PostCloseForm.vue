<template>
  <div>
    <div class="modal fade" id="closeModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="second-modal modal-dialog modal-dialog-centered">
        <div class="modal-content" style="width:30rem; height:9rem">
          <div class="modal-body">
            <p>내용을 저장하지 않고 닫으시겠습니까?</p>
            <p class="d-flex justify-content-end">
              <button @click="close" type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
              <button @click="cancle" type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal">취소</button>
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- <hr>
    <p>내용을 저장하지 않고 닫으시겠습니까?</p>
    <p>
      <button @click="close">확인</button>
      <button @click="cancle">취소</button>
    </p> -->
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
