<template>
  <div>
    <el-form inline>
      <el-form-item label="搜索">
        <el-input v-model="query.name" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="query.status" placeholder="状态" clearable>
          <el-option label="启动" value=true></el-option>
          <el-option label="停止" value=false></el-option>
        </el-select>
      </el-form-item>
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
          label="任务名称"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="className"
          label="任务类名"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="cron"
          label="cron"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="status"
          label="状态"
          min-width="100">
        <template #default="scope">
          <el-tag @click="updateStatus(scope.row)"
                  :type="scope.row.status ? '' : 'info'">
            {{ scope.row.status ? '启动' : '停止' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
          property="createTime"
          label="创建时间"
          min-width="100">
        <template #default="scope">
          {{ scope.row.createTime|time }}
        </template>
      </el-table-column>
      <el-table-column
          prop="remarks"
          label="备注"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作" width="360" fixed="right">
        <template #default="scope">
          <el-button size="mini"
                     @click="start(scope.row)">立即执行
          </el-button>
          <el-button size="mini"
                     @click="openLog(scope.row)">运行日志
          </el-button>
          <el-button size="mini"
                     @click="openUpdate(scope.row)">编辑
          </el-button>
          <el-button size="mini"
                     type="danger"
                     @click="deleteData(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        class="tablePage"
        background
        layout="prev, pager, next"
        :total="total"
        :current-page="query.pageNo"
        @current-change="pageData">
    </el-pagination>

    <el-dialog title="添加" :visible.sync="createShow" center>
      <el-form :model="createBody" :rules="createRules" ref="createForm" label-width="80px">
        <el-form-item label="任务名称" prop="name">
          <el-input v-model="createBody.name"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="任务类名" prop="className">
              <el-input v-model="createBody.className"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="cron" prop="cron">
              <el-input v-model="createBody.cron"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="createBody.remarks" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="createData">添加</el-button>
        <el-button @click="closeCreate">关闭</el-button>
      </template>
    </el-dialog>

    <el-dialog title="修改" :visible.sync="updateShow" center>
      <el-form :model="updateBody" :rules="updateRules" ref="updateForm" label-width="80px">
        <el-form-item label="任务名称" prop="name">
          <el-input v-model="updateBody.name"></el-input>
        </el-form-item>
        <el-form-item label="任务类名" prop="className">
          <el-input v-model="updateBody.className"></el-input>
        </el-form-item>
        <el-form-item label="cron" prop="cron">
          <el-input v-model="updateBody.cron"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="updateBody.remarks" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="updateData">修改</el-button>
        <el-button @click="closeUpdate">关闭</el-button>
      </template>
    </el-dialog>

    <el-dialog title="运行日志" width="40%" center :visible.sync="logShow">
      <el-table :data="logData"
                row-key="id"
                border>
        <el-table-column
            property="status"
            label="运行状态"
            min-width="50">
          <template #default="scope">
            <el-tag :type="scope.row.status ? '' : 'danger'">
              {{ scope.row.status ? '成功' : scope.row.status === false ? '失败' : '运行中' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            property="runTime"
            label="执行时间/ms"
            min-width="80">
        </el-table-column>
        <el-table-column
            property="startTime"
            label="运行时间"
            min-width="100">
          <template #default="scope">
            {{ scope.row.createTime|time }}
          </template>
        </el-table-column>
        <el-table-column
            property="message"
            label="运行信息"
            show-overflow-tooltip>
        </el-table-column>
      </el-table>
      <el-pagination
          class="tablePage"
          background
          layout="prev, pager, next"
          :total="logTotal"
          :current-page="logQuery.pageNo"
          @current-change="pageLogData">
      </el-pagination>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: true,

      data: [],
      total: 0,
      query: {
        pageNo: 1,
        name: '',
        status: '',
      },

      createShow: false,
      updateShow: false,
      logShow: false,

      createBody: {},
      updateBody: {},

      logData: [],
      logTotal: 0,
      logQuery: {
        pageNo: 1,
        crontabId: '',
      },

      createRules: {
        name: [
          {required: true, message: '请输入任务名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        className: [
          {required: true, message: '请输入任务类名', trigger: 'blur'},
          {max: 200, message: '长度在200个字符', trigger: 'blur'}
        ],
        cron: [
          {required: true, message: '请输入cron', trigger: 'blur'},
          {max: 100, message: '长度在100个字符', trigger: 'blur'}
        ],
        remarks: [
          {max: 200, message: '长度在 200 个字符'}
        ],
      },
      updateRules: {
        name: [
          {required: true, message: '请输入任务名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        className: [
          {required: true, message: '请输入任务类名', trigger: 'blur'},
          {max: 200, message: '长度在200个字符', trigger: 'blur'}
        ],
        cron: [
          {required: true, message: '请输入cron', trigger: 'blur'},
          {max: 100, message: '长度在100个字符', trigger: 'blur'}
        ],
        remarks: [
          {max: 200, message: '长度在 200 个字符'}
        ],
      }
    }
  },
  created() {
    this.listData();
  },
  methods: {
    listData() {
      this.loading = true
      this.$api.listCrontab(this.query).then(res => {
        if (res.status) {
          this.data = res.data.list
          this.total = res.data.total
        }
        this.loading = false
      })
    },
    pageData(page) {
      this.query.pageNo = page
      this.listData()
    },
    queryData() {
      this.query.pageNo = 1
      this.listData()
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
        this.$api.createCrontab(this.createBody).then(res => {
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
    },
    updateData() {
      this.$refs.updateForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateCrontab(this.updateBody).then(res => {
          if (res.status) {
            this.$message.success("修改成功");
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
        this.$api.deleteCrontab(data.id).then(res => {
          if (res.status) {
            this.$message.success("删除成功")
            this.listData()
          }
        })
      })
    },
    updateStatus(data) {
      let msg = "";
      if (!data.status) {
        msg = "此操作将开启任务, 是否继续?"
      } else {
        msg = "此操作将停止任务, 是否继续?";
      }
      this.$confirm(msg, '提示', {
        type: 'warning',
        center: true
      }).then(() => {
        let body = {};
        body.id = data.id;
        body.status = !data.status;
        this.$api.updateCrontabStatus(body).then(res => {
          if(res.status){
            this.$message.success("修改成功")
            this.listData()
          }
        })
      })
    },
    start(data) {
      this.$api.startCrontab(data.id).then(res => {
        if(res.status){
          this.$message.success("操作成功")
        }
      })
    },
    openLog(data) {
      this.logQuery.pageNo = 1;
      this.logQuery.crontabId = data.id;
      this.listLogData();
      this.logShow = true;
    },
    listLogData() {
      this.$api.listCrontabLog(this.logQuery).then(res => {
        if(res.status){
          this.logData = res.data.list
          this.logTotal = res.data.total
        }
      })
    },
    pageLogData(page) {
      this.logQuery.pageNo = page;
      this.listLogData();
    },
  }
}
</script>

<style scoped>
</style>