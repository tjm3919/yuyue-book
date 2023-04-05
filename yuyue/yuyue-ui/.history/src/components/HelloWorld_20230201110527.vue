<script setup lang="ts">
import { ref,computed } from 'vue'
import {useStore} from '../store'

const store = useStore();
const count = ref(0)
const showcount = computed(()=>{
  return store.getters
})
const addVtn = ()=>{
  store.commit('setCount',++count.value)
}
defineProps<{ msg: string }>()
</script>

<template>
  <h1>{{ msg }}</h1>
    <button type="button" @click="addVtn">新增 {{ count }}</button>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
