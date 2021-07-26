<template>

  <el-container>
    <el-header>
      <el-button type="primary" plain>成为开发者</el-button>
      <el-button type="primary" plain>
        <router-link to="/login">登陆</router-link>
      </el-button>
      <el-button type="primary" plain>
        <router-link to="/register">注册</router-link>
      </el-button>
      <el-button type="primary" plain>发布项目</el-button>
    </el-header>
    <el-main>Main</el-main>
  </el-container>




</template>

<script>
export default {
  name: "Index"
}
</script>

<style scoped>

</style>
