<template>
  <div>
    <navi-bar/>
    <router-view/>
    (데스크관리로 들어왔다!)
    <br><br>

    <div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
      <b-col class="text-right">
        <button variant="outline-primary" @click="deskCreate()">
          데스크 생성하기
        </button>
      </b-col>
    </b-row>
    <br>
    <b-row>
      <b-col>
        <table hover responsive>
          <thead head-variant="dark">
            <tr>
              <th>No.</th>
              <th>아이디</th>
              <th>비밀번호</th>
              <th>이름</th>
              <th>위도</th>
              <th>경도</th>
              <th>지역정보</th>
              <th>생성날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="9">
                <button variant="outline-primary" @click="deskEdit()">
                 (desk 정보가 여기 삽입됨)
                </button>
              </td>
            </tr>
            <desk-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </table>
      </b-col>
    </b-row>
    
  </b-container>
    </div>

  </div>
</template>

<script>
import NaviBar from '@/views/AdminHome.vue'
// @ is an alias to /src

export default {
  name: 'AdminDesk',
  components: {
    NaviBar,
  },
  methods: {
    deskCreate(){
      this.$router.push({ name: 'AdminDeskCreate' })
    },
    deskEdit(){
      this.$router.push({ name: 'AdminDeskEdit' })
    },
  }
}
</script>
<style>
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
  }
</style>