<template>

  <el-container>
    <el-header>
      <el-button type="primary" plain>成为开发者</el-button>
      <el-button type="primary" plain @click="login">登陆</el-button>
      <el-button type="primary" plain @click="register">注册</el-button>
      <el-button type="primary" plain>发布项目</el-button>
    </el-header>
    <el-main>Main</el-main>
  </el-container>




</template>

<script>
export default {
  name: "Index",
  methods:{
    login(){
      this.$router.push('/login')
    },
    register(){
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>

</style>
