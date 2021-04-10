<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <div class="row">
      <div class="col-sm-8">
        <p v-show="alteracao">
          id: {{idProcesso}} - Situacao: {{situacao}}
        </p>
        <form name="form" @submit.prevent="alteraOuCriaProcesso">
          <div class="mb-3">
            <label for="" class="form-label">Responsavel</label>
            <input type="text" v-model="responsavel" disabled="disabled" class="form-control" >
          </div>
          <div class="mb-3">
            <label for="" class="form-label">Solicitante</label>
            <input type="text" v-model="solicitante" class="form-control" >
          </div>
          <div class="mb-3">
            <label for="" class="form-label">Telefone</label>
            <input type="text" v-model="telefone" class="form-control" >
          </div>
          <div class="mb-3">
            {{tipoServico}}
            <label for="" class="form-label">Tipo de serviço:</label>
            <v-select  v-model="selected" :options="options" />
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import processo from '../services/processo.service';

export default {
  name: 'FomularioProtocolo',
  data() {
    return {
      content: 'Protocolos cidadãos',
      analise: '',
      responsavel: '',
      solicitante: '',
      telefone: '',
      tipoServico: '',
      situacao:'',
      dataCriacao:'',
      alteracao: false,
      idProcesso: '',
      selected:'',
      options: [
        'Emissão CND',
        'Emissao ISS',
        'Limpesa urbana',
        'Alvara'
      ]
    };
  },
   computed: {
    usuario() {
      return this.$store.state.auth.user;
    }
  },
   methods: {
    isAlterar() {
      if(this.$route.params.idProcesso) {
        this.alteracao = true
        this.idProcesso = this.$route.params.idProcesso
      }
    },
    alteraOuCriaProcesso(){
      if(this.alteracao) {
        this.alterarProcesso()
      } else {
        this.criarProcesso()
      }
    },
    modeloProcesso(){
      return {
        id: this.idProcesso,
        status: this.status,
        responsavel: this.responsavel,
        solicitante: this.solicitante,
        telefone: this.telefone,
        tipoServico: this.selected
      }
    },
    alterarProcesso() {
      let processoModificado = this.modeloProcesso()
      processo.alterarProcesso(processoModificado).then(
        response => {
          console.log(response)
          if(!response.error){
            console.log('alterou: ' + response)
          }
        },
        error => {
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    criarProcesso() {
      let processoCriado= this.modeloProcesso()
      processo.criarProcesso(processoCriado).then(
        response => {
          if(!response.error){
            console.log('crirou: ' + response)
          }
        },
        error => {
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    carregarProcesso () {
      this.responsavel = this.usuario.user.nome
      processo.pesquisarProcesso(this.idProcesso).then(
        response => {
          this.resultadoProcesso = response;
          this.analise = response.analise
          this.solicitante = response.solicitante,
          this.telefone = response.telefone,
          this.selected = response.tipoServico
          this.situacao = response.status
          this.dataCriacao = response.dataCriacao
        },
        error => {
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    }
  },
  created() {
    this.isAlterar()
   this.carregarProcesso();
  }
};
</script>
