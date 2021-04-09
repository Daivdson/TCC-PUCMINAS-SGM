const jwt = require('jsonwebtoken') 
const profile = require('../profile/profiles').profile();
const ModeloUsuario = require('../../modelo/usuario');

const env_secret = process.env.SECRET_JWT;
const secret = !env_secret ? profile.secretJWT : env_secret;

const sign = dadosUsuarioParaToken => jwt.sign(dadosUsuarioParaToken, secret, { expiresIn: '1h' })

const destroy = async (req, res, next) => {
  let paramAuthorization = req.headers.authorization;

  if(!paramAuthorization)
    res.status(401).send({erro: 'Token invalido'})

  const [, token] = paramAuthorization.split(' ')

  try {
      res.status(202).send('Implementacao falatando');
  } catch (error) {
    console.error(error)
    res.status(500).send(error)
  }
}

const verify = async (req, res, next) => {

  let paramAuthorization = req.headers.authorization;

  if(!paramAuthorization)
    res.status(401).send({erro: 'Token invalido'})

  const [, token] = paramAuthorization.split(' ')
  try {
    const dadosToken = await jwt.verify(token, secret, (error, dados) => {
      if(error)
        res.status(401).send({erro: 'Token invalido'})
      return dados
    })
    
    const usuario = await ModeloUsuario.findById(dadosToken.user)

    if (!usuario) {
      return res.send(401).send({erro: 'Usuário não encontrado'})
    }

    req.auth = usuario

    next()
  } catch (error) {
    console.error(error)
    res.status(500).send(error)
  }
}

module.exports = {
    sign,
    verify,
    destroy
};
