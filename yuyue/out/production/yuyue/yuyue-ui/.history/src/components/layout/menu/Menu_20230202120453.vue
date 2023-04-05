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
       {
            path: '/home',
            component: "Layout",
            meta: {
                title: "首页",
                icon: "House",
                roles: ["sys:manage"]
            }
       }, 
       {
            path: '/book',
            component: "Layout",
            alwaysShow: true,
            name: "book",
            meta: {
                title: "图书管理",
                icon: "Files",
                roles: ["sys:manage"],
                parentId: 0,
            },
            children:[
            ]
       },
       {
            path: '/system',
            component: "Layout",
            alwaysShow: true,
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
                    component: "/system/department/department",
                    alwaysShow: false,
                    name: "department",
                    meta: {
                        title: "审批管理",
                        icon: "Setting",
                        roles: ["sys:dept"],
                        parentId: 17,
                    }
                },
                {
                    path: '/department',
                    component: "/system/department/department",
                    alwaysShow: false,
                    name: "department",
                    meta: {
                        title: "用户管理",
                        icon: "Setting",
                        roles: ["sys:dept"],
                        parentId: 17,
                    }
                }
            ]
       },
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