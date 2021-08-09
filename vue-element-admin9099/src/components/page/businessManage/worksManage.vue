<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>作品管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table
          :data="worksList"
          border
          class="table"
          ref="multipleTable"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="worksId" label="作品序号" sortable width="110"></el-table-column>
        <el-table-column prop="worksTitle" label="标题" width="120"></el-table-column>
        <el-table-column prop="demandType.demandTypeName" label="类型"></el-table-column>
        <el-table-column prop="worksPrice" label="价格"></el-table-column>
        <el-table-column prop="worksDescribe" label="描述"></el-table-column>
        <el-table-column prop="releaseTime" label="时间"></el-table-column>
        <el-table-column prop="providerId" label="发布者ID"></el-table-column>
        <el-table-column prop="worksAddress" label="链接"></el-table-column>
        <el-table-column prop="auditStatus" label="审核"></el-table-column>
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
      worksList: [],
      // 总条数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 5,
      isShowloading: false,
      formLabelWidth: "120px"
    };
  },
  methods: {
    // 当前页数发送改变
    currentChange(val) {
      console.log("当前页"+val);
      this.pageNo = val;
      this.getWorks();
    },
    // // 每页显示条数
    handleSizeChange(val){
      console.log("每页条数更改为"+val);
      this.pageSize = val;
      this.currentChange(1);
    },
    // 当选择项发送变化时，执行一下方法
    handleSelectionChange(delData) {
      // 将复选框选中的结果集合赋值给delData
      this.delData = delData;
    },
    //获取作品列表
    getWorks(){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/works`,
          {params:{
            page: this.pageNo,
              pageSize:this.pageSize
            }}).then(res=>{
        console.log(res)
        const code = res.data
        this.worksList = code.data
      }).catch(error=>{
        console.log(error)
      })
    },
    //获取总数
    getTotal(){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/total`).then(totalWorks=>{
        console.log(totalWorks)
        const total = totalWorks.data
        this.total = total.data
      }).catch(error=>{
        console.log(error)
      })
    }

  },
  mounted() {
    this.getWorks();
    this.getTotal();
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

