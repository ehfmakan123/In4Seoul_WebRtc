<template>
    <div class="row flex-nowrap bg-gray-1">
     <AdminSidebar />
        <div class="w-80 p-5">
            <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
        <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt ">스태프 관리＞수정페이지</div>
        <table hover responsive class="p-3 mb-5 bg-body rounded"><tbody>
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
    

        <button variant="outline-primary" @click="staffUpdate()">수정</button>
        <button variant="outline-primary" @click="staffCancel()">취소</button>
        <button variant="outline-primary" @click="staffDelete()">계정삭제</button>
        </div>
    </div>
        
    </div>

</template>

<script>

import AdminSidebar from '@/views/AdminHome.vue'

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
            this.$router.push({ name: 'AdminStaff' })
        },
    }
})
</script>