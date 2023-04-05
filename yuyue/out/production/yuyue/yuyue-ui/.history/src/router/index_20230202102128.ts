import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/layout/Index.vue'

const routes:Array<RouteRecordRaw> = [
    {
        path:'/',
        name:'home',
        component:Layout
    },
    {
        path: '/dashboard',
        component: Layout,
        name: 'home',
        meta: {
            title: "首页",
            icon: "House",
            roles: ["sys:manage"]
        },
        children:[]
   }, 
   {
        path: '/system',
        component: Layout,
        alwaysShow: true,
        name: "system",
        meta: {
            title: "用户管理",
            icon: "User",
            roles: ["sys:manage"],
            parentId: 0,
        }
   },
   {
        path: '/system',
        component: Layout,
        alwaysShow: true,
        name: "system",
        meta: {
            title: "图书管理",
            icon: "Files",
            roles: ["sys:manage"],
            parentId: 0,
        }
   },
   {
        path: '/system',
        component: Layout,
        alwaysShow: true,
        name: "system",
        meta: {
            title: "系统管理",
            icon: "Setting",
            roles: ["sys:manage"],
            parentId: 0,
        },
        children:[
            // {
            //     path: '/department',
            //     component: "/system/department/department",
            //     alwaysShow: false,
            //     name: "department",
            //     meta: {
            //         title: "机构管理",
            //         icon: "Setting",
            //         roles: ["sys:dept"],
            //         parentId: 17,
            //     }
            // },
            // {
            //     path: '/department',
            //     component: "/system/department/department",
            //     alwaysShow: false,
            //     name: "department",
            //     meta: {
            //         title: "机构管理",
            //         icon: "Setting",
            //         roles: ["sys:dept"],
            //         parentId: 17,
            //     }
            // }
        ]
   }
]

// 创建router
const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router