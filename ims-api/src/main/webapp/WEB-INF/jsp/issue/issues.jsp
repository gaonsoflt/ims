<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Issues</title>
<script type="text/javascript" src="/resources/js/component/app.js"></script>
<script type="text/javascript" src="/resources/js/component/manifest.js"></script>
<script type="text/javascript" src="/resources/js/component/vendor.js"></script>
</head>
<body>
<div id="app">
	<div>
		프로젝트: <select v-model="selectedProject" v-on:change="changeProject">
			<option v-for="project in projects" v-bind:value="project.id">
				{{ project.name }}
			</option>
		</select>
		버전: <select v-model="selectedVersion">
			<option v-for="version in versions" v-bind:value="version.id">
				{{ version.name }}
			</option>
		</select>
		<button v-on:click="search">조회</button>
	</div>
	<grid :rowData="gridData" :columnData="gridColumns"></grid>
</div>

<script>
	var app = new Vue({
		el : '#app',
		data : {
			isSearch : false,
			searchQuery : '',
			gridColumns : [
				{ title: 'id', name: 'id' }, 
				{ title: 'fixed_version_name', name: '버전' },
				{ title: 'tracker_name', name: '업무구분' },
				{ title: 'status_name', name: '상태' },
				{ title: 'priority_name', name: '중요도' },
				{ title: 'subject', name: '제목' },
				{ title: 'description', name: '내용' },
				{ title: 'start_date', name: '시작일' },
				{ title: 'due_date', name: '종료일' },
				{ title: 'done_ratio', name: '진척도' },
			],
			gridData : [],
			projects : [],
			selectedProject : '',
			versions : [],
			selectedVersion : '',
		},
		methods : {
			search : function() {
				axios.get("/api/issues", {
					headers: { // 요청 헤더
						'Authorization': getCookie("token")
					},
					params: {
						projectId: this.selectedProject,
						fixedVersionId: this.selectedVersion
					}
				}).then((response) => {
					this.gridData = response.data.result;					
				}).catch((error) => {
					console.log(error);
					alert("조회에 실패했습니다.");
				});
			},
			searchCodeProjects: function() {
				axios.get("/api/code/projects", {
					headers: { // 요청 헤더
						'Authorization': getCookie("token")
					}
				}).then((response) => {
					this.projects = response.data.result;
				}).catch((error) => {
					console.log(error);
					alert("프로젝트 조회에 실패했습니다.");
				});
			},
			changeProject: function () {
				this.searchCodeProjectVersions();
			},
			searchCodeProjectVersions: function() {
				axios.get("/api/code/projects/" + this.selectedProject + "/versions", {
					headers: { // 요청 헤더
						'Authorization': getCookie("token")
					}
				}).then((response) => {
					this.versions = response.data.result;
				}).catch((error) => {
					console.log(error);
					alert("버전 조회에 실패했습니다.");
				});
			}
		},
		mounted : function() {
			this.searchCodeProjects();
			//this.search();
		}
	})
</script>
<style type="text/css">
	body {
		font-family: Helvetica Neue, Arial, sans-serif;
		font-size: 12px;
		color: #444;
	}
	
	table {
		border: 2px solid #42b983;
		border-radius: 3px;
		background-color: #fff;
		table-layout: auto;
	    width: 100%;
	}
	
	th {
		background-color: #42b983;
		color: rgba(255, 255, 255, 0.66);
		cursor: pointer;
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	
	td {
		background-color: #f9f9f9;
	}
	
	th, td {
		padding: 10px 20px;
	}
	
	th.active {
		color: #fff;
	}
	
	th.active .arrow {
		opacity: 1;
	}
	
	.arrow {
		display: inline-block;
		vertical-align: middle;
		width: 0;
		height: 0;
		margin-left: 5px;
		opacity: 0.66;
	}
	
	.arrow.asc {
		border-left: 4px solid transparent;
		border-right: 4px solid transparent;
		border-bottom: 4px solid #fff;
	}
	
	.arrow.dsc {
		border-left: 4px solid transparent;
		border-right: 4px solid transparent;
		border-top: 4px solid #fff;
	}
</style>
</body>
</html>