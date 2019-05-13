<template>
  <div>
    <h1>Counter</h1>
    <div class="counter-number">
      {{counter}}
    </div>
    <button class="counter-button" v-on:click="incrementCounter">+</button>
    <button class="counter-button" v-on:click="decrementCounter">-</button>
  </div>
</template>

<script>
export default {
  name: 'counter-page',
  computed: {
    counter () {
      return this.$store.getters.mainCounter
    }
  },
  methods: {
    incrementCounter () {
      this.$store.dispatch('incrementMain')
    },
    decrementCounter () {
      this.$store.dispatch('decrementMain')
    }
  }
}
</script>

<style lang="css" scoped>
.counter-number {
  font-size: 40px;
}
</style>
