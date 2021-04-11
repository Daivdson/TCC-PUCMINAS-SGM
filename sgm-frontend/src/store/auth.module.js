import AuthService from '../services/auth.service';
import { router } from '../router';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, user) {
      AuthService.login(user).then(
        user => {
          if(!user.error){
            commit('loginSuccess', user);
            router.push('/');
          } else {
            commit('loginFailure', user);
          }
        },
        error => {
          commit('loginFailure', error);
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    },
    verificar({commit}) {
      AuthService.verificar().then(
        res => {
          if(res.error){
            //commit('logout');
          }
        },
        () => {
          //commit('logout');
        }
      );
    },
  },
  mutations: {
    loginSuccess(state, user) {
      state.status = { loggedIn: true };
      state.user = user;
    },
    loginFailure(state) {
      state.status = {};
      state.user = null;
    },
    logout(state) {
      state.status = {};
      state.user = null;
    }
  }
};
