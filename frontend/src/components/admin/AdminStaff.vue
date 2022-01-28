<template>
  <div>
    <navi-bar/>
    <router-view/>
    (staff 관리로 들어왔다!)
    <br><br>

    <div>
    <b-container class="bv-example-row mt-3">

    <br>
    <b-row>
      <b-col>
        <table hover responsive>
          <thead head-variant="dark">
            <tr>
              <th>No.</th>
              <th>UserID</th>
              <th>이름</th>
              <th>email</th>
              <th>휴대폰번호</th>
              <th>지역코드</th>
              <th>지역명</th>
              <th>승인</th>
              <th>생성날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="10">
                <button variant="outline-primary" @click="staffEdit()">
                 (staff 정보가 여기 삽입됨)
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
  name: 'AdminStaff',
  components: {
    NaviBar,
  },
  methods: {
    staffEdit(){
      this.$router.push({ name: 'AdminStaffEdit' })
    },
  }
}
</script>
