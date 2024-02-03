<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane label="个人信息">
        <el-card>
          <el-row>
            <el-col :span="8">登录名：{{ profile.username }}</el-col>
            <el-col :span="8">昵称：{{ profile.nickname }}</el-col>
          </el-row>
          <el-row class="profile_info">
            <el-col :span="8">手机号：{{ profile.phone }}</el-col>
            <el-col :span="8">邮箱：{{ profile.email }}</el-col>
          </el-row>
          <el-row class="profile_info">
            <el-col>创建时间：{{ profile.createTime|time }}</el-col>
          </el-row>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="修改信息">
        <el-card class="profile_card">
          <el-form :model="updateProfileBody" :rules="updateProfileRules" ref="updateProfileForm" label-width="100px">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="updateProfileBody.nickname"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="updateProfileBody.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="updateProfileBody.email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateProfile">修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="修改密码">
        <el-card class="profile_card">
          <el-form :model="updatePasswordBody" :rules="updatePasswordRules" ref="updatePasswordForm" label-width="100px">
            <el-form-item label="原密码" prop="oldPassword">
              <el-input v-model="updatePasswordBody.oldPassword" type="password"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="password">
              <el-input v-model="updatePasswordBody.password" type="password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
              <el-input v-model="updatePasswordBody.confirmPassword" type="password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updatePassword">修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {deleteToken} from "@/plugins/cache";

export default {
  data() {
    const validatePass2 = (rule, value, callback) => {
      if (value !== this.updatePasswordBody.password) {
        callback(new Error('两次密码不一致'));
      } else {
        callback();
      }
    }
    return {
      profile: {},

      updateProfileBody: {},
      updatePasswordBody: {},

      updateProfileRules: {
        nickname: [
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        phone: [
          {pattern: '^[1][3-9][0-9]{9}$', message: '手机号格式不正确', trigger: 'blur'}
        ],
        email: [
          {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
        ],
      },
      updatePasswordRules: {
        oldPassword: [
          {required: true, message: '请输入原密码', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在6到32个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在6到32个字符', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请输入确认密码', trigger: 'blur'},
          {validator: validatePass2, trigger: 'blur'}
        ],
      },
    }
  },
  created() {
    this.getProfile()
  },
  methods: {
    getProfile() {
      this.$api.getProfile().then(res => {
        this.profile = res.data
        this.updateProfileBody = res.data
      })
    },
    updateProfile() {
      this.$refs['updateProfileForm'].validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updateProfile(this.updateProfileBody).then(res => {
          if(res.status){
            this.$message.success("修改成功")
          }
        })
      })
    },
    updatePassword(){
      this.$refs['updatePasswordForm'].validate(valid => {
        if (!valid) {
          return false
        }
        this.$api.updatePassword(this.updatePasswordBody).then(res => {
          if(res.status){
            this.$message.success("修改成功")
            deleteToken()
            this.$router.push("/signin")
          }
        })
      })
    }
  },
}
</script>


<style scoped>
.profile_info {
  font-size: 15px;
  margin-top: 20px;
}

.profile_card {
  width: 50%;
}
</style>