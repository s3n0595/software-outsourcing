<template>
  <el-header>
    <div class="logo">
      <img src="../assets/img/chinamobile.png" alt/>
    </div>
    <el-menu
        :default-active="defaultActive"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
    >
      <el-menu-item @click="home">首页</el-menu-item>
      <el-menu-item index="/news">新闻资讯</el-menu-item>
      <el-menu-item index="/product">产品中心</el-menu-item>
      <el-menu-item index="/case">经典案例</el-menu-item>
      <el-menu-item index="/goin">走进科建</el-menu-item>
      <el-menu-item index="/download">下载APP</el-menu-item>

    </el-menu>

    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户头像 -->
        <div class="user-avator">
          <img src="../assets/img/img.jpg">
        </div>
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name" trigger="click" >
          <span class="el-dropdown-link">
<!--            {{username}}-->
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="personCenter">用户信息</el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </div>
    </div>


  </el-header>
</template>


<script>
export default {
  data() {
    return {
      router: true,
      defaultActive: "/",
      isShow: false
    };
  },

  methods: {
    home(){
      this.$router.push('/home')
    },
    handleSelect(key) {
      this.isShow = this.defaultActive != key;
      window.console.log(this.isShow);
    },
    personCenter(){
      this.user = JSON.parse(sessionStorage.getItem('user'))
      console.log("用户id"+this.user.employerId)
      this.$router.push('/employerCenter')
    },
    logout(){
      sessionStorage.removeItem("user");
      sessionStorage.removeItem("employer");
      sessionStorage.removeItem("provider");
      this.$router.push("/");
    }

  },

};
</script>

<style lang="scss">
* {
  padding: 0;
  margin: 0;
}

html,
body {
  height: 100%;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header {
  display: flex;
  justify-content: space-between;
  align-content: center;
  width: 1240px;
  margin: 0 auto;
  //background-color: #fff;

  .logo {
    width: 180px;
    //padding: 10px;
    height: 60px;

    img {
      width: 100%;
      height: 60px;
    }
  }
}

.el-main {
  padding: 0 !important;
}

.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.header {
  background-color: #23262e !important;
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #000000;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}
.collapse-btn:hover{
  background-color: #009688!important;
}

</style>




