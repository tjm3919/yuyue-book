<template>
    <div class="title menu-bg">
        <h3>愉悦图书管理系统</h3>
    </div>
    <el-menu default-active="2" class="menu-bg" :collapse="isCollapse" 
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
.menu-bg{
    background-color: #304156;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 230px;
    min-height: 400px;
}
.el-menu {
    border-right: none;
}
::v-deep .el-sub-menu .el-sub-menu__title {
    color: #f4f4f5 !important;
}
/* .el-submenu .is-active .el-submenu__title {
    border-bottom-color : #1890ff;
}*/
::v-deep .el-menu .el-menu-item {
    color: #bfcbd9;
}
/*菜单点中文字的颜色*/
::v-deep .el-menu-item.is-active {
    color: #409eff !important ;
}
/*当前打开菜单的所有子菜单颜色*/
::v-deep .is-opened .el-menu-item {
    background-color:#1f2d3d !important;
}
/*鼠标移动菜单的颜色*/
::v-deep .el-menu-item:hover {
    background-color: #001528 !important;
}
</style>