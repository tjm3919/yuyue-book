/*
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-04 13:45:17
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-04 14:02:41
 * @FilePath: \yuyue-ui\vite.config.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
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
    // proxy: {// 跨域代理
    //   '/apis': {
    //     // target: 'http://' + env.VUE_APP_BASE_API,
    //     target: 'http://localhost:8000', 
    //     changeOrigin: true,
    //     rewrite: (path) => path.replace(/^\/apis/, '')
    //   },
    //   // 代理 WebSocket 或 socket
    //   // '/socket.io': {
    //   //   target: 'ws://localhost:3000',
    //   //   ws: true
    //   //  }
    // },
    proxy: {
      // 带选项写法：http://localhost:5173/api/bar -> http://jsonplaceholder.typicode.com/bar
      '/api': {
        target: 'http://localhost:8000/api',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      // 代理 websockets 或 socket.io 写法：ws://localhost:5173/socket.io -> ws://localhost:5174/socket.io
      '/socket.io': {
        target: 'ws://localhost:3000',
        ws: true,
      },
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
