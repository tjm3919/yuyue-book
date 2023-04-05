<template>
   <div class="big_div">
        {{ date }}
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive } from 'vue'
   let date = ref(new Date())

</script>
<style lang='scss' scoped>
</style>