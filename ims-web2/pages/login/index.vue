<template>
	<div>
		<h2>Login</h2>
		<form @submit.prevent="onSubmit(id, password)">
			<input type="text" v-model="id" placeholder="ID">
			<input type="password" v-model="password" placeholder="Password">
			<input type="submit" value="Login">
		</form>
		<p><i></i></p>
	</div>
</template>

<script>
  import cookie from '../../assets/js/cookie'
  import SHA1 from '../../assets/js/SHA1'
  export default {
    name: 'login-page',
    data () {
      return {
        id: '',
        password: ''
      }
    },
    methods: {
      onSubmit: function (id, password) {
        this.$axios.$post('/auth/signin', {
          'username': id,
          'password': SHA1(password)
        }).then((response) => {
          var t = response.token
          console.log(t)
          cookie.setCookie('token', 'Bearer ' + t, 1)
          this.$router.push('issue')
        }).catch((error) => {
          console.log('login failed: ' + error)
        })
      }
    }
  }
</script>

<style lang="css" scoped>
</style>
