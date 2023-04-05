<template>
    <template v-for="menu in menuList" :key="menu.path">
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
    </template>
</template>

<script lang="ts" setup>
    import { ref,reactive } from 'vue'
    import { Document, Menu as IconMenu, Location, Setting, } from '@element-plus/icons-vue'


    const props = defineProps(["menuList"])
    

    const isCollapse = ref(false)
    const handleOpen = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const handleClose = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
</script>