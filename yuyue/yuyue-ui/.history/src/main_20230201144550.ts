import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index'
import { store, key } from './store/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//引入图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


let app=createApp(App).use(router).use(store,key).use(ElementPlus).mount('#app')
// ts elementpuls 图标注册
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}