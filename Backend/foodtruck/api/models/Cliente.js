/**
 * Cliente.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    usuario_cl: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 5,
      maxLength: 10,
    },
    contrasenia_cl: {
      type: 'string',
      required: true,
      minLength: 7,
      maxLength: 10,
      encrypt: true
    },
    latitud_cl: {
      type: 'string',
      minLength: 1,
      allowNull: true
    },
    longitud_cl: {
      type: 'string',
      minLength: 1,
      allowNull: true,
    },
    id_prs: {         // Nombre del fk para la relación
      model: 'persona',   // Nombre del modelo a relacionar (padre) 
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    },
    pedidoCliente:{  // Nombre atributo de la relación
      collection: 'pedido', // Nombre del modelo a relacionar
      via: 'id_cl'        // Nombre del campo a hacer la relacion
    },
  },
};

