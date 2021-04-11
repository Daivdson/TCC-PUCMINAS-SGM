import axios from 'axios';
import profile from '../profile/profile-dev.json';
import authHeader from './auth-header';

const API_URL = profile.urlAutenticacaoSgm;

class AuthService {
  login(user) {
    return axios
      .get(API_URL + '', {
        auth: {
          username: user.username,
          password: user.password
        }
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      }).catch(error => {
        return {error: error}
      })
  }

  verificar() {
    return axios
      .get(API_URL + '/me', {
        headers: authHeader
      })
      .then(response => {
        if (response.data) {
          return true
        }

        return {error: 'token invalido'}
      }).catch(error => {
        return {error: error}
      })
  }

  logout() {
    localStorage.removeItem('user');
  }
}

export default new AuthService();
