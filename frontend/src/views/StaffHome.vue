<template>
  <div class="staff-side-bar">
    <button @click="moveToStaffHome">상담기록</button> <tb/>
    <button @click="clickAlarm">상담알림</button> <tb/>
    <button @click="moveToStaffProfile">프로필정보</button>    
  </div>
  <div class="staff-main">
    <h1>상담사 이싸피 상담사 페이지</h1>
    <table
            border="1"
            width="100%"
            height="200">
            <caption>상담기록</caption>
            <thead>
                <tr align="center" bgcolor="white">
                    <th>번호</th>
                    <th>고유번호</th>
                    <th>안내데스크</th>
                    <th>상담시간</th>
                    <th>상담종료시간</th>
                    <th>상담내용</th>
                </tr>
            </thead>

            <tbody>
              <StaffHometableItem v-for="(tableItem, index) in testArray" :key="index" :index="(index+1).toString()" :tableItem="tableItem"/>
            </tbody>
        </table>
  </div>
</template>

<script>
// @ is an alias to /src
import { useRouter} from 'vue-router'
import { ref } from 'vue'
import StaffHometableItem from '@/components/staff/StaffHomeTableItem.vue'

export default {
  name: 'StaffHome',
  components: {
    StaffHometableItem,
  },
  setup() {
    const router = useRouter()
    let testArray = ref([
      {
        "id": "1",
        "deskName": "홍대1번",
        "startTime": "2022-01-14 14:00",
        "endTime": "2022-01-14 14:20",
        "content": "홍대 명소 문의"
      },
      {
        "id": "4",
        "deskName": "홍대2번",
        "startTime": "2022-01-15 14:00",
        "endTime": "2022-01-15 14:20",
        "content": "홍대 지하철역 위치 문의"
      }       
    ])

    const moveToStaffHome = () => {
      console.log("상담기록으로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffHome' })
    }

    const clickAlarm = () => {
      console.log("알람버튼 클릭됨!")
    }

    const moveToStaffProfile = () => {
      console.log("스태프 프로필로 이동 버튼 클릭됨!")
      router.push({ name: 'StaffProfile' })
    }

    return {
      testArray,
      moveToStaffHome,
      clickAlarm,
      moveToStaffProfile,
    }
  }
}
</script>
