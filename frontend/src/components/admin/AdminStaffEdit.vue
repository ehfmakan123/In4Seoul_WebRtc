<template>
        <div>(staff 상세 페이지)</div>

    <div>
        <div>데스크 아이디: <input></div>
        <div>이름: <input></div>
        <div>비밀번호: <input type="password" id="pass" name="password"> </div>
        <div>비밀번호 확인:<input  type="password"></div>
        <div>위도 <input></div>
        <div>경도 <input></div>
        <div>지역정보: <select></select> </div>
        <div>등록날짜:  </div>
        <div>수정날짜: </div>
    </div>
    <button variant="outline-primary" @click="staffUpdate()">수정</button>
    <button variant="outline-primary" @click="staffCancel()">취소</button>
    <button variant="outline-primary" @click="staffDelete()">계정삭제</button>
</template>

<script>

export default({
    name: 'AdminStaffEdit',
    components: {
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