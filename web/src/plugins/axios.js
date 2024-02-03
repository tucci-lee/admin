import axios from "axios"
import router from "@/router"
import {Message} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {deleteToken, getToken} from "@/plugins/cache"

axios.defaults.withCredentials = true;
axios.defaults.baseURL = "http://localhost:9000" // 开发使用


axios.interceptors.request.use(
    config => {
        config.headers['Authorization'] = "Bearer " + getToken()
        return config
    },
    error => {
        return Promise.reject(error)
    }
)
/**
 * axios拦截器，错误直接提示
 */
axios.interceptors.response.use(
    resp => {
        if (resp.data.code === 11111) {
            deleteToken()
            router.push("/signin")
            return
        }
        if (typeof(resp.data.status) != "undefined" && !resp.data.status) {
            Message.error(resp.data.message)
        }
        return resp.data
    },
    err => {
        let response = err.response
        if (response) {
            Message.error(response.data.message)
        } else {
            Message.error("网络异常")
        }
        return Promise.reject(err)
    })

export default axios