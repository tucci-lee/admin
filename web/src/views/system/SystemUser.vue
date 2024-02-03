<template>
  <div>
    <el-form inline>
      <el-form-item label="搜索">
        <el-input v-model="query.username" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="query.phone" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="query.isLock" clearable>
          <el-option label="锁定" value="true">
          </el-option>
          <el-option label="正常" value="false">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-cascader v-model="query.deptId"
                     :options="deptData"
                     :props="treeProps"
                     clearable
                     :show-all-levels="false"
                     placeholder="部门"></el-cascader>
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
          prop="username"
          label="账号"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="手机号"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="nickname"
          label="昵称"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="isLock"
          label="状态"
          min-width="100">
        <template #default="scope">
          <el-tag :type="scope.row.isLock? 'danger' : ''">
            {{ scope.row.isLock ? '锁定' : '正常' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
          prop="deptName"
          label="部门"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="remarks"
          label="备注"
          show-overflow-tooltip
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
      <el-table-column label="操作" width="300" fixed="right">
        <template #default="scope">
          <el-button
              size="mini"
              @click="openUpdateRole(scope.row)">角色
          </el-button>
          <el-button
              size="mini"
              @click="openPassword(scope.row)">密码
          </el-button>
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
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="username">
              <el-input v-model="createBody.username"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="createBody.password" type="password"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="createBody.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="createBody.email"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="createBody.nickname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="isLock">
              <el-radio-group v-model="createBody.isLock">
                <el-radio :label="false">正常</el-radio>
                <el-radio :label="true">锁定</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="角色" prop="roleId">
              <el-select
                  class="input_item"
                  v-model="createBody.roleIds"
                  multiple
                  clearable
                  placeholder="请选择"
                  @focus="queryRole">
                <el-option
                    v-for="role in roleData"
                    :key="role.id"
                    :label="role.name"
                    :value="role.id">
                </el-option>
                <div class="tablePage">
                  <el-pagination
                      background
                      layout="prev, pager, next"
                      :total="roleTotal"
                      :current-page="roleQuery.pageNo"
                      @current-change="pageRole">
                  </el-pagination>
                </div>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门" prop="deptId">
              <el-cascader
                  class="input_item"
                  v-model="createBody.deptId"
                  :options="deptData"
                  :props="treeProps"
                  clearable
                  :show-all-levels="false"
                  placeholder="部门"></el-cascader>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="createBody.remarks" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="closeCreate">取消</el-button>
        <el-button type="primary" @click="createData">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog title="修改" :visible.sync="updateShow" center>
      <el-form :model="updateBody" :rules="updateRules" ref="updateForm" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="username">
              <el-input v-model="updateBody.username" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门" prop="deptId">
              <el-cascader
                  class="input_item"
                  v-model="updateBody.deptId"
                  :options="deptData"
                  :props="treeProps"
                  clearable
                  :show-all-levels="false"
                  placeholder="部门"></el-cascader>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="updateBody.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="updateBody.email"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="updateBody.nickname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="isLock">
              <el-radio-group v-model="updateBody.isLock">
                <el-radio :label="false">正常</el-radio>
                <el-radio :label="true">锁定</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="updateBody.remarks" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="closeUpdate">取消</el-button>
        <el-button type="primary" @click="updateData">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="updatePasswordShow" center>
      <el-form :model="updatePasswordBody" :rules="updatePasswordRules" ref="updatePasswordForm" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="username">
              <el-input v-model="updatePasswordBody.username" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="updatePasswordBody.password" type="password"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button @click="closeUpdatePassword">取消</el-button>
        <el-button type="primary" @click="updatePassword">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog title="角色" :visible.sync="updateRoleShow" center>
      <el-form :model="updateRoleBody" :rules="updateRoleRules" ref="updateRoleForm">
        <el-form-item>
          <el-checkbox-group v-model="updateRoleBody.roleIds">
            <el-checkbox v-for="role in roleData" :key="role.id" :label="role.id">
              {{ role.name }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div class="tablePage">
        <el-pagination
            background
            layout="prev, pager, next"
            :total="roleTotal"
            :current-page="roleQuery.pageNo"
            @current-change="pageRole">
        </el-pagination>
      </div>
      <template #footer>
        <el-button @click="closeUpdateRole">关闭</el-button>
        <el-button type="primary" @click="updateRole">修改</el-button>
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
      total: 0,
      query: {
        pageNo: 1,
        username: '',
        phone: '',
        isLock: '',
        deptId: '',
      },

      createShow: false,
      updateShow: false,
      updatePasswordShow: false,
      updateRoleShow: false,

      createBody: {},
      updateBody: {},
      updatePasswordBody: {},
      updateRoleBody: {
        // 不设置这个elementui有bug
        roleIds:[]
      },

      roleData: [],
      roleTotal: 0,
      roleQuery: {
        pageNo: 1,
      },

      deptData: [],

      createRules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在6到32个字符', trigger: 'blur'}
        ],
        phone: [
          {pattern: '^[1][3-9][0-9]{9}$', message: '手机号格式不正确', trigger: 'blur'}
        ],
        email: [
          {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
        ],
        nickname: [
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        remarks: [
          {max: 200, message: '长度在200个字符', trigger: 'blur'},
        ]
      },
      updateRules: {
        phone: [
          {pattern: '^[1][3-9][0-9]{9}$', message: '手机号格式不正确', trigger: 'blur'}
        ],
        email: [
          {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
        ],
        nickname: [
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        remarks: [
          {max: 200, message: '长度在200个字符', trigger: 'blur'},
        ]
      },
      updatePasswordRules: {
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在6到32个字符', trigger: 'blur'}
        ],
      },
      updateRoleRules: {},

      treeProps: {
        checkStrictly: true,
        label: 'name',
        value: 'id',
      },
    }
  },
  created() {
    this.listData()
    this.listDept()
  },
  methods: {
    listData() {
      this.loading = true
      if (this.query.deptId) {
        this.query.deptId = this.query.deptId[this.query.deptId.length - 1];
      }
      this.$api.listUser(this.query).then(res => {
        if (res.status) {
          this.data = res.data.list
          this.total = res.data.total
        }
        this.loading = false
      })
    },
    queryData() {
      this.query.pageNo = 1
      this.listData()
    },
    pageData(page) {
      this.query.pageNo = page
      this.listData()
    },
    openCreate() {
      this.createBody.isLock = false
      this.createShow = true
    },
    closeCreate() {
      this.createShow = false
    },
    createData() {
      let deptIds = this.createBody.deptId
      if (deptIds) {
        this.createBody.deptId = deptIds[deptIds.length - 1]
      }
      this.$refs.createForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.createUser(this.createBody).then(res => {
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
      let deptIds = this.updateBody.deptId
      if (deptIds) {
        this.updateBody.deptId = deptIds[deptIds.length - 1]
      }
      this.$refs.updateForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateUser(this.updateBody).then(res => {
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
        this.$api.deleteUser(data.uid).then(res => {
          if (res.status) {
            this.$message.success("删除成功")
            this.listData()
          }
        })
      })
    },
    listDept() {
      this.$api.listDeptTree().then(res => {
        if (res.status) {
          this.deptData = res.data
        }
      })
    },
    listRole() {
      this.$api.listRole(this.roleQuery).then(res => {
        if (res.status) {
          this.roleData = res.data.list
          this.roleTotal = res.data.total
        }
      })
    },
    queryRole() {
      this.roleQuery.pageNo = 1
      this.listRole()
    },
    pageRole(page) {
      this.roleQuery.pageNo = page
      this.listRole()
    },
    openPassword(data) {
      this.updatePasswordBody = JSON.parse(JSON.stringify(data))
      this.updatePasswordShow = true
    },
    closeUpdatePassword() {
      this.updatePasswordShow = false
      this.$refs.updatePasswordForm.clearValidate()
    },
    updatePassword() {
      this.$refs.updatePasswordForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateUserPassword(this.updatePasswordBody).then(res => {
          if (res.status) {
            this.$message.success("修改成功")
            this.closeUpdatePassword()
          }
        })
      })
    },
    openUpdateRole(data) {
      this.updateRoleBody.uid = data.uid
      this.queryRole()
      this.$api.listUserRole(data.uid).then(res => {
        if (res.status) {
          this.updateRoleBody.roleIds = res.data;
          this.updateRoleShow = true
        }
      })
    },
    closeUpdateRole() {
      this.updateRoleShow = false
      this.$refs.updateRoleForm.clearValidate()
    },
    updateRole() {
      this.$refs.updateRoleForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateUserRole(this.updateRoleBody).then(res => {
          if (res.status) {
            this.$message.success("修改成功")
            this.closeUpdateRole()
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