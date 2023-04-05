<script setup lang="ts">

</script>
    
<template>
    <div class="big-404">
        <div class="font-blod desc">
            <div class="text-7xl code">404</div>
            <div ref="content" class="text-3xl content">The Page</div>
            <button active="scale-90 transform" class="rounded-lg transition btn">
                👉 Go Home
            </button>
        </div>

        <!-- <img src="../../../public/svg/32.svg" class="cover" alt="page not found" /> -->
    </div>
</template>
    
<style lang="scss" scoped>
.big-404{
    height: 100%;
    background: #ffffff url('../../../public/svg/32.svg') no-repeat fixed center;
}
.code {
    margin-bottom: 20px;
}

.content {
    height: 40px;
}

.cover {
    height: auto;
    width: 700px;
    margin: 0 5px;
    max-width: 100%;
    max-height: 100%;
}

.desc {
    flex: 1;
    width: 300px;
}

.btn {
    font-size: 20px;
    margin-top: 30px;
    padding: 12px 20px;
    --tw-shadow-color: 8, 145, 178;
    --tw-shadow: 0 4px 6px -1px rgba(var(--tw-shadow-color), 0.1),
        0 2px 4px -1px rgba(var(--tw-shadow-color), 0.06);
    box-shadow: 0 0 #0000, 0 0 #0000, 0 0 #0000, var(--tw-shadow);
}
</style>

    