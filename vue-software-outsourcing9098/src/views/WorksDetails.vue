<template>
  <div style="background-color: #f0f2f5">
    <div id="main">
      <div id="list">
        <el-row>
          <div class="goods">
            <el-col :span="24">
              <div style="margin-left: 5%;">
                <p style="padding-top: 20px;">
                  <span style="font-size: 25px;margin-left: -1%">&nbsp;{{ work.worksTitle }}</span>
                  <span style="margin-left: 30px;" class="border">No.{{ work.worksId }}</span>
                </p>
                <p style="margin-top: 2%;">作品类型
                  <el-tag :type="miStatusColor(work.demandTypeName)" style="margin-left: 2%">{{ work.demandTypeName }}</el-tag>
                </p>
                <p style="margin-top: 2%;padding-bottom: 20px;">金额<span style="margin-left: 10px;">￥{{
                    work.worksPrice
                  }}</span>
                  <span class="fenge"></span>
                  信用分<span style="margin-left: 10px;">{{ workproInfo.credit }}</span>
                  <span class="fenge"></span>
                  发布时间<span style="margin-left: 10px;">{{ work.releaseTime }}</span>
                  <span style="margin-left: 85%">
                    <el-button v-show="this.user.role !='pro'" type="primary" @click="buying">立即购买</el-button>
                  </span>
                  <pay-box :dialogShow='flag' @closeDialog='close'></pay-box>
                </p>
              </div>
            </el-col>
            <el-col :span="24">
              <p style="padding-top: 5px;margin-left: 5%;padding-bottom: 5px;">
                <span style="font-size: 20px;">服务方信息</span>
                <img src="../assets/img/bell.png" width="16px" height="16px"
                     style=" vertical-align: -2px;margin-left: 30px;">
                <span class="ds">请谨慎甄别服务方信息，防止诈骗和非法项目</span>
              </p>
            </el-col>
            <el-col :span="24">
              <div class="demo-basic--circle" style="margin-top: 25px;margin-left: 5%">
                <div class="block">
                  <el-avatar :size="70" :src="circleUrl"></el-avatar>
                </div>

                <div style="float: left;margin: -7% 0 1% 10%">
                  <span style="font-size: 20px;">{{ workproInfo.providerName }}</span>
                  <span>( 发布作品总数：{{ workproInfo.workCount }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注册时间： 2021/08/06 )</span>
                </div>
              </div>
            </el-col>
            <el-col :span="24">
              <div style="margin-left: 4%;margin-top: 10px;">
                <span style="font-size: 30px;">&nbsp;项目描述</span>
                <p style="margin-top: 10px;" class="content">
                  {{ work.worksDescribe }}
                </p>
              </div>
            </el-col>
            <el-col :span="24">
              <div style="margin-left: 4%;margin-top: 10px;">
                <span style="font-size: 30px;">&nbsp;作品展示</span>
                <p>
                  <el-empty description="暂无作品展示"></el-empty>
                </p>
                <!--                  <img  :src="'api/images/' + item.annexPath" alt="" style="width: 80%;height: 90%;margin: 5%">-->
              </div>
            </el-col>
            <el-col :span="24">
              <div style="margin-left: 4%;margin-top: 10px;">
                <span style="font-size: 30px;">&nbsp;参考网站或产品</span>
                <p style="margin-top: 20px;">{{ work.worksAddress }}</p>
              </div>
            </el-col>
          </div>
        </el-row>
      </div>
    </div>
  </div>


</template>


<script>
export default {
  name: "WorksDetails",
  data() {
    return {
      flag: false,
      paypwd:false,
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      searchInfo: "",
      radio1: '全部',
      radio2: '全部',
      radio3: '全部',
      work: "",
      workproInfo: {},
      user:"",
      order:"",
      miStatusColor: function (val) {
        if (val == 'Web 网站') {
          return 'lightpink'
        } else if (val == 'App 开发') {
          return 'warning'
        } else if (val == '微信公众号') {
          return 'success'
        } else if (val == 'HTML5 应用') {
          return 'danger'
        } else if (val == '小程序') {
          return 'success'
        } else {
          return 'info'
        }
      }
    }
  },
  methods: {
    selproInfo() {
      this.$axios.post('qworks/selpro', this.$qs.stringify({
        proId: this.work.providerId
      })).then(res => {
        this.workproInfo = res.data;
      });
    },
    buying(){
      this.user=JSON.parse(sessionStorage.getItem("user"));
      if(this.user=="" || this.user === null){
        this.$notify.error({
          title: '提示',
          message: `请先登录后购买作品`
        });
        this.$router.push({ name: 'employerLog'});
      }else{
        this.$axios.post('buy/whetherworks',this.$qs.stringify({
          employerId:this.user.employerId,
          worksId:this.work.worksId
        })).then(res=>{
            if(res.data==1){
              this.$notify.error({
                title: '提示',
                message: `您已经购买过该作品了`
              });
            }else{
              this.flag = true;
              // this.$axios.post('buy/buyBalance',this.$qs.stringify({
              //   employerId:this.user.employerId
              // })).then(res=>{
              //   if(this.work.worksPrice<=res.data){
              //     this.order= this.getProjectNum() + Math.floor(Math.random() * 100000000)
              //     this.$axios.post('buy/works',this.$qs.stringify({
              //       employerId:this.user.employerId,
              //       worksId:this.work.worksId,
              //       tradeStatus:1,
              //       worksPrice:this.work.worksPrice,
              //       tradeOrder:this.order,
              //       providerId:this.work.providerId
              //     })).then(resp=>{
              //       this.$message({
              //         message: "购买成功，请前往个人中心查看",
              //         type: "success",
              //         offset:150,
              //         duration:3000,
              //       });
              //     })
              //   }else{
              //     this.$message({
              //       message: "开发宝余额不足，请前往个人中心充值",
              //       type: "warning",
              //       offset:150,
              //       duration:2000,
              //     });
              //   }
              // })
            }
        })
      }
    },
    close(val){
      this.flag = false;
      this.$axios.post('buy/buyPwd',this.$qs.stringify({
        employerId:this.user.employerId,
        transactionPwd:sessionStorage.getItem("paypwd")
      })).then(res=>{
          if(res.data!=null && res.data!=''){
            this.$axios.post('buy/buyBalance',this.$qs.stringify({
              employerId:this.user.employerId
            })).then(res=>{
              if(this.work.worksPrice<=res.data){
                this.order= this.getProjectNum() + Math.floor(Math.random() * 100000000)
                this.$axios.post('buy/works',this.$qs.stringify({
                  employerId:this.user.employerId,
                  worksId:this.work.worksId,
                  tradeStatus:1,
                  worksPrice:this.work.worksPrice,
                  tradeOrder:this.order,
                  providerId:this.work.providerId,
                  phoneNumber:this.user.phoneNumber,
                  tradeContent:"购买作品《"+this.work.worksTitle+"》"
                })).then(resp=>{
                  this.$message({
                    message: "购买成功，请前往个人中心查看",
                    type: "success",
                    offset:150,
                    duration:3000,
                  });
                })
              }else{
                this.$message({
                  message: "开发宝余额不足，请前往个人中心充值",
                  type: "warning",
                  offset:150,
                  duration:2000,
                });
              }
            })
          }else{
            this.$message({
              message: "支付密码错误，购买失败",
              type: "warning",
              offset:150,
              duration:2000,
            });
          }
      })
    },
    // 获取当前日期的方法
    getProjectNum () {
      const projectTime = new Date() // 当前中国标准时间
      const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
      const Month = projectTime.getMonth() + 1 // 获取中国区月份
      const Day = projectTime.getDate() // 获取几号
      var CurrentDate = Year
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
  },
  mounted() {
    this.work = JSON.parse(sessionStorage.getItem("work"));
    this.selproInfo();
  },
}
</script>

<style scoped>
#main {
  width: 70%;
  height: auto;
  margin: auto;
  padding-top: 20px
}

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

.fenge {

  padding: 20px 6px 10px 6px;

  margin-left: 10px;

  border-left: 1px solid #d0d0d0;

  font-size: 0;

}

.border {
  border-radius: 3px;
  background-color: #8796A8;
  color: #fff;
  font-size: 20px;
  padding: 8px;
  margin-left: 26px;
  height: 25px;
  line-height: 25px;
}

.ds {
  color: #F5A623;
  vertical-align: 2px;
  padding-left: 6px;
}

.content {
  color: #14679f;
  font-size: 14px;
  text-indent: 25px;
  line-height: 30px;
}
</style>
