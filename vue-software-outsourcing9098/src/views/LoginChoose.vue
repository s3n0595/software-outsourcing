<template>
  <el-container>
    <banner title="登陆选择" />
    <el-header>Header</el-header>
    <el-main>
      <el-button type="primary" plain @click="providerLog">服务商登陆</el-button>
      <el-button type="primary" plain @click="employerLog">雇主登陆</el-button>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
import Banner from "../components/Banner";
export default {
  name: "LoginChoose",
  components: {
    Banner
  },
  methods: {
    providerLog(){
      this.$router.push('/providerLog')
    },
    employerLog(){
      this.$router.push('/employerLog')
    }
  }
}
</script>

<style lang="css" scoped>

</style>
