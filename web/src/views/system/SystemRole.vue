<template>
  <div>
    <el-form inline>
      <el-form-item label="搜索">
        <el-input v-model="query.name" placeholder="名称"></el-input>
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
          label="名称"
          min-width="100">
      </el-table-column>
      <el-table-column
          prop="roleChar"
          label="角色字符"
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
      <el-table-column label="操作" width="250" fixed="right">
        <template #default="scope">
          <el-button
              size="mini"
              @click="openUpdateRes(scope.row)">资源
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
            <el-form-item label="名称" prop="name">
              <el-input v-model="createBody.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色字符" prop="roleChar">
              <el-input v-model="createBody.roleChar"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="createBody.remarks" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="资源" prop="resIds">
          <el-tree
              class="form_tree"
              node-key="id"
              show-checkbox
              ref="createTree"
              :data="resData"
              :props="treeProps">
          </el-tree>
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
            <el-form-item label="名称" prop="name">
              <el-input v-model="updateBody.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色字符" prop="roleChar">
              <el-input v-model="updateBody.roleChar"></el-input>
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

    <el-dialog title="资源" :visible.sync="updateResShow" center>
      <el-form :model="updateResBody" :rules="updateResRules" ref="updateResForm">
        <el-form-item prop="resIds">
          <el-tree
              class="form_tree"
              node-key="id"
              show-checkbox
              ref="updateTree"
              :data="resData"
              :props="treeProps">
          </el-tree>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="closeUpdateRes">取消</el-button>
        <el-button type="primary" @click="updateRes">确定</el-button>
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
        name: '',
      },

      createShow: false,
      updateShow: false,
      updateResShow: false,

      createBody: {},
      updateBody: {},
      updateResBody: {},

      resData: [],

      createRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        roleChar: [
          {max: 100, message: '长度在100个字符', trigger: 'blur'},
        ],
        remarks: [
          {max: 200, message: '长度在200个字符', trigger: 'blur'},
        ]
      },
      updateRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        roleChar: [
          {max: 100, message: '长度在100个字符', trigger: 'blur'},
        ],
        remarks: [
          {max: 200, message: '长度在200个字符', trigger: 'blur'},
        ]
      },
      updateResRules: {},

      treeProps: {
        children: 'children',
        label: 'name',
      }
    }
  },
  created() {
    this.listData()
    this.listRes()
  },
  methods: {
    listData() {
      this.loading = true
      this.$api.listRole(this.query).then(res => {
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
      this.createShow = true
    },
    closeCreate() {
      this.createShow = false
    },
    createData() {
      let resTree = this.$refs.createTree;
      // 选中的节点
      let checkedResIds = resTree.getCheckedKeys();
      // 半选中的节点
      let checkedParentIds = resTree.getHalfCheckedKeys();
      this.createBody.resIds = checkedResIds.concat(checkedParentIds);
      this.$refs.createForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.createRole(this.createBody).then(res => {
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
        this.$api.updateRole(this.updateBody).then(res => {
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
        this.$api.deleteRole(data.id).then(res => {
          if (res.status) {
            this.$message.success("删除成功")
            this.listData()
          }
        })
      })
    },
    listRes() {
      this.$api.listResTree().then(res => {
        if (res.status) {
          this.resData = res.data
        }
      })
    },
    openUpdateRes(data) {
      this.updateResBody.id = data.id
      this.updateResShow = true

      this.$api.listRoleRes(data.id).then(res => {
        if (res.status) {
          let checkIds = this.getCheckedIds(this.resData, res.data)
          this.$refs.updateTree.setCheckedKeys(checkIds)
        }
      })
    },
    closeUpdateRes() {
      this.updateResShow = false
      // 清空tree中的选中数据
      this.$refs.updateTree.setCheckedKeys([])
      // 清除校验
      this.$refs.updateResForm.clearValidate()
    },
    updateRes() {
      let resTree = this.$refs.updateTree
      // 选中的节点
      let checkedResIds = resTree.getCheckedKeys()
      // 半选中的节点
      let checkedParentIds = resTree.getHalfCheckedKeys()
      this.updateResBody.resIds = checkedResIds.concat(checkedParentIds)
      this.$refs.updateResForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateRoleRes(this.updateResBody).then(res => {
          if (res.status) {
            this.$message.success("修改成功")
            this.updateResShow = false
          }
        })
      })
    },
    /**
     * elementui的tree组件，选中父节点后默认所有字节也被选中。
     * 所以设置默认选中的数据时，只将所有的子节点选中，父节点默认变成半选中个
     *
     * @param resData 所有的资源数据
     * @param resIds 角色关联的资源id
     */
    getCheckedIds(resData, resIds) {
      let checkedIds = []
      for (let i of resData) {
        if (i.children && i.children.length > 0) {
          let childArr = this.getCheckedIds(i.children, resIds)
          checkedIds = checkedIds.concat(childArr)
        } else {
          for (let j of resIds) {
            if (i.id === j) {
              checkedIds.push(i.id)
            }
          }
        }
      }
      return checkedIds
    },
  }
}
</script>

<style scoped>
.form_tree {
  border: 1px solid #DCDFE6;
  border-radius: 4px;
}
</style>