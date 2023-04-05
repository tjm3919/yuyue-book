<template>
   <div class="big_div">
        {{ date }}
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive } from 'vue'
    import datetime from '@/http/config/DateTime'
    
   let date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))

</script>
<style lang='scss' scoped>
</style>