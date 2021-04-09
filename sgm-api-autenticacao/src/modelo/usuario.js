const mongoose = require('mongoose');
const crypto = require('crypto');

const roles = ['role_admin', 'role_moderador']

const isRoleValida = (valor) => {
  let roleValida = false
  roles.filter(v => {
    if(v == valor){
      roleValida = true
    }
  })
  return roleValida
}

const usuario = new mongoose.Schema(
  {
    nome: {
      type: String,
      required: true,
    },

    email: {
      type: String,
      required: true,
      unique: true,
      lowercase: true,
      trim: true,
    },

    roles: {
      type: String,
      required: true,
      lowercase: true,
      trim: true,
      validate(value){
        if(!isRoleValida(value))
            throw new Error('Role informada não é valida');
    }
    },

    senha: {
      type: String,
      required: true,
      select: false,
      set: value =>
        crypto
          .createHash('md5')
          .update(value)
          .digest('hex'),
    },
  },
  {
    timestamps: true,
    toJSON: { virtuals: true, getters: true },
    toObject: { virtuals: true, getters: true },
  }
)
const ModeloUsuario = mongoose.model('Usuario', usuario);

module.exports = ModeloUsuario;
