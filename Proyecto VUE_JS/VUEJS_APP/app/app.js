import Vue from 'nativescript-vue';
import LoginPage from './components/Login.vue';  // Importa el componente de Login
import HomePage from './components/HomePage.vue';  // Importa el componente Home (la página que quieres mostrar después del login)

new Vue({
  render: (h) => h('frame', [h(LoginPage)])  // Carga el LoginPage primero
}).$start();
