<template>
<div>
    <el-button type="primary" @click='showUp'>去支付</el-button>
    <pay-box :dialogShow='flag' @closeDialog='close'></pay-box>
</div>
</template>

<script>
export default{
  data(){
    return{
      flag: false
    }
  },
  methods:{
    showUp(){
      this.flag = true;
    },
    close(val){
      this.flag = false;
      console.log("支付密码："+sessionStorage.getItem("paypwd"))
    },
  }

}
</script>

<style scoped>
.popup{
  width: 350px;
  height: 280px;
  padding-top: 16px;
  text-align: center;
  border-radius: 7px;
}
.van-popup--center{
  border-radius: 7px;
  overflow: hidden;
}
.popup_title{
  height: 49px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size:16px;
  font-family:Source Han Sans CN;
  font-weight:500;
  color:rgba(51,51,51,1);
}
.popup_title_{
  text-align: center;
}
.popup_yes{
  margin: 0 auto;
  margin-top: 50px;
}
.popup_{
  position: fixed;
  bottom: 0;
  z-index: 99999;
}

</style>
