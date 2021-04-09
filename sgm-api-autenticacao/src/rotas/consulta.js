
const express = require('express');
const router = express.Router();
const jwt = require('../config/jwt/jwt');
const ModeloUsuario = require('../modelo/usuario');

router.use(jwt.verify)

router.get('/usuarios', async (req, res, next) => {
    try {
        const usuario = await ModeloUsuario.find()
        res.send(usuario)
    } catch (error) {
        console.error(error)
        res.status(500).send(error)
    }
});
  
router.get('/me', async (req, res, next)  => {
    res.send(req.auth)
});

router.get('/logout', async (req, res, next)  => {
    jwt.destroy(req, res, next)
});
module.exports = router;