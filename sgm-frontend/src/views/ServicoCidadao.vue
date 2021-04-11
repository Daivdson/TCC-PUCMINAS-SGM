<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <transition name="fade">
      <div class="form-group">
        <div v-show="alerta" class="alert alert-danger" role="alert">{{alerta}}</div>
      </div>
    </transition>
    <div class="row">
      <div class="col-sm-6 mb-3">
          <div class="borda">
            <form name="form" @submit.prevent="consultarIptu">
                <div class="form-group">
                  <label for="username">Iptu</label>
                  <input v-model="inscricao" type="text" class="form-control" name="inscricao"/>
                  <div class="form-group mt-2 ">
                      <button class="btn btn-secondary " >
                        <span>Consultar</span>
                      </button>
                  </div>
                </div>
            </form>
          </div>
      </div>
      <div class="col-sm-6 mb-3">
        <div class="borda">
          <form name="form" @submit.prevent="consultarItr">
            <div class="form-group">
              <label for="username">Itr</label>
              <input v-model="nirt" type="text" class="form-control" name="nirt"/>
              <div class="form-group mt-2 ">
                  <button class="btn  btn-secondary" >
                    <span>Consultar</span>
                  </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div v-if="iptu" class="col">
        <div class="row">
          <h3>IPTU</h3>
        </div>
        <div class="row">
          <dl class="row">
            <dt class="col-sm-2">Inscrição: </dt>
            <dd class="col-sm-9">{{iptu.inscricao}}</dd>

            <dt class="col-sm-2">NomeRazaoSocial: </dt>
            <dd class="col-sm-9">{{iptu.nomeRazaoSocial}}</dd>

            <dt class="col-sm-2">Endereço: </dt>
            <dd class="col-sm-9">{{iptu.endereco}}</dd>
          </dl>
        </div>
        <div class="row">
          <h3>Debitos</h3>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Ano</th>
                <th scope="col">Valor</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in iptu.debitos" :key="item.id">
                <td>{{item.ano}}</td>
                <td>{{item.valor}}</td>
                <td><button type="button" class="btn btn-secondary btn-sm">Visualizar debito</button></td>
              </tr>
            </tbody>
          </table>
        </div>
    </div>

    <div v-if="itr" class="col">
        <div class="row">
          <h3>ITR</h3>
        </div>
        <div class="row">
          <dl class="row">
            <dt class="col-sm-2">Nirf: </dt>
            <dd class="col-sm-9">{{itr.nirf}}</dd>

            <dt class="col-sm-2">NomeRazaoSocial: </dt>
            <dd class="col-sm-9">&nbsp; {{itr.nomeRazaoSocial}}</dd>

            <dt class="col-sm-2">Endereço: </dt>
            <dd class="col-sm-9">{{itr.endereco}}</dd>
          </dl>
        </div>
        <div class="row">
          <h3>Debitos</h3>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Ano</th>
                <th scope="col">Valor</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in itr.debitos" :key="item.id">
                <td>{{item.ano}}</td>
                <td>{{item.valor}}</td>
                <td><button type="button" class="btn btn-secondary btn-sm">Visualizar debito</button></td>
              </tr>
            </tbody>
          </table>
        </div>
    </div>
  </div>
</template>

<script>
import cidadao from '../services/cidadao.service';

export default {
  name: 'servico-cidadao',
  data() {
    return {
      inscricao: '',
      nirt: '',
      content: 'Serviços cidadão',
      alerta:'',
      iptu: null,
      itr: null
    };
  },
  mounted() {
  },
  methods: {
    limparVariaveis(){
      this.iptu = ''
      this.itr = ''
      this.alert = ''
    },
    limparCamposFormulario(){
      this.inscricao = ''
      this.nirt = ''
    },
    consultarIptu() {
      this.limparVariaveis()
      cidadao.getIptu(this.inscricao).then(
        response => {
          if(!response.error ){
            this.iptu = response;
            this.alerta = ''
          } else {
            this.alerta = 'Não conseguimos encontrar Iptu com essa inscrição'
          }
          this.limparCamposFormulario()
        },
        error => {
          this.alerta = 'Não conseguimos encontrar Iptu com essa inscrição'
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      });
    },
    consultarItr() {
      this.limparVariaveis()
      cidadao.getItr(this.nirt).then(
        response => {
          if(!response.error ){
            this.itr = response;
            this.alerta = ''
          } else {
            this.alerta = 'Não conseguimos encontrar Itr com essa inscrição'
          }
          this.limparCamposFormulario()
        },
        error => {
          this.alerta = 'Não conseguimos encontrar Itr com essa inscrição'
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      });
    }
  }
};
</script>
