<template>
    <MenuLogo></MenuLogo>
    <el-menu default-active="2" :collapse="isCollapse" 
        router background-color="#304156"
        @open="handleOpen" @close="handleClose">
        <Menuitem :menuList="menuList"></Menuitem>
    </el-menu>
</template>
<script lang="ts" setup>
    import { ref,reactive } from 'vue'
    import Menuitem from './MenuItem.vue'
    import MenuLogo from './MenuLogo.vue'


    // import axios from "axios";
    // import { useRoute } from 'vue-router'
    // import router from '@/router'
    // import { GlobalStore } from '@/store'
    // import Logo from '../../logo/Logo.vue';

    // const store = GlobalStore();

    //当前路由
    // const route = useRoute();
    // const activeIdex = computed(() => {
    //     const { path } = route;
    //     return path;
    // })
    
    let menuList = reactive([
    // {
    //     path: '/:pathMatch(.*)*',
    //     name: 'notFound',
    //     component: () => import('@/components/page/NotFound/Not.vue'),
    // },
    // {
    //     path: '/login',
    //     name: 'login',
    //     component: () => import('@/components/page/login/login.vue'),
    // },
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
        path: '/user',
        component: Layout,
        name: "user",
        meta: {
            title: "用户管理",
            icon: "User",
            roles: ["sys:user"],
        },
        children: [
            {
                path: '/user',
                component: () => import('@/components/page/user/User.vue'),
                name: "user",
                meta: {
                    title: "用户管理",
                    icon: "User",
                    roles: ["sys:user"],
                },     
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
            roles: ["sys:manage"],
            parentId: 0,
        }
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
                path: '/page',
                component: () => import('@/components/page/system/System.vue'),
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
                component: () => import('@/components/page/system/System.vue'),
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
    ])

    const isCollapse = ref(false)
    const handleOpen = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const handleClose = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 230px;
    min-height: 400px;
}
.el-menu {
    border-right: none;
}

:deep(.el-sub-menu .el-sub-menu__title) {
    color: #f4f4f5 !important;
}
/* .el-submenu .is-active .el-submenu__title {
    border-bottom-color : #1890ff;
}*/
:deep(.el-menu .el-menu-item) {
    color: #bfcbd9;
}
/*菜单点中文字的颜色*/
:deep( .el-menu-item.is-active) {
    color: #409eff !important;
}
/*当前打开菜单的所有子菜单颜色*/
:deep( .is-opened .el-menu-item) {
    background-color:#1f2d3d !important;
}
/*鼠标移动菜单的颜色*/
:deep( .el-menu-item:hover) {
    background-color: #001528 !important;
}
</style>