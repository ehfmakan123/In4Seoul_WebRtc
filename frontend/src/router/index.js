import { createRouter, createWebHistory } from 'vue-router'
import Auth from '@/views/Auth.vue'
import DeskHome from '@/views/DeskHome.vue'
import Meeting from '@/views/Meeting.vue'
import Post from '@/views/Post.vue'
import StaffHome from '@/views/StaffHome.vue'
import StaffProfile from '@/views/StaffProfile.vue'
import StaffProfileEdit from '@/views/StaffProfileEdit.vue'
import AdminAuth from '@/views/AdminAuth.vue'
import AdminHome from '@/views/AdminHome.vue'
import AdminStaff from '@/components/admin/AdminStaff.vue'
import AdminDesk from '@/components/admin/AdminDesk.vue'
import AdminPost from '@/components/admin/AdminPost.vue'
import AdminStaffEdit from '@/components/admin/AdminStaffEdit.vue'
import AdminDeskCreate from '@/components/admin/AdminDeskCreate.vue'
import AdminDeskEdit from '@/components/admin/AdminDeskEdit.vue'
import AdminPostEdit from '@/components/admin/AdminPostEdit.vue'



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
    component: AdminAuth
  },
  {
    path: '/admin/',
    name: 'AdminHome',
    component: AdminHome
  },
  {
    path: '/admin/staff',
    name: 'AdminStaff',
    component: AdminStaff
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