<template>
  <el-header>
    <div class="logo">
      <img src="../assets/img/toplogo.png" alt/>
    </div>
    <el-menu
        :default-active="defaultActive"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
    >
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/news">新闻资讯</el-menu-item>
      <el-menu-item index="/product">产品中心</el-menu-item>
      <el-menu-item index="/case">经典案例</el-menu-item>
      <el-menu-item index="/goin">走进科建</el-menu-item>
      <el-menu-item index="/download">下载APP</el-menu-item>

    </el-menu>

    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name" trigger="click" >
          <span class="el-dropdown-link">
<!--            {{username}}-->
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <router-link to="/admin">
              <el-dropdown-item>用户信息</el-dropdown-item>
            </router-link>
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
      this.$router.push('/Home')
    },
    employerRegister(){
      this.$router.push('/employerReg')
    },
    providerRegister(){
      this.$router.push('/providerReg')
    },
    employerLogin(){
      this.$router.push('/employerLog')
    },
    providerLogin(){
      this.$router.push('/providerLog')
    },
    logout: function () {
      var _this = this;
      this.$confirm('确认退出吗?', '提示', {
        //type: 'warning'
      }).then(() => {
        sessionStorage.removeItem('user');
        _this.$router.push('/');
      }).catch(() => {

      });
    },


    handleSelect(key) {
      this.isShow = this.defaultActive != key;
      window.console.log(this.isShow);
    }
  }
};
</script>

<style lang="scss" scoped>
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
    width: 280px;
    padding: 10px;
    height: 60px;

    img {
      width: 70%;
      height: 40px;
    }
  }
}

.el-main {
  padding: 0 !important;
}

.footer {
  width: 100%;
  height: 216px;
  overflow: hidden;
  background-color: #14679f;

  &-content {
    width: 1240px;
    margin: 0 auto;
    padding-top: 20px;
    display: flex;
    justify-content: space-between;

    .content-nav {
      display: flex;
      justify-content: space-around;

      li {
        display: flex;
        flex-direction: column;
        padding: 0 20px;
        //justify-content: center;
        align-items: flex-start;

        p {
          font-size: 20px;
          color: #d4edff;
          padding: 10px 0;
        }

        span {
          color: #f7f7f7;
          font-weight: 300;
          padding: 5px 0;
        }
      }
    }

    img {
      width: 170px;
      height: 170px;
      padding: 10px;
    }
  }

  .copyright {
    height: 30px;
    background: #125688;

    span {
      color: #fff;
      line-height: 30px;
    }
  }

  .header-right {
    float: right;
    padding-right: 50px;
  }
}

</style>




