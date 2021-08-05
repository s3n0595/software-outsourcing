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

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :close-on-click-modal='false'
    >
      <div style="margin-top: 15%;font-size: 20px;">
      <span style="margin-left: 20%">如您已支付成功，请点击<span style="color: red">我已支付</span></span>
      </div>
      <span slot="footer" class="dialog-footer">
        <div style="margin-top: 10%">
    <el-button type="primary" @click="payment">我已支付</el-button>
    <el-button  @click="dialogVisible = false" style="margin-right: 30%">取消</el-button>
        </div>
  </span>
    </el-dialog>


  </div>

</template>

<script>
export default {
  name: "deposit",
  data () {
    return {
      dialogVisible: false,
      radio: '1',
      out_trade_no:'',
      total_amount:'',
      subject:'',
      phoneNumber:'',
      user:''
    };
  },
  methods:{
    recharge(){
      this.dialogVisible = true
      this.out_trade_no = this.getProjectNum() + Math.floor(Math.random() * 100000);//随机生成订单号
      this.subject="开发宝余额充值"+this.total_amount+"元";
      this.$axios.post("/app/aliPay",this.$qs.stringify({
        "out_trade_no":this.out_trade_no,
        "total_amount":this.total_amount,
        "subject":this.subject,
        "phoneNumber":this.user.phoneNumber,
        "type":"雇主"
      })).then(response=>{
          // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
          let divForm = document.getElementsByTagName('divform')
          if (divForm.length) {
            document.body.removeChild(divForm[0])
          }
          const div=document.createElement('divform');
          div.innerHTML=response.data; // data就是接口返回的form 表单字符串
          document.body.appendChild(div);
          document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
          document.forms[0].submit();

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
    },
    payment(){
      this.dialogVisible = false
      this.$router.push({ name: 'accountview'});
    }
  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("employer"))
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
