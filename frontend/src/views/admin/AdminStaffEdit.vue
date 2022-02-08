<template>
    <div class="row flex-nowrap bg-gray-1">
     <AdminSidebar />
     <router-view/> 
      <div class="w-80 p-5">
              <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
          <br>
      <div class="bg-white shadow">
        <div class="text-start p-3 fw-bold bd-bt ">스태프 관리＞수정페이지</div>
          <table hover responsive class="p-3 mt-3 bg-body rounded"><tbody>
              <thead class="th-calss">
                  <tr class="tr-info"><th><br></th><th><br></th></tr>
              </thead>        
              <tr class="tr-info" data-bs-placement="top" >
                <td>아이디: </td>
                <td><input class="form-control form-25"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이름: </td>
                <td><input class="form-control form-25 "></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이메일 </td>
                <td><input type="email" class="form-control form-25"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>휴대폰번호 </td>
                <td><input class="form-control form-25"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역코드 </td>
                <td><input class="form-control  form-25"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역명 </td>
                <td><input class="form-control  form-25"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>승인 </td>
                <td><select class="form-select form-10" aria-label="Default select example" name="assign" id="assign">
                      <option value="Y">Y</option>
                      <option value="N">N</option>
                    </select></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>생성일 </td>
                <td>2022-01-01 11:20</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>수정일 </td>
                <td>2022-01-01 11:20</td>
              </tr>
            </tbody>
          </table>
          <div class="bd-top">
            <div class="d-flex flex-row-reverse bd-highlight">
              <button class="btn btn-outline-danger bd-red-2 bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
              <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#cancelModal">취소</button>
              <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" @click="staffUpdate()">수정</button>
            </div>
            
          </div>
          
          </div>
      </div>
        
    </div>
    <!-- delete modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-red-2 px-4 pt-3 pb-4">
            <div id="staff-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" >계정을 <strong class="t-red-2">삭제</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-danger bd-red-2 btn-yes-no " data-bs-dismiss="modal" @click="staffDelete()">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
      <!-- cancel modal -->
      <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="cancelModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="staff-login-modal-label">수정을<strong class="t-blue-3">취소</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="staffCancel()" >네</button>
              <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>

</template>

<script>

import AdminSidebar from '@/components/admin/AdminSidebar.vue'

export default({
    name: 'AdminStaffEdit',
    components: {
        AdminSidebar,
    },
    methods: {
        staffUpdate(){
            this.$router.push({ name: 'AdminStaff' })
        },
        staffCancel(){
            this.$router.push({ name: 'AdminStaff' })
        },
        staffDelete(){
            //삭제 구현.
            this.$router.push({ name: 'AdminStaff' })
        },
    }
})
</script>