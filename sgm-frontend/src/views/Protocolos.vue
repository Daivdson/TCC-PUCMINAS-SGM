<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <div class="row">
      <div class="col-sm-12">
        <a class="btn btn-secondary btn-sm mb-3" href="/protocolo/">Novo protocolo</a>
        <table class="table  table-action">
          <thead>
            <tr>
              <th scope="col">Serviço</th>
              <th scope="col">Responsavel</th>
              <th scope="col">Solicitante</th>
              <th scope="col">Data criação</th>
              <th scope="col">Situação</th>
              <th scope="col">Aprovar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in resultadoProcesso" :key="item.id">
              <td><a v-bind:href="/protocolo/+item.id">{{item.tipoServico}}</a></td>
              <td>{{item.responsavel}}</td>
              <td>{{item.solicitante}}</td>
              <td>{{item.dataCriacao}}</td>
              <td>{{item.status}}</td>
              <td>
                <button type="button" v-if="!isAdmin || item.status == 'aprovado'" disabled class="btn btn-secondary btn-sm">Aprovar</button>
                <button type="button" v-if="isAdmin && item.status != 'aprovado'" @click="aprovarProcesso(item.id)" class="btn btn-success btn-sm">Aprovar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import processo from '../services/processo.service';

export default {
  name: 'protocolos',
  data() {
    return {
      content: 'Protocolos cidadãos',
      resultadoProcesso: null
    };
  },
  computed: {
    usuario() {
      return this.$store.state.auth.user;
    },
    isAdmin() {
      let roleAdmin = 'role_admin'
      let roleDoUsuario = this.usuario.user.roles
      if(roleAdmin == roleDoUsuario) {
        return true
      } else {
        return false
      }
      
    }
  },
  mounted() {
    this.$store.dispatch('auth/verificar');
  },
  methods: {
    aprovarProcesso(idProcesso) {
      processo.aprovarProcesso(idProcesso).then(
        response => {
          this.resultadoProcesso = response;
          this.carregaProcessos()
        },
        error => {
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    carregaProcessos() {
      processo.todosProcesso().then(
        response => {
          this.resultadoProcesso = response;
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
    this.carregaProcessos()
  }
};
</script>
<style scoped>
@media (max-width: 480px) {
  .table-action thead {
    display: none;
  }
  .table-action tr {
    border-bottom: 1px solid #dddddd;
  }
  .table-action td {
    border: 0;
  }
  .table-action td:not(:first-child) {
    display: block;
  }
}
</style>