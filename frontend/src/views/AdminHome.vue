<template>
  <div>
    <div class="bggg"></div>
   <b-navbar toggleable="lg" type="dark" variant="danger">
      <b-navbar-brand href="#">
        <router-link to="/">
          
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'AdminStaff' }" class="link"
              ><b-icon icon="star-fill" animation="fade" font-scale="3"></b-icon> Staff관리 </router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'AdminDesk' }" class="link"
              ><b-icon icon="three-dots" animation="cylon" font-scale="3"></b-icon>
              데스크관리</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'AdminPost' }" class="link"
              ><b-icon icon="circle-fill" animation="throb" font-scale="3"></b-icon>
              게시글관리</router-link
            ></b-nav-item
          >
          
       
        </b-navbar-nav>

      </b-collapse>
      
    </b-navbar>
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
