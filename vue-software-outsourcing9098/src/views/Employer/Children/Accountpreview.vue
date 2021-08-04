<template>
  <div>
    <el-card class="box-card" >
      <div slot="header" class="clearfix">
        <img src="../../../assets/img/dp.png" >
        <div >
          <div class="title"><span style="font-size: 20px;">开发宝余额</span></div>
          <div class="je"><span style="font-size: 30px;color: #2894FF">0</span><span>元</span></div>
        </div>
      </div>
      <div style="padding-bottom: 50px;" class="text item">
        <el-row>
          <el-button type="primary"  style="width: 20%;margin-left: 30%" @click="goRecharge">充值</el-button>
          <el-button type="info"     style="width: 20%;margin-left: 5%">提现</el-button>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Accountpreview",
  data() {
    return{

    };
  },
  methods:{
    goRecharge(){
      this.$router.push({ name: 'deposit'});
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 600px;
  background-color: rgb(248,249,251);
  margin-top: 12%;
  margin-left: 22%;
}
.title{
  margin-top: 10px;
  text-align: center;
}
.je{
  text-align: center;margin-top: 15px;
}
</style>
