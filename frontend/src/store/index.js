import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    isLoggedIn: false,
    config: {},
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
    LOGIN: function (state, config) {
      state.isLoggedIn = true
      state.config = config
    },
    SET_POST_LIST(state, postList) {
      state.postList = postList
    },
    SET_POST(state, post) {
      state.post = post
    }
  },
  actions: {
    loginAction({ commit }) {
      console.log('loginAction 실행됨!')
      const token = localStorage.getItem("accessToken")
      console.log(token)
      const config = {
        Authorization: `Bearer ${token}`
      }
      commit('LOGIN', config)
    },
    fetchPostList({ state, commit }, deskId, areaId) {
      axios({
        method: 'get',
        url: `${state.serverHost}/board/posts/`,
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