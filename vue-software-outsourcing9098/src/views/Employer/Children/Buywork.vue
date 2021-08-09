<template>
  <div>
    <div>作品列表</div>
  <div id="list">
    <el-row v-for="item in buylist">
      <div class="goods" >
        <el-col :span="5" style="border: none">
          <img src="../../../assets/img/bell.png"  alt="" style="width: 50%;height: 50%;margin: 5%">
        </el-col>
        <el-col :span="7" style="border: none">
          <p style="padding-top: 20px;">作品编号：<span>No.{{item.worksId}}&nbsp;</span></p>
          <p style="padding-top: 20px;">作品类型：<span style="padding-right: 50px;">{{item.demandTypeName}}</span></p>
          <p style="padding-top: 20px;">交易时间：{{item.tradeTime}}</p>
          <p style="padding-top: 20px;padding-bottom: 20px;">服务商联系方式：{{item.phoneNumber}}</p>
        </el-col>
        <el-col :span="9" style="border: none">
          <p style="padding-top: 20px;">作品名称：<span>&nbsp;{{item.worksTitle}}</span></p>
          <p style="padding-top: 20px;">交易金额：<span>&nbsp;￥{{item.worksPrice}}</span></p>
          <p style="padding-top: 20px;">订单编号：<span>&nbsp;{{item.tradeOrder}}</span></p>
          <p style="padding-top: 20px;">服务商名称：<span>&nbsp;{{item.providerName}}</span></p>
        </el-col>
        <el-col :span="3" style="border: none">
          <p style="padding-top: 20px;margin-left: 40%;">
            <el-link v-if="item.tradeStatus==1 || item.tradeStatus==2 || item.tradeStatus==3" type="success" disabled><span style="font-size: 20px;">交易中...</span></el-link>
            <el-link v-else-if="item.tradeStatus==4" type="success" disabled><span style="font-size: 20px;">交易成功</span></el-link>
            <el-link v-else type="danger" disabled><span style="font-size: 20px;">交易失败</span></el-link>
          </p>
          <p style="margin-top: 20px;margin-left: 20%;">
            <el-button v-if="item.tradeStatus==1 || item.tradeStatus==2 || item.tradeStatus==3" type="primary" @click="confirmRec(item)">确认收货</el-button>
            <el-button v-else  type="success" disabled>&nbsp;已收货</el-button>
          </p>
        </el-col>
        <div>
        <el-col :span="24" style="border: none">
            <div style="border: none;"> <el-divider content-position="center" ><span style="font-size: 20px;">交付进度</span></el-divider></div>
          <div style="margin-left: 15%;margin-top: 20px;margin-bottom: 20px;">
          <el-steps :space="200" :active="item.tradeStatus" finish-status="success" align-center>
            <el-step title="支付成功"></el-step>
            <el-step title="服务方确认"></el-step>
            <el-step title="作品交付"></el-step>
            <el-step title="交易完成"></el-step>
          </el-steps>
          </div>
        </el-col>
        </div>
      </div>
    </el-row>
  </div>
  </div>
</template>

<script>
export default {
  name: "Buywork",
  data(){
    return{
      user:'',
      buylist:{}
    }
  },
  methods:{
      seltradeWork(){
        this.$axios.post('buy/buylist',this.$qs.stringify({
          employerId:this.user.employerId
        })).then(res=>{
           this.buylist=res.data;
        })
      },
    confirmRec(item){
      this.$confirm("是否确认收货?（如确认收货后，产生的一系列问题平台不予负责）", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios.post('buy/confirm',this.$qs.stringify({
          tradeWorksId:item.tradeWorksId,
          employerId:this.user.employerId
        })).then(res=>{
            if(res.data==1){
              this.$message({
                message: "确认收货成功！",
                type: "success"
              });
              this.seltradeWork();
            }else{
              this.$message({
                message: "确认收货失败！",
                type: "error"
              });
          }
        })
      }).catch(() => {
        this.$message({
          type: "info",
          message: "已取消确认收货"
        });
      });
    }

  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("user"));
    this.seltradeWork();
  },

}
</script>

<style scoped>
#list {
  margin-top: 30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#list div {
  height: auto;
  border-bottom: 1px solid #eff2f7;
}
.goods:hover {
  background: #eff2f7;
}

</style>
