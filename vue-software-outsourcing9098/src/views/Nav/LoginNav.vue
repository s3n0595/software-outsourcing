<template>
  <el-header>
    <div class="logo">
      <img src="../../../src/assets/img/chinamobile.png" alt/>
    </div>
    <div class="register">
      <el-link disabled >还没注册账号？</el-link>
      <el-link type="primary" @click="employerReg" style="font-size: 16px">注册</el-link>
    </div>
  </el-header>

</template>

<script>
export default {
  name: "loginNav",
  data() {
    return {
      router: true,
      defaultActive: "/",
      isShow: false
    };
  },
  methods: {
    employerReg(){
      this.$router.push('/employerReg')
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


.el-header {
  display: flex;
  justify-content: space-between;
  align-content: center;
  width: 1240px;
  margin: 0 auto;
  //box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
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

.register{
  padding-top: 2%;
}

</style>
