<template>
    <div>(게시판 상세 페이지)</div>

    <div>
        <div>지역정보: <input></div>
        <div>데스크정보: <input></div>
        <div>제목: </div>
        <div>내용: </div>
        <div>비밀번호: </div>
        <div>등록날짜:  </div>
        <div>수정날짜: </div>
    </div>
    <button variant="outline-primary" @click="postUpdate()">수정</button>
    <button variant="outline-primary" @click="postCancel()">취소</button>
</template>

<script>

export default({
    name: 'AdminPostEdit',
    components: {
    },
    methods: {
        postUpdate(){
            this.$router.push({ name: 'AdminPost' })
        },
        postCancel(){
            this.$router.push({ name: 'AdminPost' })
        },
    }
})
</script>