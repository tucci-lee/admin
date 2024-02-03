import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
import moment from "moment"
import api from './plugins/api'
import VueClipboard from 'vue-clipboard2'
Vue.use(VueClipboard)

Vue.prototype.$event = new Vue()
Vue.prototype.$moment = moment
Vue.prototype.$api = api

Vue.config.productionTip = false

Vue.filter('time', (value)=>{
  return moment(parseInt(value)).format("YYYY-MM-DD HH:mm:ss")
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
