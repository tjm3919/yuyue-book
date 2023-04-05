import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

module.exports = {
  devServer:{
    proxy:{
      '/api':{//表示拦截以/api开头的请求路径
        target:'http://http://localhost:8000',
        changOrigin: true,//是否开启跨域
        pathRewrite:{
          '^/api':'' //重写api，把api变成空字符，因为我们真正请求的路径是没有api的
        }
      }
    }
  }
}

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
