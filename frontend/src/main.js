import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import jQuery from 'jquery'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import '../src/common/css/reset.css'
import '../src/common/css/common.css'

createApp(App).use(store).use(router).mount('#app') //.use(jQuery)
global.$ = jQuery