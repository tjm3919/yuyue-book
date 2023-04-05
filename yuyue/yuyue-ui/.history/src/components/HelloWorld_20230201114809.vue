<script setup lang="ts">
import { ref,computed } from 'vue'
import {useStore} from '@/store'
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'

const store = useStore();
const count = ref(0)
const showcount = computed(()=>{
  return store.getters['getCount']
})
const addVtn = ()=>{
  store.commit('setCount',++count.value)
}
defineProps<{ msg: string }>()
</script>

<template>
    <el-row class="mb-4">
    <el-button>Default</el-button>
    <el-button type="primary">Primary</el-button>
    <el-button type="success">Success</el-button>
    <el-button type="info">Info</el-button>
    <el-button type="warning">Warning</el-button>
    <el-button type="danger">Danger</el-button>
  </el-row>
  <h1>{{ msg }}</h1>
    <button type="button" @click="addVtn">新增 {{ count }}</button>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
