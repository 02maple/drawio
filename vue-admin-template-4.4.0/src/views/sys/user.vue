<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.salerName" placeholder="商家名称" clearable />
          <el-input v-model="searchModel.phone" placeholder="商家电话" clearable />
          <el-button type="primary" round icon="el-icon-search" @click="getSalersList">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" circle @click="openEditUI(null)" />
        </el-col>
      </el-row>
    </el-card>
    <!--    结果列表-->
    <el-card>
      <el-table :data="salList" stripe style="width: 100%">
        <el-table-column type="index" label="序号" width="50" />
        <el-table-column prop="salerId" label="商家编号" width="80" />
        <el-table-column prop="salerName" label="商家名称" width="140" />
        <el-table-column prop="phone" label="电话" width="110" />
        <el-table-column prop="address" label="地址" width="360" />
        <el-table-column prop="remark" label="备注" />
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.salerId)" />
            <el-button type="danger" icon="el-icon-delete" size="mini" circle />
          </template>
        </el-table-column>
        <el-table-column label="操作状态" width="130">
          <template slot-scope="scope">
            <!--            <el-switch-->
            <!--              v-model="scope.row.result"-->
            <!--              active-color="#5B7BFA"-->
            <!--              inactive-color="#dadde5"-->
            <!--              active-value="1"-->
            <!--              @click="updateResult()"-->
            <!--            />-->
            <!--            审核通过或者不通过按钮-->
            <el-button type="success" round size="mini" icon="el-icon-check" @click="updateResult(1,scope.row.salerId),scope.row.result=1" />
            <el-button type="danger" round size="mini" icon="el-icon-close" @click="updateResult(0,scope.row.salerId),scope.row.result=0" />
          </template>
        </el-table-column>
        <el-table-column prop="result" label="状态" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.result == 1" type="success">通过</el-tag>
            <el-tag v-if="scope.row.result == 0" type="danger">不通过</el-tag>
            <el-tag v-if="scope.row.result == 2" type="warning">待审核</el-tag>
          </template>

        </el-table-column>
      </el-table>
    </el-card>
    <!--    分页-->
    <el-pagination
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!--  添加信息表单  -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form ref="salersFormRef" :model="salersForm" :rules="rules">
        <el-form-item label="商家名称" prop="salerName" :label-width="formLabelWidth">
          <el-input v-model="salersForm.salerName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone" :label-width="formLabelWidth">
          <el-input v-model="salersForm.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="地址" prop="address" :label-width="formLabelWidth">
          <el-input v-model="salersForm.address" autocomplete="off" />
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="salersForm.remark" autocomplete="off" />
        </el-form-item>
        <!--        <el-form-item label="审核状态" :label-width="formLabelWidth">-->
        <!--          <el-switch-->
        <!--            v-model="salersForm.result"-->
        <!--            active-value="1"-->
        <!--            inactive-value="0"-->
        <!--          />-->
        <!--        </el-form-item>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveSalers">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import salersApi from '@/api/salersManage'
export default {
  data() {
    return {
      formLabelWidth: '130px',
      salersForm: {},
      dialogFormVisible: false,
      title: '',
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      salList: [],
      rules: {
        salerName: [
          { required: true, message: '请输入商家名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getSalersList()
  },
  methods: {
    saveSalers() {
      // 触发表单验证
      this.$refs.salersFormRef.validate((valid) => {
        if (valid) {
          // 表单验证过后，保存数据，提交给后台
          salersApi.saveSaler(this.salersForm).then(response => {
            // 成功提示
            this.$message({
              message: response.msg,
              type: 'success'
            })
            // 关闭对话框
            this.dialogFormVisible = false
            // 刷新表格
            this.getSalersList()
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    clearForm() {
      this.salersForm = {}
      // 当退出表单时，消除表单验证信息
      this.$refs.salersFormRef.clearValidate()
    },
    openEditUI(salerId) {
      if (salerId == null) {
        this.title = '新增商家'
      } else {
        this.title = '修改商家信息'
        salersApi.getSalerById(salerId).then(response => {
          this.salersForm = response.data
        })
      }

      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getSalersList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getSalersList()
    },
    getSalersList() {
      salersApi.getSalersList(this.searchModel).then(response => {
        this.salList = response.data.rows
        this.total = response.data.total
      })
    },
    updateResult(result, salerId) {
      salersApi.updateResult(result, salerId)
    }
  }
}
</script>

<style>
#search .el-input{
  width: 200px;
  margin-right: 15px;
}
.el-dialog .el-input{
  width: 80%;
}
</style>
