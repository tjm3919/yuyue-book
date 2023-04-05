import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import Layout from '@/components/layout/Index.vue'

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
                    icon: "House"
                }
            }
        ]
    },
   {
        path: '/book',
        component: Layout,
        name: "book",
        meta: {
            title: "图书管理",
            icon: "Files",
            roles: ["sys:book"],
            parentId: 0,
        },
        children: [
            {
                path: '/book',
                component: () => import('@/components/page/book/book/Book.vue'),
                name: "book",
                meta: {
                    title: "图书管理",
                    icon: "Files",
                    roles: ["sys:book"],
                },     
            },
            {
                path: '/book',
                component: () => import('@/components/page/book/remark/remark.vue'),
                name: "book",
                meta: {
                    title: "评论管理",
                    icon: "Files",
                    roles: ["sys:remark"],
                },     
            }
        ]
   },
   {
        path: '/system',
        component: () => import('@/components/page/comment/Comment.vue'),
        name: "comment",
        meta: {
            title: "评论管理",
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
                path: '/approval',
                component: () => import('@/components/page/system/approval/Approval.vue'),
                name: "approval",
                meta: {
                    title: "审批管理",
                    icon: "Setting",
                    roles: ["sys:approval"]
                }
            },
            {
                path: "/user",
                component: () => import('@/components/page/system/user/User.vue'),
                name: "user",
                meta: {
                    title: "用户管理",
                    icon: "User",
                    roles: ["sys:user"]
                },
            }
        ]
   },
   
]

// 创建router
const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router