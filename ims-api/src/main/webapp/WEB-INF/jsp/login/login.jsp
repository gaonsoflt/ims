<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>IMS</title>
<!-- 도움되는 콘솔 경고를 포함한 개발 버전 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<!-- 상용버전, 속도와 용량이 최적화됨. -->
<!-- <script src="https://cdn.jsdelivr.net/npm/vue"></script> -->
<!-- axios -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="/resources/js/SHA1.js"></script>
<script type="text/javascript" src="/resources/js/cookie.js"></script>
</head>
<body>
	<div id="app">
		<h2>Login</h2>
		<form @submit.prevent="onSubmit(id, password)">
			<input type="text" v-model="id" placeholder="ID">
			<input type="password" v-model="password" placeholder="Password">
			<input type="submit" value="Login">
		</form>
		<p><i></i></p>
	</div>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				id: '',
				password: ''
			},
			beforeCreate : function() {
				var token = getCookie("token");
				console.log("token: " + token);
				if(token != "") {
					// 로그인 필요
					//location.href="/web/main";
				}
			},
			methods : {
				onSubmit: function(id, password) {
					axios.post('/auth/signin', {
						"username": id,
						"password": SHA1(password)
					}).then(function(response) {
						setCookie("token", "Bearer " + response.data.token, 1);
						location.href="/web/main";
					}).catch(function(error) {
						alert("로그인에 실패했습니다.");
					});
				}
			}
		})
	</script>
</body>
</html>