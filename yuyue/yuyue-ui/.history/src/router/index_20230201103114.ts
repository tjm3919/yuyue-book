import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/components/HelloWorld.vue'
import { createHistogram } from 'perf_hooks'

const routers:Array<RouteRecordRaw> = [
    {
        path:'/',
        name:'home',
        component:Layout
    }
]

// 创建router
const router = createRouter({
    history:createHistogram
})