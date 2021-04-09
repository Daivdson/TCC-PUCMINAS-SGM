import axios from 'axios';
import profile from '../profile/profile-dev.json';

const API_URL = profile.urlApiSgm;

class Cidadao {
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

export default new Cidadao();
