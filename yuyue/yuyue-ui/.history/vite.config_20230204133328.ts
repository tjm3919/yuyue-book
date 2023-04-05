import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0',
    port: 3000,
    open: true,
    proxy: {// 跨域代理
      '/apis': {
        // target: 'http://' + env.VUE_APP_BASE_API,
        target: 'http://localhost:8000', 
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/apis/, '')
      },
      // 代理 WebSocket 或 socket
      // '/socket.io': {
      //   target: 'ws://localhost:3000',
      //   ws: true
      //  }
    },
  },
  resolve: {
    alias: [
      {
        find: '@',
        replacement: resolve(__dirname,'src')
      }
    ]
  },
})
