<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>成功案例</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <!--    批量删除按钮-->
        <el-button
            type="primary"
            icon="delete"
            class="handle-del mr10"
            @click="delAll"
        >批量删除</el-button>
        <el-input v-model="searchInfo" placeholder="请输入顾问名称" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="searchCase">搜索</el-button>
        <el-button type="primary" @click="getSearchState(0)">待确定</el-button>
        <el-button type="primary" @click="getSearchState(1)">已显示</el-button>
        <el-button type="primary" @click="getSearchState(2)">不显示</el-button>
      </div>
      <el-table
          :data="tableDataList"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="tradeWorksId" label="序号" sortable width="110"></el-table-column>
        <el-table-column prop="providerAccount.providerName" label="服务商" width="120"></el-table-column>
        <el-table-column prop="employerAccount.employerName" label="雇主"></el-table-column>
        <el-table-column prop="works.worksTitle" label="标题"></el-table-column>
        <el-table-column prop="works.worksDescribe" label="描述"></el-table-column>
        <el-table-column prop="works.annexPath" label="图片"></el-table-column>
        <el-table-column prop="works.worksPrice" label="价格"></el-table-column>
        <el-table-column prop="showCase" label="显示状态">
          <template slot-scope="scope">
            <el-tag type="info" v-if="scope.row.showCase === 0">待确定</el-tag>
            <el-tag type="success" v-if="scope.row.showCase === 1">已显示</el-tag>
            <el-tag type="danger" v-if="scope.row.showCase === 2">不显示</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <!--      slot-scope="scope" 可以获取到父组件传递的参数，将这些参数使用到子组件插槽里-->
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-lx-roundcheck"
                :disabled="scope.row.showCase !== 0"
                @click="handleAccept(scope.$index, scope.row)"
            >显示</el-button>
            <el-button
                type="text"
                icon="el-icon-lx-roundclose"
                :disabled="scope.row.showCase !== 0"
                class="red"
                @click="handleRefuse(scope.$index, scope.row)"
            >不显示</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
            background
            @current-change="currentChange"
            @size-change="handleSizeChange"
            layout="total, prev, pager, next, sizes, jumper"
            :total="total"
            :page-size="pageSize"
            :page-sizes="[5,10,15]"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      url:"",
      caseList: [],
      searchInfo:'',
      // 总条数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 5,
      isShowloading: false,
      delData:[],
      formLabelWidth: "120px"
    };
  },
  computed:{
    // 动态获取分页data
    tableDataList(){
      return this.caseList.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    },
  },
  methods: {
    // 当前页数发送改变
    currentChange(val) {
      console.log("当前页"+val);
      this.pageNo = val;
    },
    // // 每页显示条数
    handleSizeChange(val){
      console.log("每页条数更改为"+val);
      this.pageSize = val;
    },
    // 当选择项发送变化时，执行一下方法
    handleSelectionChange(delData) {
      // 将复选框选中的结果集合赋值给delData
      this.delData = delData;
    },
    //批量删除
    delAll() {
      this.$confirm("确认删除该案例?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.tradeWorksId);
        // axios传递数组 在数组后加入''
        let baseUrl = 'baseUrl';
        this.$axios.get(`${baseUrl}/portal/deleteCase`,
            {params:{
                tradeWorksId: delIds + '',
              }}).then(res=>{
          console.log(res)
          const code = res.data
          if (code.code === 200) {
            this.$message.success("删除成功")
            this.getSuccessCase()
          } else {
            this.$message.error("删除失败")
          }
        }).catch(err=>{
          console.log(err)
        })
      });
    },
    //关键词搜索
    searchCase() {
      this.isShowloading = true
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/searchCase`,{params:{
          worksTitle: this.searchInfo
        }}).then(res=>{
        console.log(res)
        const code = res.data
        this.pageNo = 1;
        this.caseList = code.data
        this.total = this.caseList.length
        this.isShowloading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    //显示状态筛选
    getSearchState(State) {
      this.isShowloading = true;
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/isShow`,{params:{
          showCase: State
        }}).then(res=>{
        console.log(res)
        const code = res.data
        this.pageNo = 1;
        this.caseList = code.data
        this.total = this.caseList.length
        this.isShowloading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    //审核通过
    handleAccept(index, row){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/showCase`,
          {params:{
              'tradeWorksId':row.tradeWorksId,
              'showCase':1
            }}).then(res=>{
        console.log(res)
        const code = res.data
        if (code.code === 200) {
          this.$message.success("修改成功")
          this.getSuccessCase()
        } else {
          this.$message.error("修改失败，请重新修改")
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    //审核不通过
    handleRefuse(index, row){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/showCase`,
          {params:{
              'tradeWorksId' : row.tradeWorksId,
              'showCase' : 2
            }}).then(res=>{
        console.log(res)
        const code = res.data
        if (code.code === 200) {
          this.$message.success("修改成功")
          this.getSuccessCase()
        } else {
          this.$message.error("修改失败，请重新修改")
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    //获取成功案例列表
    getSuccessCase(){
      this.isShowloading = true;
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/caseList`,
          {params:{

            }}).then(res=>{
        console.log(res)
        const code = res.data
        this.caseList = code.data
        this.total = this.caseList.length;
        this.isShowloading = false
      }).catch(error=>{
        console.log(error)
      })
    },

  },
  mounted() {
    this.getSuccessCase();
  }
};
</script>
<style>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
.el-switch__core:after {
  content: "";
  position: absolute;
  top: 1px;
  left: 1px;
  border-radius: 100%;
  transition: all .3s;
  width: 16px;
  height: 16px;
  background-color: #fff;
}

.el-switch.is-checked .el-switch__core:after {
  left: 100%;
  margin-left: -17px;
}
</style>

