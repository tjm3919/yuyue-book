<script setup lang="ts">
import { ref } from 'vue'
import {useStore} from '../store'

const store = useStore();
const count = ref(0)
c
defineProps<{ msg: string }>()
</script>

<template>
  <h1>{{ msg }}</h1>
    <button type="button" @click="count++">count is {{ count }}</button>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
