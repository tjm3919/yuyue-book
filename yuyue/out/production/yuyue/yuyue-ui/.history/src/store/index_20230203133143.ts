// store.ts
import { InjectionKey } from 'vue'
import { createStore, useStore as baseUseStore, Store } from 'vuex'

// import {defineStore} from 'pinia';
// import {createPinia} from 'pinia';
// // 引入pinia的持久化存储插件
// import piniaPersist from 'pinia-plugin-persist';

export interface State {
  id: string,
  count: number,
  collapse: boolean,
  userInfo: {
    id:number,
    username: string,
    isValidate: boolean,
    headurl: string,
    token: string
  }
}

export const key: InjectionKey<Store<State>> = Symbol()

export const store = createStore<State>({
  state: {
    id: 'GlobalState',
    count: 0,
    collapse: false,
    userInfo: {
      id: 0,
      username: "",
      isValidate:false,
      headurl:'',
      token:""
    }
  },
  mutations:{
    setCount(state:State, count:number){
      state.count = count;
    },
    setCollapse:(state:State,collapse:boolean)=>{
      state.collapse = collapse;
    }
  },
  getters:{
    getCount(state:State){
      return state.count;
    },
    getCollapse:(state:State)=>{
      return state.collapse;
    }
  }
})

// 定义自己的 `useStore` 组合式函数
export function useStore () {
  return baseUseStore(key)
}