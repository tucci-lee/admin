<template>
  <div style="height: 100vh">
    <iframe :src="druidPath" width="100%" height="100%" frameborder="no"></iframe>
  </div>
</template>

<script>
import axios from "axios"

export default {
  data() {
    return {
      druidPath: axios.defaults.baseURL + '/druid/index.html'
    }
  },
}
</script>

<style scoped>

</style>