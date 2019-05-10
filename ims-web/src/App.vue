<template>
  <div id="app">
    <ul class="navigation">
      <li><router-link v-bind:to="{ name: 'home-page' }">home</router-link></li>
      <li><router-link v-bind:to="{ name: 'issue-page' }">issue</router-link></li>
      <li><router-link v-bind:to="{ name: 'login-page' }">login</router-link></li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<script>
import store from 'src/vuex/store'

export default {
  store,
  name: 'app'
}
</script>

<style>
.navigation {
  list-style-type: none;
  padding: 0;
}
.navigation li {
  display: inline-block;
  margin: 0 10px;
}
.navigation a {
  color: #42b983;
}
</style>
