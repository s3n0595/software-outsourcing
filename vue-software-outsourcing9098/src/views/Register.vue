<template>

  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-button type="primary" plain @click="providerReg">服务商</el-button>
      <el-button type="primary" plain @click="employerReg">雇主</el-button>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>

</template>

<script>
export default {
  name: "Register",
  methods:{
    providerReg(){
      this.$router.push('/providerReg')
    },
    employerReg(){
      this.$router.push('/employerReg')
    }
  }
}
</script>

<style scoped>

</style>
