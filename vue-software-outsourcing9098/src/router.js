import Vue from 'vue'
import Router from 'vue-router'
import EmployerReg from "@/views/EmployerReg";
import ProviderLog from "@/views/ProviderLog";
import EmployerLog from "@/views/EmployerLog";

Vue.use(Router)

let softwareOutsourcing = new Router({
  routes: [{
    path: '/',
    name: 'home',
    component: () => import('./views/Home.vue')
  },
    {
      path: '/home',
      name: 'home',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/goodsList',
      name: 'goodsList',
      component: () => import('./views/GoodsList.vue')
    },
    {
      path: '/worksList',
      name: 'worksList',
      component: () => import('./views/WorksList.vue')
    },
    {
      path: '/worksdetails',
      name: 'worksdetails',
      component: () => import('./views/WorksDetails.vue')
    },
    {
      path: '/demandDetails',
      name: 'demandDetails',
      component: () => import('./views/Provider/DemandDetails.vue')
    },
    {
      //雇主注册
      path: '/EmployerReg',
      name: 'EmployerReg',
      component: EmployerReg
    },
    {
      //雇主登陆
      path: '/employerLog',
      name: 'employerLog',
      component: EmployerLog
    },
    {
      //服务商注册
      path: '/providerReg',
      name: 'providerReg',
      component: () => import('./views/ProviderReg.vue')
    },
    {
      //服务商登陆
      path: '/providerLog',
      name: 'providerLog',
      component: ProviderLog
    },
    {
      //雇主中心
      path: '/employerCenter',
      name: 'employerCenter',
      meta: {
        requireAuth: true
      },
      component: () => import('./views/Employer/EmployerCenter.vue'),
      children: [
        {
          path: '/employerCenter/information',
          name: 'information',
          component: () => import('./views/Employer/Children/Information.vue')
        },
        {
          path: '/employerCenter/adddemand',
          name: 'adddemand',
          component: () => import('./views/Employer/Children/Adddemand.vue')
        },
        {
          path: '/employerCenter/accountview',
          name: 'accountview',
          component: () => import('./views/Employer/Children/Accountpreview.vue'),
        },
        {
          path: '/employerCenter/deposit',
          name: 'deposit',
          component: () => import('./views/Employer/Children/deposit.vue'),
        },
        {
          path: '/employerCenter/updatePwd',
          name: 'updatePwd',
          component: () => import('./views/Employer/Children/updatePwd.vue'),
        },
        {
          path: '/employerCenter/map',
          name: 'map',
          component: () => import('./views/Map.vue')
        },
        {
          path: '/employerCenter/buywork',
          name: 'buywork',
          component: () => import('./views/Employer/Children/Buywork.vue')
        },
        {
          path: '/employerCenter/kefu',
          name: 'kefu',
          component: () => import('./views/Employer/Children/Kefu.vue')
        },
        {
          path: '/employerCenter/liaotian',
          name: 'liaotian',
          component: () => import('./views/Employer/Children/Liaotian.vue')
        },
      ]
    },
    {
      // 服务商个人中心
      path: '/providerCenter',
      name: 'providerCenter',
      component: () => import('./views/Provider/ProviderCenter.vue'),
      children: [
        {
          path: '/providerCenter/PublishWorks',
          name: 'PublishWorks',
          component: () => import('./views/Provider/PublishWorks.vue')
        },
        {
          path: '/providerCenter/updatePwd',
          name: 'updatePwd',
          component: () => import('./views/Provider/updatePwd.vue'),
        },
        {
          path: '/providerCenter/mywork',
          name: 'mywork',
          component: () => import('./views/Provider/Mywork.vue'),
        },
        {
          path: '/providerCenter/map',
          name: 'map',
          component: () => import('./views/Map.vue')
        },
        {
          path: '/providerCenter/accountviewp',
          name: 'accountviewp',
          component: () => import('./views/Provider/Accountpreview.vue')
        },
        {
          path: '/providerCenter/depositp',
          name: 'depositp',
          component: () => import('./views/Provider/deposit.vue')
        },
      ]
    },




  ]
})

// 判断是否需要登录权限 以及是否登录
softwareOutsourcing.beforeEach((to, from, next) => {
  // 判断是否需要登录权限
  if (to.matched.some(res => res.meta.requireAuth)) {
    // 判断是否登录
    if (sessionStorage.getItem('token')) {
      next()
    } else {
      // 没登录则跳转到登录界面
      next({
        path: '/home',
        query: {
          redirect: to.fullPath
        }
      })
    }
  } else {
    next()
  }
})

export default softwareOutsourcing
