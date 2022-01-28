<template>
  <div>
    <navi-bar/>
    <router-view/>
    (게시판관리로 들어왔다!)
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
              <th>지역정보</th>
              <th>데스크</th>
              <th>제목</th>
              <th>내용</th>
              <th>비밀번호</th>
              <th>등록날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="9">
                <button variant="outline-primary" @click="postEdit()">
                 (게시판 정보가 여기 삽입됨)
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
  name: 'AdminPost',
  components: {
    NaviBar,
  },
  methods: {
    postEdit(){
      this.$router.push({ name: 'AdminPostEdit' })
    },
  }
}
</script>
