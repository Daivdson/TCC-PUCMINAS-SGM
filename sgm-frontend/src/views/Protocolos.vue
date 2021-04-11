<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <div class="row">
      <div class="col">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Serviço</th>
              <th scope="col">Responsavel</th>
              <th scope="col">Solicitante</th>
              <th scope="col">Data criação</th>
              <th scope="col">Situação</th>
              <th scope="col">aprovar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in resultadoProcesso" :key="item.id">
              <td><a v-bind:href="/protocolo/+item.id">{{item.tipoServico}}</a></td>
              <td>{{item.responsavel}}</td>
              <td>{{item.solicitante}}</td>
              <td>{{item.dataCriacao}}</td>
              <td>{{item.status}}</td>
              <td><button>Aprovar</button></td>
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
  mounted() {
    
  },
  created() {
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
};
</script>
