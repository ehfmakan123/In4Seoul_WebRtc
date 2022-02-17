<template>
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-blue-5">
            <div class="d-flex flex-column align-items-center align-items-center px-3 pt-2 text-white min-vh-100">
              
              <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline"></span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li v-if="currentTab =='0'" class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminStaff"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-people-fill fs-3"></i>
                          스태프관리
                        </span>
                        
                      </div>
                    </li>
                    <li v-else class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminStaff"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-people-fill fs-3"></i>
                          스태프관리ㅁ
                        </span>
                        
                      </div>
                    </li>

                    <li v-if="currentTab =='1'" class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminDesk"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-info-circle-fill fs-3"></i>
                          데스크관리
                        </span>
                      </div>
                    </li>
                    <li v-else class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminDesk"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-info-circle-fill fs-3"></i>
                          데스크관리
                        </span>
                      </div>
                    </li>


                    <li v-if="currentTab =='2'" class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminPost"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-clipboard-fill fs-3"></i>
                          게시글관리ㅁ
                        </span>
                      </div>
                    </li>
                    <li v-else class="nav-item">
                      <div type="button" class="nav-link text-white align-middle px-0" @click="moveToAdminPost"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-clipboard-fill fs-3"></i>
                          게시글관리
                        </span>
                      </div>
                    </li>
                    <li class="nav-item ms-2 mt-1">
                    <div class="d-flex justify-content-center align-items-center text-white text-decoration-none" type="button"  data-bs-toggle="modal"  data-bs-target="#adminLogoutModal">
                      <i class="bi bi-box-arrow-right fs-2"></i>
                      <span class="fs-5 d-none d-sm-inline mx-1 text-center">Logout</span>
                  </div>
                    </li>
                    <!-- <li class="nav-item">
                      <router-link :to="{ name: 'AdminStaff' }" class="nav-link text-white align-middle px-0" v-on:click="currentTab= 0"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-people-fill fs-3"></i>
                          스태프관리2
                        </span>
                        
                      </router-link>
                    </li> 
 
                    <li class="nav-item">
                      <router-link :to="{ name: 'AdminDesk' }" class="nav-link text-white align-middle px-0" v-on:click="currentTab= 1"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-info-circle-fill fs-3"></i>
                          데스크관리
                        </span>
                      </router-link>
                    </li>


                    <li class="nav-item">
                      <router-link :to="{ name: 'AdminPost' }" class="nav-link text-white align-middle px-0"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-clipboard-fill fs-3"></i>
                          게시글관리
                        </span>
                      </router-link>
                    </li>-->
                </ul>
                <hr>
                
                

            </div>
              <!-- <div class="pb-4">
                  <div class="d-flex justify-content-center  align-items-center text-white text-decoration-none" type="button"  data-bs-toggle="modal"  data-bs-target="#adminLogoutModal">
                      
                      <span class="d-none d-sm-inline mx-1 text-center">Logout</span>
                  </div>
                  
              </div>             -->
      </div>
      <!-- Modal -->
      <div class="modal fade" id="adminLogoutModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="staff-login-modal-label"><strong class="t-blue-3">로그아웃</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" @click="logout">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>

</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { Modal } from 'bootstrap'

export default {
  name: 'AdminSidebar',
  data(){
    return {
      currentTab: 0, //기본값 0.
      tabs: ['tabs1', 'tabs2', 'tabs3']
    }
  },
  components: {
  },  
  setup() {
    const router = useRouter()
    const store = useStore()

    const moveToAdminStaff = () => {
      //this.$currentTab= 0;
      router.push({ name: 'AdminStaff' })
    }

    const moveToAdminDesk = () => {
      //this.$currentTab= 1;
      router.push({ name: 'AdminDesk' })
    }

    const moveToAdminPost = () => {
      //this.$currentTab= 2;
      router.push({ name: 'AdminPost' })
    }


    const logout = () => {
      localStorage.clear()
      //localStorage.removeItem('token')
      router.push({ name: 'AdminAuth' })
      store.dispatch("logoutAction")
      // adminLogoutModal 끄기
      const adminLogoutModal = document.querySelector('#adminLogoutModal')
      let modal = Modal.getOrCreateInstance(adminLogoutModal)
      modal.hide()
    }

    return {
      moveToAdminStaff,
      moveToAdminDesk,
      moveToAdminPost,
      logout,
    }
  }
}
</script>

<style>
  /*table 지정 색*/
  .table-class {
    width: 100%;
    text-align: center;
  }
  .table-class:hover tbody tr:hover td {
    background: #CECECE;
    color: #656565;
  }
  .bt-pdd{
    padding-right: 1.5rem!important;
    padding-left: 1.5rem!important;
  }
  .bd-bt {
    border-bottom: 2px solid #CECECE;
  }
  .bd-top {
    border-top: 2px solid #CECECE;
  }
  .th-class > tr > th {
    padding: 1rem!important;
  } 
  .tr-class {
    border-top: 1px solid #CECECE!important;
    padding: 1rem!important;
  }
  .tr-class > td {
    padding: inherit;
  }
  .tr-info > td:nth-child(1) {
    width: 18%;
    padding: 1rem!important;
    padding-left: 3rem!important;
    padding-right: 3rem!important;
    text-align: center;
  }
  .tr-info >td:nth-child(2) {
    padding: 0.5rem!important;
  }
  .form-10{
    width: 10% !important;
    text-align: center;
  }
  .form-25{
    width: 25% !important;
  }
  .w-80 {
    width: 80%!important;
  }
  /* th, td {
    border: 1px solid #444444;
  } */
    /* admin 지정색 */
  /*승인 YN*/
  .table-yes{
    color: #00AC45;
  }
  .modal-rounded{
    border-radius: 1rem !important;
    border-width: 2px;
  }
  .btn-yes-no {
    width:25%!important;
    border-radius:2rem!important;
  }
</style>