<template>
    <div class="title menu-color">
        <h3>愉悦图书管理系统</h3>
    </div>
    <el-menu default-active="2" class="el-menu-vertical-demo menu-color" :collapse="isCollapse" 
        unique-opened
        @open="handleOpen" @close="handleClose">
        <Menuitem :menuList="menuList"></Menuitem>
    </el-menu>
</template>
<script lang="ts" setup>
    import { ref,reactive } from 'vue'
    import Menuitem from './MenuItem.vue'

    let menuList = reactive([
       {
            path: '/dashboard',
            component: "Layout",
            meta: {
                title: "首页",
                icon: "House",
                roles: ["sys:manage"]
            },
            children:[]
       }, 
       {
            path: '/system',
            component: "Layout",
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
            component: "Layout",
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
                        title: "机构管理",
                        icon: "el-icon-document",
                        roles: ["sys:dept"],
                        parentId: 17,
                    },
                    children:[]
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
<style lang="scss" scoped>
.menu-color{
    background-color: #304156;
}
</style>