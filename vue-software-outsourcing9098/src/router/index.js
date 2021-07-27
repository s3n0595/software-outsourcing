import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Index from "../views";
import Register from "../views/Register";
import EmployerReg from "../views/EmployerReg";
import ProviderReg from "../views/ProviderReg";
import UserAgreement from "../views/UserAgreement";
import EmployerLog from "../views/EmployerLog";
import ProviderLog from "../views/ProviderLog";

Vue.use(Router)

export default new Router({
  mode:"history",
  routes: [
    {
      //首页
      path:'/',
      name:'Index',
      component:Index
    },
    {
      //雇主登陆
      path:'/login',
      name:'Login',
      component:Login
    },
    {
      //注册
      path:'/register',
      name:'Register',
      component:Register
    },
    {
      //雇主注册页面
      path:'/employerReg',
      name:'EmployerReg',
      component:EmployerReg
    },
    {
      //服务商注册界面
      path:'/providerReg',
      name:'ProviderReg',
      component:ProviderReg
    },
    {
      //用户服务协议
      path:'/userAgreement',
      name:'UserAgreement',
      component:UserAgreement
    },
    {
      //登陆雇主账号
      path:'/employerLog',
      name:'EmployerLog',
      component:EmployerLog
    },
    {
      //登陆服务商账号
      path:'/providerLog',
      name:'ProviderLog',
      component:ProviderLog
    }

  ]
})
