import axios from 'axios';
import profile from '../profile/profile-dev.json';

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

  logout() {
    localStorage.removeItem('user');
  }
}

export default new AuthService();
