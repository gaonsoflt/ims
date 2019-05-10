<template>
  <div>
    <h1>ISSUES</h1>
    <div>
      프로젝트:
      <select v-model="selectedProject" v-on:change="changeProject">
        <option v-for="project in projects" v-bind:value="project.id">
          {{ project.name }}
        </option>
      </select>
      버전:
      <select v-model="selectedVersion">
        <option v-for="version in versions" v-bind:value="version.id">
          {{ version.name }}
        </option>
      </select>
      상태:
      <select v-model="selectedStatus">
        <option v-for="status in statuses" v-bind:value="status.id">
          {{ status.name }}
        </option>
      </select>
      <div v-if="isSearch">
        <form id="search">
          Search <input name="query" v-model="searchQuery">
        </form>
      </div>
      <button @click="search">조회</button>
    </div>
    <grid :options="options" :rowData="rows" :columnData="columns" />
  </div>
</template>

<script>
  import cookie from '../assets/js/cookie'
  import 'tui-grid/dist/tui-grid.css'
  import Grid from '@toast-ui/vue-grid/src/Grid.vue'
  export default {
    name: 'issue-page',
    components: {
      'grid': Grid
    },
    data () {
      return {
        isSearch: false,
        searchQuery: '',
        projects: [],
        selectedProject: '',
        versions: [],
        selectedVersion: '',
        statuses: [],
        selectedStatus: '',
        rows: [],
        options: {
          scrollX: false,
          scrollY: false,
          rowHeight: 30
        },
        columns: [
          { title: 'id',
            name: 'id',
            align: 'center',
            width: 60,
            formatter: function (value, rowData) {
              var imsId = rowData.id
              var url = 'http://ims.gaonsoft.com/issues/' + imsId
              return '<a href="' + url + '" target="_blank">' + value + '</a>'
            }
          },
          { title: '버전', name: 'fixed_version_name', align: 'center', width: 100 },
          { title: '모듈', name: 'biz_module', align: 'center', width: 80 },
          { title: '구분', name: 'tracker_name', align: 'center', width: 100 },
          { title: '상태', name: 'status_name', align: 'center', width: 80 },
          { title: '중요도', name: 'priority_name', align: 'center', width: 80 },
          { title: '제목', name: 'subject', width: 300 },
          { title: '내용', name: 'description' },
          { title: '요청자', name: 'requester_name', align: 'center', width: 100 },
          { title: '요청일자',
            name: 'created_on',
            align: 'center',
            width: 100,
            formatter: function (value) {
              return (value != null) ? new Date(value).toISOString().substring(0, 10) : value
            }
          },
          { title: '담당자', name: 'assigned_fullname', align: 'center', width: 100 },
          { title: '시작일자',
            name: 'start_date',
            align: 'center',
            width: 100,
            formatter: function (value) {
              return (value != null) ? new Date(value).toISOString().substring(0, 10) : value
            }
          },
          { title: '완료일자',
            name: 'due_date',
            align: 'center',
            width: 100,
            formatter: function (value) {
              return (value != null) ? new Date(value).toISOString().substring(0, 10) : value
            }
          },
          { title: '진행율', name: 'done_ratio', align: 'center', width: 100 }
        ]
      }
    },
    watch: {
      selectedProject: function (val) {
        this.searchCodeProjectVersions()
      }
    },
    mounted () {
      /* 페이지 로딩 시 프로젝트 목록 로드 */
      this.searchCodeProjects()
    },
    methods: {
      search: function () {
        this.$api.get('/issues', {
          headers: { // 요청 헤더
            'Authorization': cookie.getCookie('token')
          },
          params: {
            projectId: this.selectedProject,
            fixedVersionId: this.selectedVersion
          }
        }).then((response) => {
          this.rows = response.data.result
        }).catch((error) => {
          console.log(error)
          alert('조회에 실패했습니다.')
        })
      },
      searchCodeProjects: function () {
        this.$api.get('/code/projects', {
          headers: { // 요청 헤더
            'contentType': 'application/json',
            'Authorization': cookie.getCookie('token')
          }
        }).then((response) => {
          this.projects = response.data.result
          // test code
          this.selectedProject = 5
        }).catch((error) => {
          console.log(error)
          alert('프로젝트 조회에 실패했습니다.')
        })
      },
      changeProject: function () {
        this.searchCodeProjectVersions()
      },
      searchCodeProjectVersions: function () {
        this.$api.get('/code/projects/' + this.selectedProject + '/versions', {
          headers: { // 요청 헤더
            'contentType': 'application/json',
            'Authorization': cookie.getCookie('token')
          }
        }).then((response) => {
          this.versions = response.data.result
          // test code
          this.selectedVersion = 3
        }).catch((error) => {
          console.log(error)
          alert('버전 조회에 실패했습니다.')
        })
      }
    }
  }
</script>

<style lang="css" scoped>

</style>
