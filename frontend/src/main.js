import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import '../src/common/css/reset.css'
import '../src/common/css/common.css'
// firebase
import firebase from 'firebase/app'
import 'firebase/messaging'

//import axios from 'axios'
//const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

// --Firebase 코드 시작
const firebaseConfig = {
  apiKey: "AIzaSyDxJhsqdI1EY9CUpQH1GwhjgDaH3LaeCrY",
  authDomain: "ssafy-project-614a2.firebaseapp.com",
  projectId: "ssafy-project-614a2",
  storageBucket: "ssafy-project-614a2.appspot.com",
  messagingSenderId: "418108971907",
  appId: "1:418108971907:web:fdff0f5c5f9356668e34b6",
  measurementId: "G-65223SW7WZ"
};

firebase.initializeApp(firebaseConfig)

const messaging = firebase.messaging()

// messaging.usePublicVapidKey('BK_qGmvUaOH-PiEnOcd5Or0eLaUIcLsucSVPOC8mySPxp1MEAroR9K6btphRoGo3Q7OLYd9iFT_O0u-8OdSSxAU')

// 알림 수신을 위한 사용자 권한 요청
Notification.requestPermission()
  .then((permission) => {
    console.log('permission ', permission)
    if (permission !== 'granted') {
      alert('알림을 허용해주세요')
    }
  })

// TODO: Send token to server for send notification
// messaging.getToken({vapidKey: "BK_qGmvUaOH-PiEnOcd5Or0eLaUIcLsucSVPOC8mySPxp1MEAroR9K6btphRoGo3Q7OLYd9iFT_O0u-8OdSSxAU"})
//   .then(console.log)

// Handle received push notification at foreground
messaging.onMessage(payload => {
  console.log("main.js 메시지 수신!!")
  console.log(payload)
  // console.log("제목: ", payload.notification.title)
  // console.log("제목: ", payload.data.title)
  // alert(payload.notification.title)
})
// --Firebase 코드 끝




// 브라우저 종료시 localStorage 초기화
// window.addEventListener('unload',()=>{


//   const deskData=JSON.parse(localStorage.getItem("deskData"));
  
//  if(deskData==null)
//  {
//  localStorage.clear();
//  }
// })

// window.addEventListener('beforeunload',()=>{


//   const deskData=JSON.parse(localStorage.getItem("deskData"));
  
//  if(deskData==null)
//  {
//   const jwtToken = localStorage.getItem('token')
//   axios({
//     method: 'post',
//     url: `${SERVER_HOST}/staff/logout`,
//     headers : {
//       Authorization: `Bearer ${jwtToken}` 
//     }
//   })
//     .then((res) => {
//       console.log(res.data)
//       localStorage.clear();
//     }).catch((err) => console.log(err))
//  }



// })

// window.onbeforeunload = function() {

//   const deskData=JSON.parse(localStorage.getItem("deskData"));
//  if(confirm("test")){
//   if(deskData==null)
//  {
//   const jwtToken = localStorage.getItem('token')
//   axios({
//     method: 'post',
//     url: `${SERVER_HOST}/staff/logout`,
//     headers : {
//       Authorization: `Bearer ${jwtToken}` 
//     }
//   })
//     .then((res) => {
//       console.log(res.data)
//       localStorage.clear();
//     }).catch((err) => console.log(err))
//  }
//  } 
 
//   return '';
// };



createApp(App).use(store).use(router).mount('#app')