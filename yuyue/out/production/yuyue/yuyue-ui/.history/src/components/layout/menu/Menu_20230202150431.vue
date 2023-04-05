<template>
    <MenuLogo></MenuLogo>
    <el-menu :default-active="activeIdex" class="el-menu-vertical-demo" :collapse="isCollapse" 
        router background-color="#304156"
        @open="handleOpen" @close="handleClose">
        <Menuitem :menuList="menuList"></Menuitem>
    </el-menu>



    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
    <el-radio-button :label="false">expand</el-radio-button>
    <el-radio-button :label="true">collapse</el-radio-button>
  </el-radio-group>
  <el-menu default-active="2"  :collapse="isCollapse" @open="handleOpen" @close="handleClose" >
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
      <el-icon><icon-menu /></el-icon>
      <template #title>Navigator Two</template>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <template #title>Navigator Three</template>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <template #title>Navigator Four</template>
    </el-menu-item>
  </el-menu>
</template>
<script lang="ts" setup>
    import { ref,reactive,computed } from 'vue' 
    import { useRoute } from 'vue-router' 
    import Menuitem from './MenuItem.vue'
    import MenuLogo from './MenuLogo.vue'

    //当前路由
    const route = useRoute()
    // 获取当前激活的菜单index(路径)
    const activeIdex = computed(()=>{
        const {path} = route
        // console.log(path)
        return path
    })
    
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
                    path: '/approval',
                    component: "/system/approval",
                    alwaysShow: false,
                    name: "approval",
                    meta: {
                        title: "审批管理",
                        icon: "Setting",
                        roles: ["sys:approval"],
                        parentId: 17,
                    }
                },
                {
                    path: '/user',
                    component: "/system/user",
                    alwaysShow: false,
                    name: "user",
                    meta: {
                        title: "用户管理",
                        icon: "Setting",
                        roles: ["sys:user"],
                        parentId: 17,
                    }
                }
            ]
       },
    ])

    const isCollapse = ref(true)
    const handleOpen = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const handleClose = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
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