<template>
   <div>首页</div>
   <el-image style="width: 100px; height: 100px" src="@/image/logo/愉悦.png" fit="scale-down" alt="logo走丢了">
      <template #error>
          <div class="image-slot">
            <el-icon><icon-picture /></el-icon>
          </div>
      </template>
   </el-image>
   <img src="@/image/logo/愉悦.png"/>

</template>
<script setup lang='ts'>
   import { ref,reactive } from 'vue'
</script>
<style lang='scss' scoped>
</style>