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
                  <span style="font-size: 25px;">&nbsp;流量录制回放平台</span>
                  <span style="margin-left: 30px;" class="border">No.28394</span>
                </p>
                <p style="margin-top: 2%;">作品类型<el-tag style="margin-left: 2%">微信公众号</el-tag></p>
                  <p style="margin-top: 2%;padding-bottom: 20px;">金额<span style="margin-left: 10px;">￥2000</span>
                    <span class="fenge"></span>
                    信用分<span style="margin-left: 10px;">80</span>
                  </p>
                </div>
              </el-col>
              <el-col :span="24" >
                <p style="padding-top: 5px;margin-left: 5%;padding-bottom: 5px;">
                  <span style="font-size: 20px;">需求方信息</span>
                  <img src="../assets/img/bell.png" width="16px" height="16px" style=" vertical-align: -2px;margin-left: 30px;">
                  <span class="ds">请谨慎甄别服务方信息，防止诈骗和非法项目</span>
                </p>
              </el-col>
              <el-col :span="24" >
                <div>aaa</div>
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
      count: 0,
      searchInfo:"",
      worksList:{},
      radio1: '全部',
      radio2: '全部',
      radio3: '全部',
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
    load () {
      console.log(this.count)
      let params =  {
        count: this.count
      };
      console.log(params)
      this.$axios.get('qworks/worksList',{params:params}).then(res =>{
        this.worksList = res.data;
        this.count = res.data.length;
      })
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
    this.load();
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
</style>
