import axios from 'axios';
import profile from '../profile/profile-dev.json';
import authHeader from './auth-header';

const API_URL = profile.urlApiSgm+'/tes';

class Sgm {
    criarProcesso(processoForm) {
        return axios.get(API_URL + '/processo/n/',{
            headers: authHeader,
            params: processoForm
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    alterarProcesso(processo) {
        return axios.get(API_URL + '/processo/a/',{
            headers: authHeader,
            params: processo
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    aprovarProcesso(numProcesso) {
        return axios.get(API_URL + '/processo/' + numProcesso + '/aprovar',{
            headers: authHeader
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    pesquisarProcesso(numProcesso) {
        return axios.request({
            url: API_URL + '/processo/' + numProcesso,
            method: 'get',
            headers: authHeader
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    todosProcesso() {
        return axios.get(API_URL + '/processo/',{
            headers: authHeader
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }
}

export default new Sgm();
