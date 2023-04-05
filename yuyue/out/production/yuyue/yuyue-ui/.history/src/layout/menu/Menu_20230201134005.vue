<template>
    <div>
        愉悦图书管理系统
    </div>

</template>
<script lang="ts" setup>
    import { ref } from 'vue'
    import { Document, Menu as IconMenu, Location, Setting, } from '@element-plus/icons-vue'
    import menuitem from './MenuItem.vue'

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