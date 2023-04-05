<template>
    <div class="title">
        <h3>愉悦图书管理系统</h3>
    </div>
    <Menuitem :menuList="menuList"></Menuitem>
</template>
<script lang="ts" setup>
    import Menuitem from './MenuItem.vue'

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
</script>
<style lang="scss" scoped>

</style>