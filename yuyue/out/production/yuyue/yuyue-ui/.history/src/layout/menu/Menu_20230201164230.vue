<template>
    <el-menu default-active="2" :collapse="isCollapse" 
        unique-opened router
        text-color="#fff" active-text-color="#409EFF" background-color="#304156"
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
                    }
                },
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
.menu-bg{
    color:white;
    padding: 10px 0px;
    background: #304156;
}
.el-menu {
    border-right: none;
}

</style>