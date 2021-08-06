<template>
  <el-container>
    <el-header>
      <Nav></Nav>
    </el-header>
    <el-main>
      <div style="background-color: #f0f2f5">
        <div id="main">
          <div id="list">
            <el-row >
            <div class="goods">
              <el-col :span="24" >
                <div style="margin-left: 5%;">
                <p style="padding-top: 20px;">
                  <span style="font-size: 25px;">&nbsp;{{work.worksTitle}}</span>
                  <span style="margin-left: 30px;" class="border">No.28394</span>
                </p>
                <p style="margin-top: 2%;">作品类型<el-tag style="margin-left: 2%">微信公众号</el-tag></p>
                  <p style="margin-top: 2%;padding-bottom: 20px;">金额<span style="margin-left: 10px;">￥2000</span>
                    <span class="fenge"></span>
                    信用分<span style="margin-left: 10px;">80</span>
                  <span style="margin-left: 85%"><el-button type="primary">立即购买</el-button></span>
                  </p>
                </div>
              </el-col>
              <el-col :span="24" >
                <p style="padding-top: 5px;margin-left: 5%;padding-bottom: 5px;">
                  <span style="font-size: 20px;">服务方信息</span>
                  <img src="../assets/img/bell.png" width="16px" height="16px" style=" vertical-align: -2px;margin-left: 30px;">
                  <span class="ds">请谨慎甄别服务方信息，防止诈骗和非法项目</span>
                </p>
              </el-col>
              <el-col :span="24" >
                <div class="demo-basic--circle" style="margin-top: 25px;margin-left: 5%">
                  <div class="block">
                    <el-avatar :size="70" :src="circleUrl"></el-avatar>
                  </div>

                  <div style="float: left;margin: -7% 0 1% 10%">
                    <span style="font-size: 20px;">刘杨</span>
                    <span>( 发布作品总数：1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注册时间： 2021/08/06 )</span>
                  </div>
                </div>
              </el-col>
              <el-col :span="24">
                <div style="margin-left: 4%;margin-top: 10px;">
                  <span style="font-size: 30px;">&nbsp;项目描述</span>
                  <p style="margin-top: 10px;" class="content">
                    上海科建工程管理股份有限公司成立于2012年9月，注册资金500万。公司前身上海科建工程管理有限公司，
                    是一家从事专业工程技术服务及工程项目管理的企业。公司于2017年11月通过国家高新技术企业认定，
                    目前工程管理软件研发团队10人，包括硕士和研究生在内，平均年龄在35岁。公司自主研发工程项目管理
                    标准化+互联网协同工作系统平台，此软件广泛应用于工程项目管理过程，实现全覆盖检查、全过程控制、全方位协调的目标。
                    目前公司业务范围涉及上海、广东等多地，合作的单位有上海同济工程项目管理咨询有限公司、
                    上海华银日用品有限公司、中科建设开发总公司、广东怡轩房地产开发有限公司等多家知名企业。 立人立己、达人达己！公司一直秉承“
                    帮助施工单位解决技术问题、帮助业主解决协调问题 ”的管理理念，上海科建工程管理股份有限公司不断在工程项目管理领域开拓创新，
                    通过不断完善工程项目管理标准化+互联网协同工作系统平台，实现每项工程“无重大安全事故、无重大返工、工程施工材料无伪劣产品、
                    工程管理留下痕迹、施工过程可追溯”五大管理目标。
                  </p>
                </div>
              </el-col>
              <el-col :span="24">
                <div style="margin-left: 4%;margin-top: 10px;">
                  <span style="font-size: 30px;">&nbsp;作品展示</span>
<!--                  <p><img  :src="'api/images/' + item.annexPath" alt="" style="width: 80%;height: 90%;margin: 5%"></p>-->
                </div>
              </el-col>
              <el-col :span="24">
                <div style="margin-left: 4%;margin-top: 10px;">
                  <span style="font-size: 30px;">&nbsp;参考网站或产品</span>
                  <p style="margin-top: 20px;">https://www.dgzj.com/ruodian/84292.html</p>
                </div>
              </el-col>
            </div>
            </el-row>
          </div>
        </div>
      </div>

    </el-main>
  </el-container>


</template>


<script>
import Nav from "@/views/Nav";
export default {
  name: "WorksDetails",
  data () {
    return {
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      count: 0,
      searchInfo:"",
      worksList:{},
      radio1: '全部',
      radio2: '全部',
      radio3: '全部',
      work:"",
      miStatusColor: function(val){
        if(val == 'Web 网站'){
          return 'lightpink'
        }else if(val == 'App 开发'){
          return 'warning'
        }else if(val == '微信公众号'){
          return 'success'
        }else if(val == 'HTML5 应用'){
          return 'danger'
        }else if(val == '小程序'){
          return 'success'
        } else {
          return 'info'
        }
      }
    }
  },
  components:{
    Nav
  },
  methods: {
    selproInfo(){
      this.$axios.post('qworks/selpro',this.$qs.stringify({
        proId:this.work.providerId
      })).then(res=>{

      });
    },
    addMore(){
      let params={
        count: this.count
      };
      this.$axios.get('qworks/worksList',{params:params}).then(res =>{
        if(res.data.length == 0) {
          this.$message({
            message: "暂无更多",
            type: "warning"
          });
        } else {
          this.count += res.data.length;
          for (let i = 0; i < res.data.length; i++) {
            this.worksList.push(res.data[i]);
          }
        }
      })
    },
  },
  mounted() {
    this.work=JSON.parse(sessionStorage.getItem("work"));
    this.selproInfo();
  }
}
</script>

<style scoped>
#main{
  width:70%;
  height: auto;
  margin: auto;
  padding-top: 20px
}
#list{
  margin-top:30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}
#list div{
  height:auto;
  border-bottom: 1px solid #eff2f7;
}
.goods:hover{
  background: #eff2f7;
}
.fenge{

  padding:20px 6px 10px 6px;

  margin-left: 10px;

  border-left: 1px solid 	#d0d0d0;

  font-size: 0;

}
.border{
  border-radius: 3px;
  background-color: #8796A8;
  color: #fff;
  font-size: 20px;
  padding: 8px;
  margin-left: 26px;
  height: 25px;
  line-height: 25px;
}
.ds{
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
