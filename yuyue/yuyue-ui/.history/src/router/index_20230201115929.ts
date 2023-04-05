import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/layout/index'

const routes:Array<RouteRecordRaw> = [
    {
        path:'/',
        name:'home',
        component:Layout
    }
]

// 创建router
const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router