<template>
    <!-- <el-container class="layout">
        <el-aside width="200px" class="aside">Aside</el-aside>
            <el-container>
                <el-header class="header">Header</el-header>
                <el-main class="main">Main</el-main>
                <el-footer class="footer">Footer</el-footer>
            </el-container>
    </el-container> -->

  <div class="common-layout">
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-container>
        <el-header  class="header">Header</el-header>
        <el-main class="main">Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

</template>
  
<script lang="ts" setup>

</script>

<style lang="scss" scoped>
.layout{
    .aside{
        background-color: aqua;
    }
    .header{
        background-color: aquamarine;
    }
    .main{
        background-color: blueviolet;
    }
    .footer{
        background-color: gold;
    }
}
</style>