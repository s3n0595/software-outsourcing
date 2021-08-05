<template>

  <el-container>
    <el-header>
      <Nav></Nav>
    </el-header>
    <el-main>
      <div style="background-color: #f0f2f5">
        <div id="main">
          <div id="condition">
            <div style="padding-top: 35px;">
              <span style="margin-left: 5%;">作品类型:</span>
              <el-radio-group v-model="radio1" style="margin-left: 20px;" size="mini">
                <el-radio-button label="全部" ></el-radio-button>
                <el-radio-button label="Web 网站"></el-radio-button>
                <el-radio-button label="App 开发"></el-radio-button>
                <el-radio-button label="微信公众号"></el-radio-button>
                <el-radio-button label="HTML5 应用"></el-radio-button>
                <el-radio-button label="小程序"></el-radio-button>
                <el-radio-button label="其他应用"></el-radio-button>
              </el-radio-group>
            </div>

            <div style="padding-top: 20px;">
              <span style="margin-left: 5%;">作品价格:</span>
              <el-radio-group v-model="radio2" style="margin-left: 20px;" size="mini">
                <el-radio-button label="全部" ></el-radio-button>
                <el-radio-button label="0-5K"></el-radio-button>
                <el-radio-button label="5K-1万"></el-radio-button>
                <el-radio-button label="1万-5万"></el-radio-button>
                <el-radio-button label="5万以上"></el-radio-button>
              </el-radio-group>
            </div>
            <div style="padding-top: 20px;">
              <span style="margin-left: 5%;">角色领域:</span>
              <el-radio-group v-model="radio3" style="margin-left: 20px;" size="mini">
                <el-radio-button label="全部" ></el-radio-button>
                <el-radio-button label="前端开发"></el-radio-button>
                <el-radio-button label="后端开发"></el-radio-button>
                <el-radio-button label="微信应用开发"></el-radio-button>
                <el-radio-button label="全栈开发"></el-radio-button>
              </el-radio-group>
            </div>
          </div>
          <div id="sort">
            <el-row>
              <el-col :span="18">
                <p>排序:</p>
                <el-divider direction="vertical"></el-divider>
                <span>综合</span>
                <el-divider direction="vertical"></el-divider>
                <span>发布时间</span>
                <el-divider direction="vertical"></el-divider>
                <span>价格排序</span>
                <el-divider direction="vertical"></el-divider>
              </el-col>
              <el-col :span="6" style="padding-top: 10px;padding-right: 10px;">
                <el-input
                    placeholder="请输入内容"
                    size="mini"
                    v-model="searchInfo">
                  <i slot="prefix" class="el-input__icon el-icon-search"></i>
                </el-input>
              </el-col>
            </el-row>

          </div>
          <div id="list">
            <el-row v-for="item in worksList" >
              <div class="goods">
                <el-col :span="6">
<!--                  v-image-preview-->
                  <img  :src="'api/images/' + item.annexPath" alt="" style="width: 80%;height: 90%;margin: 5%">
                </el-col>
                <el-col :span="15">
                  <p style="padding-top: 20px;">
                    <span>No.{{item.worksId}}&nbsp;</span>
                    <span>&nbsp;{{item.worksTitle}}</span>
                  </p>
<!--                  <p>工期：{{item.predictTime}}</p>-->
                  <p> <el-tag :type="miStatusColor(item.demandTypeName)">{{item.demandTypeName}}</el-tag></p>
                  <p>发布时间：{{item.releaseTime}}</p>
                </el-col>
                <el-col :span="3">
                  <p style="padding-top: 20px;">￥{{item.worksPrice}}</p>
                </el-col>
              </div>
            </el-row>
          </div>
          <div style="text-align: center;padding: 30px">
            <el-button type="primary" plain @click="addMore()">加载更多</el-button>
          </div>
        </div>
      </div>

    </el-main>
  </el-container>


</template>


<script>
import Nav from "@/views/Nav";
export default {
  name: "WorkList",
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

#condition{
  height: 200px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}
#sort{
  height: 50px;
  margin-top:30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff;

}
#list{
  margin-top:30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}
#list div{
  height:150px;
  border-bottom: 1px solid #eff2f7;
}

.el-col {
  border-radius: 4px;
}
.bg-white{
  background: #ffffff;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.goods:hover{
  background: #eff2f7;
}
</style>
