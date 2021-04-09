import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/perfil',
      name: 'perfil',
      // lazy-loaded
      component: () => import('./views/Perfil.vue')
    },
    {
      path: '/protocolos',
      name: 'protocolos',
      // lazy-loaded
      component: () => import('./views/Protocolos.vue')
    },
    {
      path: '/servico-cidadao',
      name: 'servicoCidadao',
      // lazy-loaded
      component: () => import('./views/ServicoCidadao.vue')
    }
  ]
});

router.beforeEach((to, from, next) => {
  let user = JSON.parse(localStorage.getItem('user'));
  const rotasPublicas = ['/','/login', '/servico-cidadao', '/home'];
  const rotaSolicitada = to.path;
  const isAutenticacao = !rotasPublicas.includes(rotaSolicitada);

  if(isAutenticacao && !user) {
    next('/login');
  }
    next();
});
