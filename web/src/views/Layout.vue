<template>
  <el-container class="layout">
    <Menu/>
    <el-container>
      <el-header class="layout_header">
        <Header/>
      </el-header>
      <el-main>
        <Tag/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Menu from "@/components/Menu.vue";
import Header from "@/components/Header.vue";
import Tag from "@/components/Tag.vue";

export default {
  components: {
    Tag, Menu, Header
  }
}
</script>


<style>
.layout {
  height: 100%;
}

.layout_header {
  background-color: #545c64;
}
</style>