<template>
  <div>
    <el-form :model="signinBody" :rules="signinRules" ref="signinForm" class="signinForm">
      <h3>登陆</h3>
      <el-form-item prop="username">
        <el-input v-model="signinBody.username" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="signinBody.password" placeholder="密码" type="password"></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="14">
          <el-form-item prop="captcha">
            <el-input v-model="signinBody.captcha" placeholder="验证码"
                      @keyup.enter.native="signin()"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <img :src="captchaBase64" @click="refreshCaptcha()" alt=""/>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="signin()" class="input_item">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {setToken} from "@/plugins/cache";

export default {
  data() {
    return {
      signinBody: {
        username: '',
        password: '',
        captchaKey: '',
        captcha: '',
      },
      captchaBase64: '',
      signinRules: {
        username: [
          {required: true, message: "请输入账号", trigger: 'blur'},
          {max: 20, message: '长度在20个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ],
        captcha: [
          {required: true, message: "请输入验证码", trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.refreshCaptcha();
  },
  methods: {
    /**
     * 刷新验证码图片
     */
    refreshCaptcha() {
      this.$api.imageCaptcha({type: 101})
          .then(res => {
            this.captchaBase64 = res.data.base64;
            this.signinBody.captchaKey = res.data.key;
          })
    },
    /**
     * 登陆
     */
    signin() {
      // 校验
      this.$refs.signinForm.validate(valid => {
        if (!valid) {
          return false;
        }
        // 登陆请求
        this.$api.signin(this.signinBody).then(res => {
          if (res.status) {
            setToken(res.data)
            this.$router.push("/");
          } else {
            this.refreshCaptcha();
          }
        })
      });
    }
  }
}
</script>

<style scoped>
.signinForm {
  border-radius: 10px;
  width: 350px;
  padding: 30px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin: 10% auto 0;
}

.input_item {
  width: 100%
}
</style>