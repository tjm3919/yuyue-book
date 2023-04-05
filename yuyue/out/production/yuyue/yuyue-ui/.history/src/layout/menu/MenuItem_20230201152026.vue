<template>
    <template v-for="menu,index in menuList" :key="menu.path">
        <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="index">
            <template #title>
                <i :class=" menu.meta.icon"></i>
                <span>{{ menu.meta.title }}</span>
            </template>
            <menu-item :menuList="menu.menuList"></menu-item>
        </el-sub-menu>
        <!-- <el-menu-item v-else :index="index">
            <template #title>
                <i :class="menu.meta.icon"></i>
                <span>{{ menu.meta.title }}</span>
            </template> 
        </el-menu-item> -->
    </template>
</template>

<script lang="ts" setup>
    import { ref,reactive } from 'vue'
    import { Document, Menu as IconMenu, Location, Setting, } from '@element-plus/icons-vue'


    defineProps(["menuList"])
    

    const isCollapse = ref(false)
    const handleOpen = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
    const handleClose = (key: string | number, keyPath: string[]) => {
        console.log(key, keyPath)
    }
</script>