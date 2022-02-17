<template>


      <!-- detail Modal -->
      <post-detail :selectedPost="state.selectedPost" @post-updated="postUpdated"></post-detail>
      
      <!-- Post List -->
      <div class="padding-postlist">
        <!-- <div class="row" v-for="(list, idx) in listTen" :key="idx" :list="list"></movie-list> -->

        <div class="row" style="padding-left: 6rem;">
          <post-list-item
            v-for="post in postList"
            :key="post.id"
            :post="post"
            @click="selectPost(post)"
          >
          </post-list-item>
        </div>
        <!-- <div class="row">
          <post-list-item
            v-for="post in postList"
            :key="post.id"
            :post="post"
            @click="selectPost(post)"
          >
          </post-list-item>
        </div> -->

<!--     
      <nav aria-label="..." class="d-flex justify-content-center">
      <ul class="pagination d-flex justify-content-between">
        <li v-if="start" class="page-item">
          <a class="page-link" href="#" @click="gotostartpage">«</a>
        </li>
        <li v-else class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">«</a>
        </li>

        <li v-if="pre" li class="page-item">
          <a class="page-link" href="#" @click="gotoprepage">‹</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">‹</a>
        </li>

        <li
         v-for="pageitem in pageNumbers"
          v-bind:id="'p'+pageitem"
          v-bind:class="{' active': pageitem == nowPage}"
          :key="pageitem"
         class="page-item"><a class="page-link" href="#" @click="thispage(pageitem)">{{pageitem}}</a></li>
        
        
        <li v-if="next" class="page-item">
          <a class="page-link" href="#" @click="gotonextpage">›</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">›</a>
        </li>

        <li v-if="end" class="page-item">
          <a class="page-link" href="#" @click="gotoendpage">»</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">»</a>
        </li>
      </ul>
    </nav>   -->

      </div>

</template>

<script>
import { ref } from 'vue'
import PostListItem from '@/components/post/PostListItem'
import PostDetail from '@/components/post/PostDetail'


export default {
  name: 'PostList',
  components: { PostListItem, PostDetail},
  props: ["postList"],
  // data () {
  //   return {
  //     pageNumbers: [],
  //     totalPage: undefined,
  //     totalCount: undefined,
  //     nowPage: undefined,
  //     startPage: undefined,
  //     endPage: undefined,
  //     pre: false,
  //     next: true,
  //     start: false,
  //     end: true,  
  //   }
  // },
  setup(props, {emit}) {
    const state = ref({
      selectedPost: {}
    })
    
    const selectPost = (post) => {
      console.log(`${post.id}번 포스트 선택됨`)
      state.value.selectedPost = post
      console.log(state.value.selectedPost)
    }

    const postUpdated = () => {
      emit("post-updated")
    }

    return {state, selectPost, postUpdated}
  },
  //   methods: {
  //   deskCreate(){
  //     this.$router.push({ name: 'AdminDeskCreate' })
  //   },
  //   thispage(np){
  //     this.nowPage= np
  //     const obj = {deskId:deskId, nowPage: this.nowPage }
      
  //     console.log(response.data);
  //       this.articles = response.data.data;
  //       this.totalPage = response.data.totalPage;
  //       this.totalCount = response.data.totalCount;
  //       this.nowPage = response.data.nowPage;
  //       this.startPage = response.data.startPage;
  //       this.endPage = response.data.endPage;
  //       this.pre = response.data.pre;
  //       this.next = response.data.next;
  //       this.start = response.data.start;
  //       this.end = response.data.end;
  //       this.pageNumbers = []
  //       for(var i =this.startPage; i<=this.endPage;i++){
  //         this.pageNumbers[i-this.startPage] = i
  //       }
  //   },
  //   gotoprepage(){
  //     if(this.nowPage-5 < 1 ){
  //       this.thispage(1)
  //     }else{
  //       this.thispage(this.nowPage-5)
  //     }
      
  //   },
  //   gotonextpage(){
  //     if(this.nowPage+5 > this.totalPage){
  //       this.thispage(this.totalPage)
  //     }else{
  //       this.thispage(this.nowPage+5)
  //     }
  //   },
  //   gotostartpage(){
  //     this.thispage(1)
  //   },
  //   gotoendpage(){
  //     this.thispage(this.totalPage)
  //   }
  // }
}
</script>
<style scoped>
  /* .wall-background {
    background-image: url('../../assets/wall.jpg');
    background-repeat : no-repeat;
    background-size: cover;
    height: auto;
  } */



  .padding-postlist {
    padding-left: 6%;
    padding-right: 6%;
    padding-top: 8%
  }
</style>
