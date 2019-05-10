import HomePage from 'src/pages/HomePage'
import IssuePage from 'src/pages/IssuePage'
import LoginPage from 'src/pages/LoginPage'

export default [
  {
    path: '/',
    name: 'home-page',
    component: HomePage
  },
  {
    path: '/issues',
    name: 'issue-page',
    component: IssuePage
  },
  {
    path: '/login',
    name: 'login-page',
    component: LoginPage
  },
  {
    path: '*',
    redirect: '/'
  }
]
