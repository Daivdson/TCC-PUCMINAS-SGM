import axios from 'axios';
import profile from '../profile/profile-dev.json';
import authHeader from './auth-header';

const API_URL = profile.urlApiSgm;

class Sgm {
    criarProcesso(processo) {
        return axios.request({
            url: API_URL + '/processo',
            method: 'post',
            headers: authHeader,
            data: processo
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    alterarProcesso(processo) {
        console.log(authHeader)
        console.log(processo)
        return axios.put(API_URL + '/processo/',{
            headers: authHeader,
            data: processo
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    aprovarProcesso(numProcesso) {
        return axios.request({
            url: API_URL + '/processo/' + numProcesso + 'aprovar',
            method: 'patch',
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
