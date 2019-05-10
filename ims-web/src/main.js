// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Router from 'vue-router'
import App from './App'
import routes from './routes'
import Axios from 'axios'
// Styles
import './assets/styles/styles.scss'

Vue.use(Router)
const router = new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes
})
Vue.use({
  install (Vue) {
    Vue.prototype.$api = Axios.create({
      baseURL: 'http://192.168.205.213:6910/api/'
    })
    Vue.prototype.$http = Axios.create({
      baseURL: 'http://192.168.205.213:6910/'
    })
  }
})

/* eslint-disable no-new */
new Vue({
  router,
  ...App
}).$mount('#app')
