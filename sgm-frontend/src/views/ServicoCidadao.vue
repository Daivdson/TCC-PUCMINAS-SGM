<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <div class="form-group">
      <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
    </div>
    <div class="row">
      <div class="col-8 col-sm-6">
        <form name="form" @submit.prevent="consultarIptu">
          <div class="form-group">
            <label for="username">Iptu</label>
            <input v-model="inscricao" type="text" class="form-control" name="inscricao"/>
            <div class="form-group">
                <button class="btn btn-primary btn-block" >
                  <span>Consultar</span>
                </button>
            </div>
          </div>
        </form>
      </div>
      <div class="col-8 col-sm-6">
        <form name="form" @submit.prevent="consultarItr">
          <div class="form-group">
            <label for="username">Itr</label>
            <input v-model="nirt" type="text" class="form-control" name="nirt"/>
            <div class="form-group">
                <button class="btn btn-primary btn-block" >
                  <span>Consultar</span>
                </button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="row">
      <h2>Resultado ipu</h2>
      {{iptuItr}}
    </div>
  </div>
</template>

<script>
import cidadao from '../services/sgm.service';

export default {
  name: 'servico-cidadao',
  data() {
    return {
      inscricao: '',
      nirt: '',
      content: 'Serviços cidadão',
      message:'',
      iptuItr: null
    };
  },
  mounted() {
  },
  methods: {
    consultarIptu() {
      cidadao.getIptu(this.inscricao).then(
        response => {
          console.log(response)
          this.iptuItr = response;
        },
        error => {
          console.log( error.data.message )
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      });
    },
    consultarItr() {
      cidadao.getItr(this.inscricao).then(
        response => {
          console.log(response)
          this.iptuItr = response;
        },
        error => {
          console.log(error)
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      });
    }
  }
};
</script>
