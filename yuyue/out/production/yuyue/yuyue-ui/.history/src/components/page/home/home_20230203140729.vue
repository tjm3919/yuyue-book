<template>
   <img src="@/image/logo/愉悦.png" class="logo"/>

</template>
<script setup lang='ts'>
   import { ref,reactive,onBeforeMount } from 'vue'
   import { useStore } from '@/store/index' 

   onBeforeMount(()=>{
      islogin()
   })

   const store = useStore();
   const islogin = () =>{
      const user = store.state.userInfo;
      if(user.id<=0){
         
      }
   }

</script>
<style lang='scss' scoped>
.logo{
   width: 460px;
   height: 400px;
}
</style>