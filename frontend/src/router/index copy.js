import { createRouter, createWebHistory } from 'vue-router' //, Router 
import Auth from '@/views/Auth.vue'
import DeskHome from '@/views/DeskHome.vue'
import Meeting from '@/views/Meeting.vue'
import Post from '@/views/Post.vue'
import StaffHome from '@/views/StaffHome.vue'
import StaffProfile from '@/views/StaffProfile.vue'
import StaffProfileEdit from '@/views/StaffProfileEdit.vue'
import AdminAuth from '@/views/AdminAuth.vue'
import AdminStaff from '@/views/admin/AdminStaff.vue'
import AdminDesk from '@/views/admin/AdminDesk.vue'
import AdminPost from '@/views/admin/AdminPost.vue'
import AdminStaffEdit from '@/views/admin/AdminStaffEdit.vue'
import AdminDeskCreate from '@/views/admin/AdminDeskCreate.vue'
import AdminDeskEdit from '@/views/admin/AdminDeskEdit.vue'
import AdminPostEdit from '@/views/admin/AdminPostEdit.vue'
//import store from '../store/index'
//import Vue from 'vue'

//import { VueElement } from 'vue'
//import { useStore } from 'vuex'

// const store = useStore()

// const adminrequireAuth = () => (from, to, next) => {
//   //const isAuthenticated = false
//   if (store.createStore.state.isAdmin == false) return next()
//   next('/login?returnPath=me')
// }

//Vue.use(Router);

// const requireAdminAuth = () => (to, from, next) => {
//   //if (store.state.accessToken !== '') {
//   console.log("이동!")
//   if (store.state.isAdmin == true) {
//     return next();
//   }
//   next('/login');
// };

const routes = [
  
  {
    path: '/',
    name: 'Auth',
    component: Auth
  },
  {
    path: '/desk',
    name: 'DeskHome',
    component: DeskHome
  },
  {
    path: '/meeting',
    name: 'Meeting',
    component: Meeting
  },
  {
    path: '/post',
    name: 'Post',
    component: Post
  },
  {
    path: '/staff',
    name: 'StaffHome',
    component: StaffHome
  },
  {
    path: '/staff/profile',
    name: 'StaffProfile',
    component: StaffProfile
  },
  {
    path: '/staff/profile/edit',
    name: 'StaffProfileEdit',
    component: StaffProfileEdit
  },
  {
    path: '/admin/login',
    name: 'AdminAuth',
    component: AdminAuth,
    //beforeEnter: adminrequireAuth(),
  },
  // {
  //   path: '/admin/',
  //   name: 'AdminStaff',
  //   component: AdminStaff
  // },
  {
    path: '/admin/staff',
    name: 'AdminStaff',
    component: AdminStaff,
    //beforEnter: requireAdminAuth(),
  },
  {
    path: '/admin/desk',
    name: 'AdminDesk',
    component: AdminDesk
  },
  {
    path: '/admin/post',
    name: 'AdminPost',
    component: AdminPost
  },
  {
    path: '/admin/staff/edit',
    name: 'AdminStaffEdit',
    component: AdminStaffEdit
  },
  {
    path: '/admin/desk/edit',
    name: 'AdminDeskEdit',
    component: AdminDeskEdit
  },
  {
    path: '/admin/desk/create',
    name: 'AdminDeskCreate',
    component: AdminDeskCreate
  },
  {
    path: '/admin/post/edit',
    name: 'AdminPostEdit',
    component: AdminPostEdit
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

// const adminrequireAuth = () => (from, to, next) => {
//   const isAuthenticated = this.$store.state.isAdmin
//   if (isAuthenticated) return next()
//   next('admin/login?returnPath=me')
// }






// {
//   path: "/board",
//   name: "Board",
//   component: Board,
//   redirect: "/board/list",
//   children: [
//     {
//       path: "list",
//       name: "BoardList",
//       component: BoardList,
//     },
//     {
//       path: "write",
//       name: "BoardWrite",
//       beforeEnter: onlyAuthUser,
//       component: BoardWrite,
//     },
//     {
//       path: "detail/:articleno",
//       name: "BoardView",
//       beforeEnter: onlyAuthUser,
//       component: BoardView,
//     },
//     {
//       path: "update/:articleno",
//       name: "BoardUpdate",
//       beforeEnter: onlyAuthUser,
//       component: BoardUpdate,
//     },
//   ],
// },


// // https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
// const onlyAuthUser = async (to, from, next) => {
// // console.log(store);
// const checkUserInfo = store.getters["memberStore/checkUserInfo"];
// const getUserInfo = store._actions["memberStore/getUserInfo"];
// let token = sessionStorage.getItem("access-token");
// if (checkUserInfo == null && token) {
//   await getUserInfo(token);
// }
// if (checkUserInfo === null) {
//   alert("로그인이 필요한 페이지입니다..");
//   // next({ name: "SignIn" });
//   router.push({ name: "SignIn" });
// } else {
//   console.log("로그인 했다.");
//   next();
// }
// };

