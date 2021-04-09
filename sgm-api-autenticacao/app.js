require('./src/config/db/mongoose')
const express = require('express');
const cors = require('cors');
const cadastro = require('./src/rotas/cadastro');
const consulta = require('./src/rotas/consulta');
const profile = require('./src/config/profile/profiles').profile();

const app = express();
app.use(cors());
//app.options('*', cors())
app.use(express.json());
app.use(profile.baseUrl, cadastro);
app.use(profile.baseUrl, consulta);

app.listen(profile.porta, () => {
  console.log('Servidor rodando na porta'+profile.porta)
})
