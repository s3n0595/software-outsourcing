import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import Axios from 'axios'
import qs from 'qs'

Vue.prototype.$axios = Axios;

Axios.defaults.baseURL = 'api';

// 导入 ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import App from './App'
// import Axios from "axios";

// 安装路由
Vue.use(VueRouter);

// 安装 ElementUI
Vue.use(ElementUI);

Vue.prototype.$qs = qs;

new Vue({
  el: '#app',
  // 启用路由
  router,
  // 启用 ElementUI
  render: h => h(App)
});
