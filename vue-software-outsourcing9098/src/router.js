import Vue from 'vue'
import Router from 'vue-router'
import Nav from "@/views/Nav";
import Home from "@/views/Home";
import EmployerReg from "@/views/EmployerReg";
import GoodsList from "@/views/GoodsList";

Vue.use(Router)


export default new Router({
  routes: [{
    path: '/',
    name: 'home',
    component: () => import('./views/Home.vue')
  },
    {
      path: '/home',
      name: 'home',
      component: Home
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
      path: '/demandDetails/:demand',
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
      component: () => import('./views/EmployerLog.vue')
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
      component: () => import('./views/ProviderLog.vue')
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
      ]
    },

  ]
})


// let kejianrouter = new Router({
//   routes: [{
//     path: '/',
//     name: 'home',
//     component: () => import('./views/Home.vue')
//   },
//     {
//       path: '/news',
//       name: 'news',
//       component: () => import('./views/News.vue'),
//     },
//     {
//       path: '/newsdetails/:id',
//       name: 'newsdetails',
//       component: () => import('./views/NewsDetails.vue'),
//     },
//     {
//       path: '/product',
//       name: 'product',
//       component: () => import('./views/Product.vue'),
//     },
//     {
//       path: '/case',
//       name: 'case',
//       component: () => import('./views/Case.vue')
//     },
//     {
//       path: '/casedetails/:id',
//       name: 'casedetails',
//       component: () => import('./views/CaseDetails.vue')
//     },
//     {
//       path: '/goin',
//       name: 'goin',
//       component: () => import('./views/GoIn.vue')
//     },
//     {
//       path: '/download',
//       name: 'download',
//       component: () => import('./views/Download.vue')
//     },
//     {
//       path: '/goodsList',
//       name: 'goodsList',
//       component: () => import('./views/GoodsList.vue')
//     },
//     {
//       path: '/adminLogin',
//       name: 'adminLogin',
//       component: () => import('./views/AdminLogin.vue')
//     },
//
//
//
//
//   ]
// })

// 判断是否需要登录权限 以及是否登录
// kejianrouter.beforeEach((to, from, next) => {
//   // 判断是否需要登录权限
//   if (to.matched.some(res => res.meta.requireAuth)) {
//     // 判断是否登录
//     if (sessionStorage.getItem('token')) {
//       next()
//     } else {
//       // 没登录则跳转到登录界面
//       next({
//         path: '/login',
//         query: {
//           redirect: to.fullPath
//         }
//       })
//     }
//   } else {
//     next()
//   }
// })

// export default kejianrouter
