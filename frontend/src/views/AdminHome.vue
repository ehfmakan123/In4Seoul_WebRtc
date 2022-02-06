<template>

        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-blue-5">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
              <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline"></span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li class="nav-item">
                      <router-link :to="{ name: 'AdminStaff' }" class="nav-link text-white align-middle px-0"> 
                        <span class="ms-1 d-none d-sm-inline">
                          <i class="bi bi-people-fill fs-3"></i>
                          스태프관리
                        </span>
                        
                      </router-link>
                    </li>
                    <li class="nav-item">
                      <router-link :to="{ name: 'AdminDesk' }" class="nav-link text-white align-middle px-0"> 
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
                    </li>
                </ul>
                <hr>
                <div class="pb-4">
                    <div class="d-flex align-items-center text-white text-decoration-none" type="button"  data-bs-toggle="modal"  data-bs-target="#exampleModal">
                        
                        <span class="d-none d-sm-inline mx-1 text-center">Logout</span>
                    </div>
                    
                </div>
            </div>
      </div>
      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">로그아웃하시겠습니까?</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              ...
            </div>
            <div class="modal-footer">
              
              <button type="button" class="btn btn-primary">네</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
            </div>
          </div>
        </div>
      </div>

</template>

<script>
// @ is an alias to /src

export default {
  name: 'AdminHome',
  components: {
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

  .bd-bt {
    border-bottom: 2px solid #CECECE;
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
    width: 15%;
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
    text-align: center;
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
</style>
