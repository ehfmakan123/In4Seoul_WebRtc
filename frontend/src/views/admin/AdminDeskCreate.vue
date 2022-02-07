<template>
    <div>(데스크생성하기)</div>

    <div>
        <div>데스크 아이디: <input></div>
        <div>이름: <input></div>
        <div>비밀번호: <input type="password" id="pass" name="password"> </div>
        <div>비밀번호 확인:<input  type="password"></div>
        <div>위도 <input></div>
        <div>경도 <input></div>
        <div>지역정보: <select></select> </div>
    </div>
    <button variant="outline-primary" @click="deskInsert()">등록</button>
    <button variant="outline-primary" @click="deskCancel()">취소</button>
</template>

<script>

export default({
    name: 'AdminDeskCreate',
    components: {
    },
    methods: {
        deskInsert(){
            this.$router.push({ name: 'AdminDesk' })
        },
        deskCancel(){
            this.$router.push({ name: 'AdminDesk' })
        },
    }
})
</script>
