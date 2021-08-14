import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: {title: '自述文件'},
            children: [
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: {title: '系统首页'}
                },
                {
                    path: '/icon',
                    component: resolve => require(['../components/page/Icon.vue'], resolve),
                    meta: {title: '自定义图标'}
                },
                // {
                //     path: '/table',
                //     component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                //     meta: {title: '基础表格'}
                // },
                {
                    path: '/tabs',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: {title: 'tab选项卡'}
                },
                // {
                //     path: '/form',
                //     component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                //     meta: {title: '基本表单'}
                // },
                // {
                //     path: '/tree1',
                //     component: resolve => require(['../components/page/tree1.vue'], resolve),
                //     meta: {title: '基本树'}
                // },
                // {
                //     path: '/tree2',
                //     component: resolve => require(['../components/page/tree2.vue'], resolve),
                //     meta: {title: '可选择树'}
                // },
                // {
                //     path: '/tree3',
                //     component: resolve => require(['../components/page/tree3.vue'], resolve),
                //     meta: {title: '可编辑树'}
                // },
                // {
                //     // 富文本编辑器组件
                //     path: '/tree4',
                //     component: resolve => require(['../components/page/tree4.vue'], resolve),
                //     meta: {title: '可查询树'}
                // },
                // {
                //     // 富文本编辑器组件
                //     path: '/tree5',
                //     component: resolve => require(['../components/page/tree5.vue'], resolve),
                //     meta: {title: '节点选择'}
                // },
                // {
                //     // 富文本编辑器组件
                //     path: '/editor',
                //     component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                //     meta: {title: '富文本编辑器'}
                // },
                // {
                //     // markdown组件
                //     path: '/markdown',
                //     component: resolve => require(['../components/page/Markdown.vue'], resolve),
                //     meta: {title: 'markdown编辑器'}
                // },
                // {
                //     // 图片上传组件
                //     path: '/upload',
                //     component: resolve => require(['../components/page/Upload.vue'], resolve),
                //     meta: {title: '文件上传'}
                // },
                // {
                //     // vue-schart组件
                //     path: '/charts',
                //     component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                //     meta: {title: 'schart图表'}
                // },
                // {
                //     // 拖拽列表组件
                //     path: '/drag',
                //     component: resolve => require(['../components/page/DragList.vue'], resolve),
                //     meta: {title: '拖拽列表'}
                // },
                // {
                //     // 拖拽Dialog组件
                //     path: '/dialog',
                //     component: resolve => require(['../components/page/DragDialog.vue'], resolve),
                //     meta: {title: '拖拽弹框'}
                // },
                // {
                //     // 权限页面
                //     path: '/permission',
                //     component: resolve => require(['../components/page/Permission.vue'], resolve),
                //     meta: {title: '权限测试', permission: true}
                // },
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: {title: '404'}
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: {title: '403'}
                },
                {
                    path: '/admin',
                    component: resolve => require(['../components/page/admin.vue'], resolve),
                    meta: {title: '用户信息'}
                },
                {
                    path: '/providerManage',
                    component: resolve => require(['../components/page/userManage/ProviderManage.vue'], resolve),
                    meta: {title: '服务商管理'}
                },
                {
                    path: '/employer',
                    component: resolve => require(['../components/page/userManage/empmanager'], resolve),
                    meta: {title: '雇主管理'}
                },
                {
                    path: '/userInfoManage',
                    component: resolve => require(['../components/page/userManage/UserInfoManage.vue'], resolve),
                    meta: {title: '用户管理'}
                },
                {
                    path: '/roleManage',
                    component: resolve => require(['../components/page/userManage/RoleManage'], resolve),
                    meta: {title: '角色管理'}
                },
                {
                    path: '/menuManage',
                    component: resolve => require(['../components/page/userManage/MenuManage'], resolve),
                    meta: {title: '菜单管理'}
                },
                {
                    path: '/logManage',
                    component: resolve => require(['../components/page/userManage/LogManage'], resolve),
                    meta: {title: '日志管理'}
                },
                {
                    path: '/demandManage',
                    component: resolve => require(['../components/page/userManage/DemandManage'], resolve),
                    meta: {title: '需求管理'}
                },
                {
                  path: '/worksManage',
                  component: resolve => require(['../components/page/businessManage/worksManage'], resolve),
                    meta:{title: '作品管理'}
                },
                {
                    path: '/unionManage',
                    component: resolve => require(['../components/page/businessManage/UnionManage'], resolve),
                    meta:{title: '联盟管理'}
                },
                {
                    path: '/adviserManage',
                    component: resolve => require(['../components/page/businessManage/AdviserManage'], resolve),
                    meta:{title: '顾问管理'}
                },
                {
                    path: '/report',
                    component: resolve => require(['../components/page/businessManage/report'], resolve),
                    meta:{title: '报表统计'}
                },
                {
                    path: '/tradeRecordManage',
                    component: resolve => require(['../components/page/userManage/TradeRecordManage'], resolve),
                    meta:{title: '投标交易'}
                },
                {
                    path: '/tradeWorkManage',
                    component: resolve => require(['../components/page/userManage/TradeWorkManage'], resolve),
                    meta:{title: '作品交易'}
                },
                {
                    path: '/successfulCases',
                    component: resolve => require(['../components/page/portalManage/SuccessfulCases'], resolve),
                    meta:{title: '成功案例配置'}
                },
                {
                    path: '/tradeRetreatManage',
                    component: resolve => require(['../components/page/userManage/TradeRetreatManage'],resolve),
                    meta:{title: '作品退款'}
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
