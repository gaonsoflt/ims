<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Issues</title>
<!-- component template -->
<script type="text/x-template" id="grid-template">
  <table>
    <thead>
      <tr>
        <th v-for="key in columnsLabel"
          @click="sortBy(key)"
          :class="{ active: sortKey == key }">
          {{ key | capitalize }}
          <span class="arrow" :class="sortOrders[key] > 0 ? 'asc' : 'dsc'">
          </span>
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="entry in filteredData">
        <td v-for="key in columns">
          {{entry[key]}}
        </td>
      </tr>
    </tbody>
  </table>
</script>
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
	<div v-if="isSearch">
		<form id="search">
			Search <input name="query" v-model="searchQuery">
		</form>
	</div>
	<grid :data="gridData" :columns="gridColumns" :columns-label="gridColumnsLabel" :filter-key="searchQuery"></grid>
</div>

<script>
	// register the grid component
	Vue.component('grid', {
		template : '#grid-template',
		props : {
			data : Array,
			columns : Array,
			columnsLabel : Array,
			filterKey : String
		},
		data : function() {
			var sortOrders = {};
			this.columns.forEach(function(key) {
				sortOrders[key] = 1;
			});
			return {
				sortKey : '',
				sortOrders : sortOrders
			};
		},
		computed : {
			filteredData : function() {
				var sortKey = this.sortKey;
				var filterKey = this.filterKey && this.filterKey.toLowerCase();
				var order = this.sortOrders[sortKey] || 1;
				var data = this.data;
				if (filterKey) {
					data = data.filter(function(row) {
						return Object.keys(row).some(
								function(key) {
									return String(row[key]).toLowerCase().indexOf(filterKey) > -1;
								});
					});
				}
				if (sortKey) {
					data = data.slice().sort(function(a, b) {
						a = a[sortKey];
						b = b[sortKey];
						return (a === b ? 0 : a > b ? 1 : -1) * order;
					});
				}
				return data;
			}
		},
		filters : {
			capitalize : function(str) {
				return str.charAt(0).toUpperCase() + str.slice(1);
			}
		},
		methods : {
			sortBy : function(key) {
				this.sortKey = key;
				this.sortOrders[key] = this.sortOrders[key] * -1;
			}
		}
	})

	// bootstrap the demo
	var app = new Vue({
		el : '#app',
		data : {
			isSearch : false,
			searchQuery : '',
			gridColumns : ['id', 'fixed_version_name', 'tracker_name', 'status_name', 'priority_name', 'subject', 'description', 'start_date', 'due_date', 'done_ratio'],
			gridColumnsLabel : ['id', '버전', '업무구분', '상태', '중요도', '제목', '내용', '시작일자', '완료일자', '진행율'],
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