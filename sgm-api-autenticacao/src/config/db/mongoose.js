const mongoose = require('mongoose');
const profile = require('../profile/profiles').profile();

const env_url_senha = process.env.MONGODB_SENHA;
const urlConexao = !env_url_senha ? profile.conexaoBanco.urlConexao : env_url_senha;

mongoose.connect(urlConexao,{
    useNewUrlParser: true,
  }
)

mongoose.connection.on('error', () => console.error('Erro de conexão com o banco de dados:'))
mongoose.connection.once('open', () => console.log('Banco de dados conectado'))
