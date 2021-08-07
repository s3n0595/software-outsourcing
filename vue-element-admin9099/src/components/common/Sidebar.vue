<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#20222A"
      text-color="rgba(255,255,255,.7)"
      active-text-color="#fff"
      unique-opened
      router
    >
<!--  开始遍历菜单-->
      <template v-for="item in items">
<!--    判断是否有二级菜单-->
<!--    存在二级菜单-->
        <template v-if="item.subs">
<!--      先显示出以级菜单-->
          <el-submenu :index="item.index+''" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
<!--        遍历二级菜单-->
            <template v-for="subItem in item.subs">
<!--          判断二级菜单下是否存在三级菜单-->
<!--          存在-->
              <el-submenu v-if="subItem.subs" :index="subItem.index+''" :key="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item
                  v-for="(threeItem,i) in subItem.subs"
                  :key="i"
                  :index="threeItem.index+''"
                >{{ threeItem.title }}</el-menu-item>
              </el-submenu>
<!--          不存在-->
              <el-menu-item v-else :index="subItem.index+''" :key="subItem.index">{{ subItem.title }}</el-menu-item>
            </template>
          </el-submenu>
        </template>
<!--    不存在二级菜单-->
        <template v-else>
          <el-menu-item :index="item.index+''" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "../common/bus";
import {getMenu} from '../../api/api'
export default {
  data() {
    return {
      // 是否收起菜单
      collapse: false,
      menuItems: [],
      items: [
        {
          icon: "el-icon-menu",
          index: "dashboard",
          title: "系统首页"
        },
        // {
        //   icon: "el-icon-lx-settings",
        //   index: "11",
        //   title: "系统管理",
        //   subs: [
        //     {
        //       index: "userInfoManage",
        //       title: "用户管理"
        //     },
        //     {
        //       index: "roleManage",
        //       title: "角色管理"
        //     },
        //   ]
        // },
        // {
        //   icon: "el-icon-lx-calendar",
        //   index: "8",
        //   title: "用户管理",
        //   subs: [
        //     {
        //       index: "employer",
        //       title: "雇主管理"
        //     },
        //     {
        //       index: "providerManage",
        //       title: "服务商管理"
        //     }
        //   ]
        // },
        // {
        //   icon: "el-icon-lx-cascades",
        //   index: "table",
        //   title: "基础表格"
        // },
        // {
        //   icon: "el-icon-lx-copy",
        //   index: "tabs",
        //   title: "tab选项卡"
        // },
        // {
        //   icon: "el-icon-lx-calendar",
        //   index: "3",
        //   title: "表单相关",
        //   subs: [
        //     {
        //       index: "form",
        //       title: "基本表单"
        //     },
        //     {
        //       index: "3-2",
        //       title: "三级菜单",
        //       subs: [
        //         {
        //           index: "editor",
        //           title: "富文本编辑器"
        //         },
        //         {
        //           index: "markdown",
        //           title: "markdown编辑器"
        //         }
        //       ]
        //     },
        //     {
        //       index: "upload",
        //       title: "文件上传"
        //     }
        //   ]
        // },
        // {
        //   icon: "el-icon-lx-calendar",
        //   index: "4",
        //   title: "树形插件",
        //   subs: [
        //     {
        //       index: "tree1",
        //       title: "基本树"
        //     },
        //     {
        //       index: "tree2",
        //       title: "可选择树"
        //     },
        //     {
        //       index: "tree3",
        //       title: "可编辑树"
        //     },
        //     {
        //       index: "tree4",
        //       title: "可查询树"
        //     },
        //     {
        //       index: "tree5",
        //       title: "节点选择"
        //     }
        //   ]
        // },
        // {
        //   icon: "el-icon-lx-emoji",
        //   index: "icon",
        //   title: "自定义图标"
        // },
        // {
        //   icon: "el-icon-lx-favor",
        //   index: "charts",
        //   title: "schart图表"
        // },
        // {
        //   icon: "el-icon-rank",
        //   index: "6",
        //   title: "拖拽组件",
        //   subs: [
        //     {
        //       index: "drag",
        //       title: "拖拽列表"
        //     },
        //     {
        //       index: "dialog",
        //       title: "拖拽弹框"
        //     }
        //   ]
        // },
        // {
        //   icon: "el-icon-lx-warn",
        //   index: "7",
        //   title: "错误处理",
        //   subs: [
        //     {
        //       index: "permission",
        //       title: "权限测试"
        //     },
        //     {
        //       index: "404",
        //       title: "404页面"
        //     }
        //   ]
        // },
      ]
    };
  },
  methods: {
    // 动态获取菜单
    getMenuDate(){
      let user = JSON.parse(sessionStorage.getItem('user'));
      let params = {
        roleId: user.roleId,
      }
      getMenu(params).then(res=>{
        let menuData = res.data.menuList;
        menuData.forEach(val=>{
          if (0 == val.parentId){
            let i = this.items.length+1;
            let systemItem = {};
            systemItem.icon = val.menuIcon;
            systemItem.index = i;
            systemItem.title = val.menuName;
            systemItem.subs = [];
            menuData.forEach(val2=>{
              if (val.menuId == val2.parentId){
                let systemSubs = {};
                systemSubs.index = val2.menuPath;
                systemSubs.title = val2.menuName;
                systemItem.subs.push(systemSubs);
              }
            });
            this.items.push(systemItem);
          }
        })
      })

    },
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
    this.getMenuDate();
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
