<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <div class="title">
          <h2>选择充值方式</h2>
        </div>
        <div class="fs">
          <el-radio v-model="radio" label="1" style="zoom: 100%;">
            <div class="imgicon"><img src="../../../assets/img/zfb.png" ></div>
          </el-radio>
        </div>
      </div>
      <div  class="text item">
        <div class="title2">
          <h2>充值金额</h2>
          <div class="el-upload__tip" style="font-size: 15px;">每次充值金额不可低于 1 元。</div>
        </div>
        <div>
          <el-input v-model="total_amount" placeholder="1~100,000" style="width:15%;margin-left: 2%;margin-top: 5%"></el-input>&nbsp;&nbsp;元
        </div>
        <div style="margin-top: 15%;">
          <el-row>
            <el-button type="primary"  style="margin-left: 30%" @click="recharge">立即充值</el-button>
            <el-button type="info"     style="">取消</el-button>
          </el-row>
        </div>
      </div>
    </el-card>

  </div>

</template>

<script>
export default {
  name: "deposit",
  data () {
    return {
      radio: '1',
      out_trade_no:'',
      total_amount:'',
      subject:''
    };
  },
  methods:{
    recharge(){
      this.out_trade_no = this.getProjectNum() + Math.floor(Math.random() * 10000);//随机生成订单号
      this.subject="开发宝余额充值"+this.total_amount+"元";
      this.$axios.post("/app/aliPay",this.$qs.stringify({
        "out_trade_no":this.out_trade_no,
        "total_amount":this.total_amount,
        "subject":this.subject
      })).then(response=>{
        let divForm = document.getElementsByTagName('divform')
        if (divForm.length) {
          document.body.removeChild(divForm[0])
        }
        const div = document.createElement('divform')
        div.innerHTML = response.data // res.data就是sb支付宝返回给你的form
        document.body.appendChild(div)
        // document.forms[0].setAttribute('target', '_blank') // 加了_blank可能出问题所以我注释了
        document.getElementById('alipay_submit').submit()
        console.log(response.data);
      })
    },
    getProjectNum () {
      const projectTime = new Date() // 当前中国标准时间
      const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
      const Month = projectTime.getMonth() + 1 // 获取中国区月份
      const Day = projectTime.getDate() // 获取几号
      var CurrentDate = String(Year)
      if (Month >= 10) { // 判断月份和几号是否大于10或者小于10
        CurrentDate += Month
      } else {
        CurrentDate += '0' + Month
      }
      if (Day >= 10) {
        CurrentDate += Day
      } else {
        CurrentDate += '0' + Day
      }
      return CurrentDate
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom:10%;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
.clearfix{
  margin-bottom: 10%;
}
.title{
  float: left;
  margin-left: 10%;
  margin-top: 5%
}
.fs{
  margin-top: 6%;margin-left: 30%
}
.imgicon{
  float: right;margin-top: -15px;
}
.title2{
  float: left;
  margin-left: 10%;
  margin-top: 3%
}
</style>
