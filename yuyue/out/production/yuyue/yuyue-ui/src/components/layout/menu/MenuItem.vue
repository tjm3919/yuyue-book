<!--
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-04 13:45:48
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-05 14:11:35
 * @FilePath: \yuyue-ui\src\components\layout\menu\MenuItem.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <template v-for="menu,index in menuList" :key="menu.path">
        <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="menu.path">
            <template #title>
                <el-icon>
                    <component :is="menu.meta.icon"></component>
                </el-icon>
                <span>{{ menu.meta.title }}</span>
            </template>
            <menu-item :menuList="menu.children"></menu-item>
        </el-sub-menu>
        <el-menu-item v-else :index="menu.path" style="color:#f4f4f5">
            <el-icon>
                <component :is="menu.meta.icon"></component>
            </el-icon>
            <template #title>
                <span>{{ menu.meta.title }}</span>
            </template> 
        </el-menu-item>
    </template>
</template>

<script lang="ts" setup>
    import { ref,reactive } from 'vue'

    defineProps(["menuList"])
    
</script>
<style lang="scss" scoped>
:deep(.el-icon){
    margin-right: 15px;
}
</style>