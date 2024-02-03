import Vue from 'vue'
import VueRouter from 'vue-router'

import {getToken} from "@/plugins/cache"

import Layout from "@/views/Layout.vue";
import Index from "@/views/Index.vue";
import Signin from "@/views/Signin.vue";
import UserProfile from "@/views/system/UserProfile.vue";
import SystemRes from "@/views/system/SystemRes.vue";
import SystemDept from "@/views/system/SystemDept.vue";
import SystemRole from "@/views/system/SystemRole.vue";
import SystemUser from "@/views/system/SystemUser.vue";
import SigninLog from "@/views/log/SigninLog.vue";
import OperateLog from "@/views/log/OperateLog.vue";
import Druid from "@/views/monitor/Druid.vue";
import Crontab from "@/views/monitor/Crontab.vue";
Vue.use(VueRouter)

const routes = [
    {
        path: '/signin',
        name: '注册',
        component: Signin
    },
    {
        path: '/',
        name: '首页',
        component: Layout,
        redirect: '/index',
        children: [
            {
                path: '/index',
                name: '首页',
                component: Index
            },
            {
                path: '/user/profile',
                name: '个人信息',
                component: UserProfile
            },
            {
                path: '/system/res',
                name: '资源管理',
                component: SystemRes
            },
            {
                path: '/system/dept',
                name: '部门管理',
                component: SystemDept
            },
            {
                path: '/system/role',
                name: '角色管理',
                component: SystemRole
            },
            {
                path: '/system/user',
                name: '用户管理',
                component: SystemUser
            },
            {
                path: '/system/log/signin',
                name: '登录日志',
                component: SigninLog
            },
            {
                path: '/system/log/operate',
                name: '操作日志',
                component: OperateLog
            },
            {
                path: '/monitor/druid',
                name: 'Druid监控',
                component: Druid
            },
            {
                path: '/monitor/crontab',
                name: '定时任务',
                component: Crontab
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 路由前置守卫，没有token跳转到登录页面
router.beforeEach((to, from, next) => {
    if (to.path !== "/signin") {
        const token = getToken()
        if (token == null) {
            next({path: "/signin"})
        } else {
            next();
        }
    } else {
        next();
    }
})

export default router
