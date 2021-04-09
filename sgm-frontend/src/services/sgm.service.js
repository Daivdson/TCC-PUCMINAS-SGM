import axios from 'axios';
import profile from '../profile/profile-dev.json';
import authHeader from './auth-header';

const API_URL = profile.urlApiSgm;

class Sgm {
    criarProcesso(processo) {
        return axios.request({
            url: API_URL + 'processo',
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
        return axios.request({
            url: API_URL + 'processo/' + processo.id,
            method: 'put',
            headers: authHeader,
            data: processo
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    processo(numProcesso) {
        return axios.request({
            url: API_URL + 'processo/' + numProcesso,
            method: 'put',
            headers: authHeader
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    todosProcesso() {
        return axios.request({
            url: API_URL + 'processo/',
            method: 'get',
            headers: authHeader
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }

    getItr(nirt) {
        return axios.get(API_URL + '/cidadao/itr',{
            params: {
                nirt: nirt
            }
          })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }
    
    getIptu(inscricao) {
        return axios.get(API_URL + '/cidadao/iptu', {
            params: {
                inscricao : inscricao
            }
        })
        .then(response => {
            return response.data;
        }).catch(error => {
            return {error: error}
        })
    }
}

export default new Sgm();
