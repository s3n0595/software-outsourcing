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
      <el-menu-item @click="home">首页</el-menu-item>
      <el-submenu index="login">
        <template slot="title">登陆</template>
        <el-menu-item @click="employerLogin">雇主登陆</el-menu-item>
        <el-menu-item @click="providerLogin">服务商登陆</el-menu-item>
      </el-submenu>
      <el-submenu index="register">
        <template slot="title">注册</template>
        <el-menu-item @click="employerRegister">注册成为雇主</el-menu-item>
        <el-menu-item @click="providerRegister">注册成为服务商</el-menu-item>
      </el-submenu>
    </el-menu>


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
      width: 100%;
      line-height: 60px;
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
}

</style>
