<template>
  <div class="menu">
    <div>
      <a class="menu_top" href="/">
        <img src="../static/logo.png" class="menu_top_logo">
        <h3 class="menu_top_text" v-if="!collapse">后台管理系统</h3>
      </a>
    </div>
    <el-menu router
             :collapse="collapse"
             background-color="#545c64"
             text-color="#FFF"
             active-text-color="#ffd04b"
             class="el-menu-vertical">
      <el-submenu v-for="item in menuList" :key="item.id" :index="item.url?item.url:item.id">
        <template #title>
          <i :class="item.icon" class="menu_icon"></i>
          <span>{{ item.name }}</span>
        </template>
        <el-menu-item v-for="citem in item.children" :key="citem.id" :index="citem.url">
          <i :class="citem.icon" class="menu_icon"></i>
          <template #title>
            {{ citem.name }}
          </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>
<script>
import {listenerMenuCollapse} from "@/plugins/event";

export default {
  data() {
    return {
      collapse: false,
      menuList: [],
    }
  },
  created() {
    this.getMenus();
  },
  mounted() {
    listenerMenuCollapse((collapse) => {
      this.collapse = collapse;
    })
  },
  methods: {
    getMenus() {
      this.$api.getOwnedMenus().then(res => {
        if (res.status) {
          this.menuList = res.data;
        }
      })
    }
  }
}
</script>
<style scoped>

.menu {
  height: 100%;
  background-color: #545c64;
  box-shadow: 0 0 5px;
}

.menu_top {
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  height: 60px;
}

.menu_top_logo {
  width: 40px;
  height: 40px;
}

.menu_top_text {
  color: #FFF;
}

.menu_icon {
  color: #FFF;
  padding-right: 10px;
}

.el-menu {
  border-right: 0
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
}
</style>