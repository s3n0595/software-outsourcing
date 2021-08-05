<template>

  <el-container>
    <el-header>
      <Nav></Nav>
    </el-header>
    <el-main>
      <div style="background-color: #f0f2f5">
        <div id="main">
          <div id="condition">
            <div>
              <p>需求类型</p>
              <el-radio  label="1" ><i class="el-icon-platform-eleme">&nbsp;&nbsp;Web 网站</i></el-radio>
              <el-radio  label="2"><i class="el-icon-mobile-phone">&nbsp;&nbsp;App 开发</i></el-radio>
              <el-radio  label="3"><i class="el-icon-connection">&nbsp;&nbsp;微信公众号</i></el-radio>
              <el-radio   label="4"><i class="el-icon-s-management">&nbsp;&nbsp;HTML5 应用</i></el-radio>
              <el-radio  label="5"><i class="el-icon-info">&nbsp;&nbsp;小程序</i></el-radio>
              <el-radio  label="6"><i class="el-icon-cherry">&nbsp;&nbsp;其他应用</i></el-radio>
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
              <el-col :span="6" style="padding-top: 10px;">
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
            <el-row v-for="item in demandList" >
              <div class="goods" @click="viewDetails(item)">
                <el-col :span="6">
                  <img :src="'api/images/bg_00' + (item.demandId%5) +'.png'" alt="" style="width: 80%;height: 80%;margin: 5%">
                </el-col>
                <el-col :span="15">
                  <p>
                    <span>No.{{item.demandId}}&nbsp;</span>
                    <span>&nbsp;{{item.demandTitle}}</span>
                    <el-tag>{{item.demandTypeName}}</el-tag>
                  </p>

                  <p>工期：{{item.predictTime}}</p>
                  <p>发布时间：{{item.releaseTime}}</p>
                </el-col>
                <el-col :span="3">
                  <p>￥{{item.predictPrice}}</p>
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
        name: "GoodsList",
        data () {
          return {
            count: 0,
            searchInfo:"",
            demandList:{}
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
            this.$axios.get('demand/list',{params:params}).then(res =>{
                this.demandList = res.data;
                this.count = res.data.length;
            })
          },
          addMore(){
            let params={
              count: this.count
            };
             this.$axios.get('demand/list',{params:params}).then(res =>{
                 if(res.data.length == 0) {
                     this.$message({
                        message: "暂无更多",
                        type: "warning"
                      });
                 } else {
                     this.count += res.data.length;
                     for (let i = 0; i < res.data.length; i++) {
                         this.demandList.push(res.data[i]);
                     }
                 }
            })
          },
          viewDetails(item) {
            this.$router.push({name:"demandDetails",params:{"demand":item}});
          }
        },
        mounted() {
            this.load();
        }
    }
</script>

<style scoped>
    #main{
        width:50%;
        height: auto;
        margin: auto
    }

    #condition{
        height: 200px;
        margin-top:30px;
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
        height:100px;
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
