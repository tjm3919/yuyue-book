<template>
    <div class="title">
        <h3>愉悦图书管理系统</h3>
    </div>
    <el-menu default-active="2" class="el-menu-vertical-demo" :collapse="isCollapse" @open="handleOpen" @close="handleClose">
        <el-sub-menu index="1">
            <template #title>
                <el-icon><location /></el-icon>
                <span>Navigator One</span>
            </template>
            <el-menu-item-group>
                <template #title><span>Group One</span></template>
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
                <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
                <template #title><span>item four</span></template>
                <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
            <el-icon><setting /></el-icon>
            <template #title>Navigator Four</template>
        </el-menu-item>
    </el-menu>
    <Menuitem :menuList="menuList"></Menuitem>
</template>
<script lang="ts" setup>
    import { ref,reactive } from 'vue'
    import Menuitem from './MenuItem.vue'
    import { Document, Menu as IconMenu, Location, Setting, } from '@element-plus/icons-vue'

    let menuList = reactive([
       {
            path: '/dashboard',
            component: "Layout",
            meta: {
                title: "首页",
                icon: "el-icon-s-home",
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
                title: "系统管理",
                icon: "el-icon-menu",
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
<style lang="scss" scoped>

</style>