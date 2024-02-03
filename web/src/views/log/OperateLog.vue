<template>
  <div>
    <el-form inline>
      <el-form-item label="搜索">
        <el-input v-model="query.username" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="query.ip" placeholder="IP"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="query.status" clearable placeholder="状态">
          <el-option label="成功" value="true">
          </el-option>
          <el-option label="失败" value="false">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker
            v-model="time"
            type="daterange"
            value-format="timestamp"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryData">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="data"
        v-loading="loading"
        row-key="id"
        border>
      <el-table-column
          prop="username"
          label="账号"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="ip"
          label="IP"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="description"
          label="描述"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="status"
          label="状态"
          min-width="100">
        <template #default="scope">
          <el-tag :type="scope.row.status? '' : 'danger'">
            {{ scope.row.status ? '成功' : '失败' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
          prop="url"
          label="请求地址"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="method"
          label="请求方法"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="params"
          label="请求参数"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="result"
          label="响应结果"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="errorMessage"
          label="错误信息"
          min-width="100"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间"
          min-width="100">
        <template #default="scope">
          {{ scope.row.createTime|time }}
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,

      data: [],
      total: 0,
      time: [],
      query: {
        pageNo: 1,
        username: '',
        ip: '',
        status: '',
        beginTime: '',
        endTime: '',
      },
    }
  },
  created() {
    this.listData()
  },
  methods: {
    listData() {
      this.loading = true
      this.$api.listOperateLog(this.query).then(res => {
        if (res.status) {
          this.data = res.data.list
          this.total = res.data.total
        }
        this.loading = false
      })
    },
    queryData() {
      this.query.pageNo = 1
      this.query.beginTime = this.time[0]
      this.query.endTime = this.time[1]
      this.listData()
    },
    pageData(page) {
      this.query.pageNo = page
      this.listData()
    },
  }
}
</script>

<style scoped>
</style>