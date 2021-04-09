const express = require('express');
const router = express.Router();
const jwt = require('../config/jwt/jwt');
const ModeloUsuario = require('../modelo/usuario');

router.post('/', async (req, res) => {
    try {
      const result = await ModeloUsuario.create(req.body)
      const { senha, ...usuario } = result.toObject()
  
      const token = jwt.sign({ user: usuario.id })
  
      res.send({ usuario, token })
    } catch (error) {
        console.error(error)
        res.status(400).send(error)
    }
});
  
router.get('/', async (req, res) => {
    if(!req.headers.authorization && req.headers.authorization == undefined)
        res.send(400)
    
    const [, hash] = req.headers.authorization.split(' ')
    const [email, senha] = Buffer.from(hash, 'base64')
        .toString()
        .split(':')
    try {
        const user = await ModeloUsuario.findOne({ email, senha })

        if (!user) {
            res.status(401).send()
        }
        const token = jwt.sign({ user: user.id })
        console.log('usuario autenticou')
        res.send({ user, token })
    } catch (error) {
        console.error(error)
        res.status(500).send(error)
    }
});

module.exports = router;