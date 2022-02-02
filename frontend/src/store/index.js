import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    postList: [],
    post: null,
    serverHost: 'http://127.0.0.1:8000',
  },
  mutations: {
    SET_POST_LIST(state, postList) {
      state.postList = postList
    },
    SET_POST(state, post) {
      state.post = post
    }
  },
  actions: {
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
    fetchPost({ state, commit }, postId) {
      axios({
        method: 'get',
        url: `${state.serverHost}/board/posts/${postId}/`,
      })
        .then(res => {
          commit('SET_POST', res.data)
        })
        .catch(err => console.error(err))
    }
  },
  modules: {
  }
})
