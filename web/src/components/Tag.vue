<template>
  <div>
    <el-tabs v-model="activeName"
             @tab-click="tagClick"
             @tab-remove="tagRemove"
             closable>
      <el-tab-pane v-for="tag in tags"
                   :key="tag.name"
                   :name="tag.name">
        <template #label>
          <span @contextmenu.prevent="openContextmenu(tag,$event)">{{ tag.name }}</span>
        </template>
        <keep-alive>
          <!--解决多次加载接口 el-tab-pane有bug-->
          <router-view v-if="activeName === tag.name"/>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
    <div
        :style="{left:left+'px',top:top+'px'}"
        class="contextmenu"
        v-if="visible">
      <span class="contextmenu_item" @click="closeOtherTag">关闭其他</span>
      <span class="contextmenu_item" @click="closeAllTag">全部关闭</span>
      <span class="contextmenu_item" @click="closeMenu">取消</span>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      activeName: '首页',
      tags: [{name: '首页', path: '/index'}],
      visible: false,
      left: 0,
      top: 0,
      activeTag: '',
    }
  },
  created() {
    this.init()
  },
  methods: {
    openContextmenu(tag, e) {
      this.left = e.clientX
      this.top = e.clientY
      this.visible = true
      this.activeTag = tag
    },
    closeContextmenu() {
      this.visible = false
    },
    closeOtherTag() {
      this.visible = false;
      let tags = this.tags
      for (let i = 1; i < tags.length; i++) {
        if (this.activeTag.name === tags[i].name) {
          continue
        }
        this.tagRemove(this.tags[i].name)
        i--
      }
    },
    closeAllTag() {
      this.visible = false;
      let tags = this.tags
      for (let i = 1; i < tags.length;) {
        this.tagRemove(tags[i].name)
      }
    },
    closeMenu() {
      this.visible = false
    },
    /**
     * 初始化，添加当前路由到tags
     */
    init() {
      let route = this.$route
      this.tagAdd(route)
    },
    /**
     * 添加tag
     * @param route 路由
     */
    tagAdd(route) {
      let addTag = {name: route.name, path: route.path};
      this.activeName = addTag.name
      // 如果是相同的路由地址，不添加
      if (this.tags.some(tag => tag.name === addTag.name)) {
        return
      }
      // 添加到tags数组中，并切换选中的tag
      this.tags.push(addTag)
    },
    /**
     * 点击tag
     * @param tag tag
     */
    tagClick(tag) {
      // 点击tag，路由也更换
      if (tag.name === this.$route.name) {
        return
      }
      let changeTag = this.tags[tag.index]
      this.$router.push(changeTag.path)
    },
    /**
     * 删除tag
     * @param name tag的name
     */
    tagRemove(name) {
      // 不能删除首页
      if (name === '首页') {
        return
      }
      // 获取删除的tag下标并删除
      let index = this.tags.findIndex(tag => tag.name === name)
      if (this.activeName === name) {
        // 当前选中的tag更换为上一个tag下标
        let changeTag = this.tags[index - 1]
        this.activeName = changeTag.name
        // 路由更换为上一个tag的地址
        this.$router.push(changeTag.path)
      }
      this.tags.splice(index, 1)
    }
  },
  watch: {
    /**
     * 监听路由切换
     * @param to
     */
    $route(to) {
      this.tagAdd(to)
    },
    visible(value) {
      if (value) {
        document.body.addEventListener('click', this.closeContextmenu)
      } else {
        document.body.removeEventListener('click', this.closeContextmenu)
      }
    }
  }
}
</script>

<style scoped>
.contextmenu {
  position: absolute;
  border-radius: 5px;
  background-color: #FFF;
  border: 1px solid #DCDFE6;
  z-index: 1000;
  padding: 5px 0;
  display: flex;
  flex-direction: column;
}

.contextmenu_item {
  padding: 5px 20px;
  font-size: 13px;
  font-weight: 500;
  color: #606266;
}

.contextmenu_item:hover {
  cursor: pointer;
  background-color: #eee;
}
</style>