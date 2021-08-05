<template>

  <el-container>
    <el-header>
      <Nav></Nav>
    </el-header>
    <el-main>
      <div style="background-color: #f0f2f5">
        <div id="main">

            <div id="demandInfo">
              <div style="    min-height: 40px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    display: -webkit-flex;">
                <span style="font-size: 30px;">{{this.demand.demandTitle}}</span>
                <span style="margin-left: 30px;" class="border">No.{{this.demand.demandId}}</span>
              </div>
              <div style="margin-top: 20px;">
                    <el-col :span="3">{{this.demand.demandTypeName}}</el-col>
              </div>
              <div>
                <el-col :span="4">{{this.demand.predictPrice}}</el-col>
                <el-divider direction="vertical"></el-divider>
                <el-col :span="8">{{this.demand.releaseTime}}</el-col>
                <el-divider direction="vertical"></el-divider>
                <el-col :span="4">{{this.demand.predictTime}}</el-col>
                <el-divider direction="vertical"></el-divider>
              </div>
            </div>
            <div id="employerInfo">

            </div>
            <div id="introduce">

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
            demand:{},
            searchInfo:"",
            demandList:{}
          }
        },
      components:{
          Nav
      },
        methods: {


        },
        mounted() {
            console.log(this.$route.params.demand)
            this.demand = this.$route.params.demand;
        }
    }
</script>

<style scoped>
    #main{
        width:50%;
        height: auto;
        margin: auto
    }

    #demandInfo{
        height: 200px;
        margin-top:30px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        background-color: #ffffff
    }
    #employerInfo{
        height: 200px;
        margin-top:30px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        background-color: #ffffff;

    }
    #introduce{
        margin-top:30px;
        height: auto;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        background-color: #ffffff
    }
    #list div{
        height:100px;
        border-bottom: 1px solid #eff2f7;
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
