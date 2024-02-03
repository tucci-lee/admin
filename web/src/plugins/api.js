import axios from '@/plugins/axios'

export default {
    imageCaptcha: (query) => axios.get("/captcha/image", {params: query}),
    signin: (body) => axios.post("signin", body),
    signout: () => axios.post("signout"),
    getOwnedMenus: () => axios.get("sys/res/ownedMenus"),
    getProfile: () => axios.get("profile"),
    updateProfile: (body) => axios.put("profile", body),
    updatePassword: (body) => axios.put("password", body),
    listResTree: () => axios.get("sys/res/tree"),
    createRes: (body) => axios.post("sys/res", body),
    updateRes: (body) => axios.put("sys/res", body),
    deleteRes: (id) => axios.delete("sys/res/" + id),
    listDeptTree: () => axios.get("sys/dept/tree"),
    createDept: (body) => axios.post("sys/dept", body),
    updateDept: (body) => axios.put("sys/dept", body),
    deleteDept: (id) => axios.delete("sys/dept/" + id),
    listRole: (query) => axios.get("sys/role", {params: query}),
    createRole: (body) => axios.post("sys/role", body),
    updateRole: (body) => axios.put("sys/role", body),
    deleteRole: (id) => axios.delete("sys/role/" + id),
    listRoleRes: (id) => axios.get("sys/role/res/" + id),
    updateRoleRes: (body) => axios.put("sys/role/res", body),
    listUser: (query) => axios.get("sys/user", {params: query}),
    createUser: (body) => axios.post("sys/user", body),
    updateUser: (body) => axios.put("sys/user", body),
    deleteUser: (uid) => axios.delete("sys/user/" + uid),
    updateUserPassword: (body) => axios.put("sys/user/password", body),
    listUserRole: (uid) => axios.get("sys/user/role/" + uid),
    updateUserRole: (body) => axios.put("sys/user/role", body),
    listSigninLog: (query) => axios.get("sys/log/signin", {params: query}),
    listOperateLog: (query) => axios.get("sys/log/operate", {params: query}),
    listCrontab: (query) => axios.get('crontab', {params: query}),
    createCrontab: (body) => axios.post("crontab", body),
    updateCrontab: (body) => axios.put("crontab", body),
    deleteCrontab: (id) => axios.delete("crontab/" + id),
    listCrontabLog: (query) => axios.get('crontab/log', {params: query}),
    updateCrontabStatus: (body) => axios.put("crontab/status", body),
    startCrontab: (id) => axios.post("crontab/start/" + id),
}






