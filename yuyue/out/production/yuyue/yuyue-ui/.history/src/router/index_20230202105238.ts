import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/layout/Index.vue'

const routes:Array<RouteRecordRaw> = [
    {
        path: '/:pathMatch(.*)*',
        name: 'notFound',
        component: () => import('@/components/page/NotFound/Not.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/components/page/login/login.vue'),
    },
    {
        path: '/',
        component: Layout,
        redirect: '/home', // 跳转的路径
        children: [
            {
                path: '/home',
                component: () => import('@/components/page/home/home.vue'),
                name: 'home',
                meta: {
                    title: "首页",
                    icon: "House",
                    roles: ["sys:manage"]
                }
            }
        ]
    },
   {
        path: '/system',
        component: () => import('@/components/page/user/User.vue'),
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
        component: () => import('@/components/page/book/Book.vue'),
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
        name: "system",
        meta: {
            title: "系统管理",
            icon: "Setting",
            roles: ["sys:manage"],
            parentId: 0,
        },
        children:[
            {
                path: '/department',
                component: () => import('@/components/HelloWorld.vue'),
                name: "department",
                meta: {
                    title: "机构管理",
                    icon: "Setting",
                    roles: ["sys:dept"],
                    parentId: 17,
                }
            },
            {
                path: '/department',
                component: () => import('@/components/HelloWorld.vue'),
                name: "department",
                meta: {
                    title: "机构管理",
                    icon: "Setting",
                    roles: ["sys:dept"],
                    parentId: 17,
                }
            }
        ]
   }
]

// 创建router
const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router