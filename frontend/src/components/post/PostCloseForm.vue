<template>
  <div>
    <div class="modal fade" id="closeModal" style="z-index: 1060;" tabindex="-1" aria-labelledby="closeModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content bd-blue-4 ms-3">
          <div class="modal-body p-4">
            <div class="mt-4 ms-3">
              <p class="fw-bold" style="font-size: 1.1rem;">내용을 저장하지 않고 닫으시겠습니까?</p>
              <p class="mt-2 fs-6 fw-light t-gray-3">Do you want close without saving the contents?</p>
            </div>
            <div class="d-flex justify-content-end pt-3">
              <button @click="close" type="button" class="btn btn-outline-primary t-blue-4 bd-blue-4 rounded-btn" data-bs-dismiss="modal">Yes</button>
              <button type="button" class="btn btn-outline-dark ms-3 rounded-btn" data-bs-dismiss="modal">No</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from 'bootstrap'

export default {
  name: 'PostCloseForm',
  components: {
  },
  setup() {
    const close = () => {
      console.log("내용 저장하지 않고 닫음")
      // updateModal도 닫기
      const updateModal = document.querySelector('#updateModal')
      let modal = Modal.getOrCreateInstance(updateModal)
      modal.hide()
    }
    return { close }
  }
}
</script>
<style scoped>
  .modal-content{
    border-width: 1.5px;
    border-style: solid;
    border-radius: 1rem;
    width:29rem; 
    height:10rem;
  }
</style>