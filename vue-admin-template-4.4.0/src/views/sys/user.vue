<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.salname" placeholder="商家名称" />
          <el-input v-model="searchModel.phone" placeholder="商家电话" />
          <el-button type="primary" round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" circle />
        </el-col>
      </el-row>
    </el-card>
    <!--    结果列表-->
    <el-card>
      <el-table :data="salList" stripe style="width: 100%">
        <el-table-column type="index" label="序号" width="80" />
        <el-table-column prop="id" label="商家编号" width="180" />
        <el-table-column prop="salname" label="商家名称" width="180" />
        <el-table-column prop="phone" label="电话" width="180"/>
        <el-table-column prop="address" label="地址" width="180"/>
        <el-table-column prop="result" label="备注" />
        <el-table-column  label="操作" width="180"/>
      </el-table>
    </el-card>
<!--    分页-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      salList: []
    }
  },
  methods: {
    handleSizeChange() {

    },
    handleCurrentChange() {

    }
  }

}
</script>

<style>
#search .el-input{
  width: 200px;
  margin-right: 15px;
}
</style>
