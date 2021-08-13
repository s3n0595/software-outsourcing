<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>作品管理</el-breadcrumb-item>
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
            :disabled="this.delData.length===0"
        >批量删除</el-button>
        <el-input v-model="searchInfo" placeholder="请输入作品标题" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="searchUnion">搜索</el-button>
        <el-button type="primary" @click="getSearchState(0)">未审核</el-button>
        <el-button type="primary" @click="getSearchState(1)">已通过</el-button>
        <el-button type="primary" @click="getSearchState(2)">未通过</el-button>
      </div>
      <el-table
          :data="tableDataList"
          align="center"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="unionId" label="联盟序号" sortable width="110"></el-table-column>
        <el-table-column prop="unionName" label="联盟名称" width="120"></el-table-column>
        <el-table-column prop="allProviderId" label="服务商ID"></el-table-column>
        <el-table-column prop="applicationTime" label="创建时间"></el-table-column>
        <el-table-column prop="auditStatus" label="审核状态">
          <template slot-scope="scope">
            <el-tag type="info" v-if="scope.row.auditStatus === 0">未审核</el-tag>
            <el-tag type="success" v-if="scope.row.auditStatus === 1">已通过</el-tag>
            <el-tag type="danger" v-if="scope.row.auditStatus === 2">未通过</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <!--      slot-scope="scope" 可以获取到父组件传递的参数，将这些参数使用到子组件插槽里-->
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-lx-roundcheck"
                :disabled="scope.row.auditStatus !== 0"
                @click="handleAccept(scope.$index, scope.row)"
            >通过</el-button>
            <el-button
                type="text"
                icon="el-icon-lx-roundclose"
                :disabled="scope.row.auditStatus !== 0"
                class="red"
                @click="handleRefuse(scope.$index, scope.row)"
            >未通过</el-button>
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
      unionList: [],
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
      return this.unionList.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
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
      this.$confirm("确认删除该联盟?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.unionId);
        // axios传递数组 在数组后加入''
        let baseUrl = 'baseUrl';
        this.$axios.get(`${baseUrl}/audit/deleteUnion`,
            {params:{
                unionIds: delIds + '',
              }}).then(res=>{
          console.log(res)
          const code = res.data
          if (code.code === 200) {
            this.$message.success("删除成功")
            this.getUnion()
          } else {
            this.$message.error("删除失败")
          }
        }).catch(err=>{
          console.log(err)
        })
      });
    },
    //关键词搜索
    searchUnion() {
      this.isShowloading = true
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/searchUnion`,{params:{
          unionName: this.searchInfo
        }}).then(res=>{
        console.log(res)
        const code = res.data
        this.pageNo = 1;
        this.unionList = code.data
        this.total = this.unionList.length
        this.isShowloading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    //审核状态筛选
    getSearchState(State) {
      this.isShowloading = true;
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/unionState`,{params:{
          auditStatus: State
        }}).then(res=>{
        console.log(res)
        const code = res.data
        this.pageNo = 1;
        this.unionList = code.data
        this.total = this.unionList.length
        this.isShowloading = false
      }).catch(err=>{
        console.log(err)
      })
    },
    //审核通过
    handleAccept(index, row){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/union`,
          {params:{
              'unionId':row.unionId,
              'auditStatus':1
            }}).then(res=>{
        console.log(res.data)
        const code = res.data
        if (code.code === 200) {
          this.$message.success("修改成功")
          this.getUnion()
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
      this.$axios.get(`${baseUrl}/audit/union`,
          {params:{
              'unionId' : row.unionId,
              'auditStatus' : 2
            }}).then(res=>{
        console.log(res)
        const code = res.data
        if (code.code === 200) {
          this.$message.success("修改成功")
          this.getUnion()
        } else {
          this.$message.error("修改失败，请重新修改")
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    //获取联盟列表
    getUnion(){
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/audit/unionList`,
          {params:{

            }}).then(res=>{
        console.log(res)
        const code = res.data
        this.unionList = code.data
        this.total = this.unionList.length;
        this.isShowloading = false
      }).catch(error=>{
        console.log(error)
      })
    },
  },
  mounted() {
    this.getUnion();
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

