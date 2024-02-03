<template>
  <div class="header">
    <div>
      <i class="el-icon-s-fold header__left" v-if="!menuCollapse" @click="changeMenu"></i>
      <i class="el-icon-s-unfold header__left" v-else @click="changeMenu"></i>
    </div>
    <div>
      <el-dropdown>
        <el-avatar :src="require('../static/avatar.png')" style="cursor: pointer;"></el-avatar>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click.native="toProfilePage">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="signout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {publishMenuCollapse} from "@/plugins/event";
import {deleteToken} from "@/plugins/cache";

export default {
  data() {
    return {
      menuCollapse: false,
      updatePasswordShow: false,
      updatePasswordBody:{},
    }
  },
  created() {
  },
  methods: {
    changeMenu() {
      this.menuCollapse = !this.menuCollapse
      publishMenuCollapse(this.menuCollapse)
    },
    toProfilePage() {
      this.$router.push("/user/profile")
    },
    signout(){
      this.$api.signout().then(res => {
        if(res.status){
          deleteToken();
          this.$router.push("/signin")
        }
      })
    }
  }
}
</script>

<style scoped>
.header {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header__left {
  color: #FFF;
  font-size: 25px;
}

.header__left:hover {
  cursor: pointer;
}
</style>