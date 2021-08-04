import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

let kejianrouter = new Router({
  routes: [{
      path: '/',
      name: 'home',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/news',
      name: 'news',
      component: () => import('./views/News.vue'),
    },
    {
      path: '/newsdetails/:id',
      name: 'newsdetails',
      component: () => import('./views/NewsDetails.vue'),
    },
    {
      path: '/product',
      name: 'product',
      component: () => import('./views/Product.vue'),
    },
    {
      path: '/case',
      name: 'case',
      component: () => import('./views/Case.vue')
    },
    {
      path: '/casedetails/:id',
      name: 'casedetails',
      component: () => import('./views/CaseDetails.vue')
    },
    {
      path: '/goin',
      name: 'goin',
      component: () => import('./views/GoIn.vue')
    },
    {
      path: '/download',
      name: 'download',
      component: () => import('./views/Download.vue')
    },
    {
      path: '/goodsList',
      name: 'goodsList',
      component: () => import('./views/GoodsList.vue')
    },
    {
      path: '/adminLogin',
      name: 'adminLogin',
      component: () => import('./views/AdminLogin.vue')
    },
    {
      //雇主注册
      path: '/employerRegister',
      name: 'employerRegister',
      component: () => import('./views/EmployerReg.vue')
    },
    {
      //雇主登陆
      path: '/employerLogin',
      name: 'employerLogin',
      component: () => import('./views/EmployerLog.vue')
    },
    {
      //服务商注册
      path: '/providerRegister',
      name: 'providerRegister',
      component: () => import('./views/ProviderReg.vue')
    },
    {
      //服务商登陆
      path: '/providerLogin',
      name: 'providerLogin',
      component: () => import('./views/ProviderLog.vue')
    },
    {
      path: '/employerCenter',
      name: 'employerCenter',
      meta: {
        requireAuth: true
      },
      component: () => import('./views/Employer/EmployerCenter.vue'),
      children: [{
        path: '/employerCenter/adddemand',
        name: 'adddemand',
        component: () => import('./views/Employer/Children/Adddemand.vue')
      },
        {
          path: '/admin/news',
          name: 'new',
          component: () => import('./views/Admin/News.vue')
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
        }
      ]
    }
  ]
})

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

export default kejianrouter
