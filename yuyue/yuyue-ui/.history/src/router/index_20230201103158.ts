import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/components/HelloWorld.vue'

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

export def