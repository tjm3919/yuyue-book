/*
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-01 10:02:51
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-04 12:30:21
 * @FilePath: \yuyue\vite.config.ts
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
    open: true
  },
  resolve: {
    alias: [
      {
        find: '@',
        replacement: resolve(__dirname,'src')
      }
    ]
  }
})
