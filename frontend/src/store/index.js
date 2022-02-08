import { createStore } from 'vuex'
import axios from 'axios'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default createStore({
  state: {
    isLoggedIn: false,
    isDesk: false,
    isStaff: false,
    isAdmin: false,
    postList: [],
    post: {
      "id":"3",
      "title":"뮤지컬 프랑켄슈타인",
      "content":"블루스퀘어에서 하고있어요 괴물 너무 마음 아파 ㅠㅠ",
      "createdAt":"2022-01-01 11:20:00",
      "updatedAt":"2022-01-01 11:20:00"
    }
    // selectedPost: {},
  },
  mutations: {
    DESK_LOGIN(state) {
      state.isLoggedIn = true
      state.isDesk = true
    },
    STAFF_LOGIN(state) {
      state.isLoggedIn = true
      state.isStaff = true
    },
    ADMIN_LOGIN(state) {
      state.isLoggedIn = true
      state.isAdmin = true
    },
    LOGOUT(state) {
      state.isLoggedIn = false
      state.isDesk = false
      state.isStaff = false
      state.isAdmin = false
    },
    SET_POST_LIST(state, postList) {
      state.postList = postList
    },
    SET_POST(state, post) {
      state.post = post
    }
  },
  actions: {
    desk_login({ commit }) {
      console.log('desk_login action 실행됨!')
      commit('staff_login')
    },
    staff_login({ commit }) {
      console.log('staff_login action 실행됨!')
      commit('STAFF_LOGIN')
    },
    admin_login({ commit }) {
      console.log('admin_login action 실행됨!')
      commit('ADMIN_LOGIN')
    },
    logoutAction({ commit }) {
      commit('LOGOUT')
    },
    fetchPostList({ commit }, deskId, areaId) {
      axios({
        method: 'get',
        url: `${SERVER_HOST}/board/posts/`,
        data: {
          "deskId":`${deskId}`,
          "areaId":`${areaId}`
        }
      })
        .then(res => {
          commit('SET_POST_LIST', res.data)
        })
        .catch(err => console.error(err))
    },
    fetchPost({ commit }, post) {
      commit('SET_POST', post)
    }
  },
  modules: {
  }
})