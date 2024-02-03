<template>
  <div>
    <el-form inline>
      <el-form-item>
        <el-button type="primary" @click="queryData">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="openCreate">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="data"
        v-loading="loading"
        row-key="id"
        border>
      <el-table-column
          prop="name"
          label="名称"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="manager"
          label="负责人"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="managerPhone"
          label="联系电话"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="seq"
          label="排序"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间"
          min-width="100">
        <template #default="scope">
          {{ scope.row.createTime|time }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="scope">
          <el-button
              size="mini"
              @click="openUpdate(scope.row)">编辑
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="deleteData(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="添加" :visible.sync="createShow" center>
      <el-form :model="createBody" :rules="createRules" ref="createForm" label-width="80px">
        <el-form-item label="上级资源" prop="pid">
          <el-cascader class="input_item"
                       v-model="createBody.pid"
                       :options="data"
                       :props="{checkStrictly: true,label: 'name',value: 'id',}"
                       clearable
                       placeholder="不选择默认顶级资源"></el-cascader>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="createBody.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序" prop="seq">
              <el-input-number v-model="createBody.seq" :min="0" :max="99" class="input_item"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人" prop="manager">
              <el-input v-model="createBody.manager"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="managerPhone">
              <el-input v-model="createBody.managerPhone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button @click="closeCreate">取消</el-button>
        <el-button type="primary" @click="createData">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog title="修改" :visible.sync="updateShow" center>
      <el-form :model="updateBody" :rules="updateRules" ref="updateForm" label-width="80px">
        <el-form-item label="上级资源" prop="pid">
          <el-cascader class="input_item"
                       v-model="updateBody.pid"
                       :options="data"
                       :props="{checkStrictly: true,label: 'name',value: 'id',}"
                       clearable
                       placeholder="不选择默认顶级资源"></el-cascader>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="updateBody.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序" prop="seq">
              <el-input-number v-model="updateBody.seq" :min="0" :max="99" class="input_item"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人" prop="manager">
              <el-input v-model="updateBody.manager"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="managerPhone">
              <el-input v-model="updateBody.managerPhone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button @click="closeUpdate">取消</el-button>
        <el-button type="primary" @click="updateData">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,

      data: [],

      createShow: false,
      updateShow: false,

      createBody: {},
      updateBody: {},

      createRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        manager: [
          {required: true, message: '请输入负责人', trigger: 'blur'},
          {max: 100, message: '长度在100个字符', trigger: 'blur'}
        ],
        managerPhone: [
          {required: true, message: '请输入联系电话', trigger: 'blur'},
          {pattern: '^[1][3-9][0-9]{9}$', message: '手机号格式不正确', trigger: 'blur'}
        ],
      },
      updateRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        manager: [
          {required: true, message: '请输入负责人', trigger: 'blur'},
          {max: 100, message: '长度在100个字符', trigger: 'blur'}
        ],
        managerPhone: [
          {required: true, message: '请输入联系电话', trigger: 'blur'},
          {pattern: '^[1][3-9][0-9]{9}$', message: '手机号格式不正确', trigger: 'blur'}
        ],
      },
    }
  },
  created() {
    this.listData()
  },
  methods: {
    listData() {
      this.loading = true
      this.$api.listDeptTree().then(res => {
        if (res.status) {
          this.data = res.data
        }
        this.loading = false
      })
    },
    queryData() {
      this.listData();
    },
    openCreate() {
      this.createShow = true
    },
    closeCreate() {
      this.createShow = false
    },
    createData() {
      this.$refs.createForm.validate(valid => {
        if (!valid) {
          return false
        }
        let pids = this.createBody.pid
        if (pids) {
          this.createBody.pid = pids[pids.length - 1]
        }
        this.$api.createDept(this.createBody).then(res => {
          if (res.status) {
            this.$message.success("添加成功")
            this.closeCreate()
            this.listData()
            this.createBody = {}
          }
        })
      })
    },
    openUpdate(data) {
      this.updateBody = JSON.parse(JSON.stringify(data))
      this.updateShow = true
    },
    closeUpdate() {
      this.updateShow = false
      this.$refs.updateForm.clearValidate()
    },
    updateData() {
      this.$refs.updateForm.validate(valid => {
        if (!valid) {
          return false
        }
        let pids = this.updateBody.pid
        if (Array.isArray(pids)) {
          this.updateBody.pid = pids[pids.length - 1]
        }
        if (this.updateBody.pid === this.updateBody.id) {
          this.$message.error("部门上级不可以是当前部门")
          return
        }
        this.$api.updateDept(this.updateBody).then(res => {
          if (res.status) {
            this.$message.success("修改成功")
            this.closeUpdate()
            this.listData()
          }
        })
      })
    },
    deleteData(data) {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        center: true,
        type: 'warning'
      }).then(() => {
        this.$api.deleteDept(data.id).then(res => {
          if (res.status) {
            this.$message.success("删除成功")
            this.listData()
          }
        })
      })
    }
  }
}
</script>

<style scoped>
.input_item {
  width: 100%
}
</style>