<template>
  <div>
    <div style="font-size: 20px;margin-left: 10%;margin-top: 3%">
      <span>往来明细</span>
    </div>
    <el-divider></el-divider>
    <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort = "{prop: 'date', order: 'descending'}"
    >
      <el-table-column
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
          prop="tradeTime"
          label="日期"
          sortable
          width="250">
      </el-table-column>
      <el-table-column
          prop="tradeType"
          label="途径"
          width="250">
      </el-table-column>
      <el-table-column
          prop="tradeContent"
          label="详情"
          width="300">
      </el-table-column>
      <el-table-column
          prop="tradeCapital"
          sortable
          label="金额"
          >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Detailed",
  data() {
    return {
      tableData: [],
      user:'',
    }
  },
  methods: {
    formatter(row, column) {
      return row.address;
    },
    selFlow(){
      this.$axios.post('empcenter/flow',this.$qs.stringify({
          phoneNumber:this.user.phoneNumber,
          type:"雇主",
          tradeState:"ACQ.TRADE_HAS_SUCCESS"
        })).then(res=>{
          this.tableData=res.data;
          })
    }
  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("user"));
    this.selFlow();
  }
}
</script>

<style scoped>

</style>
