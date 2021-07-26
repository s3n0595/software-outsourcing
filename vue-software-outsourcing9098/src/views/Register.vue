<template>

  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-button type="primary" plain>
        <router-link to="/providerReg">服务商</router-link>
      </el-button>
      <el-button type="primary" plain>
        <router-link to="/employerReg">雇主</router-link>
      </el-button>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>

</template>

<script>
export default {
  name: "Register"
}
</script>

<style scoped>

</style>
