/**
 * Foodtruck.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre_ft: {
      type: 'string',
      minLength: 3,
      maxLength: 10,
      required: true
    },
    latitud_ft: {
      type: 'string',
      minLength: 1,
      allowNull: true
    },
    longitud_ft: {
      type: 'string',
      minLength: 1,
      allowNull: true,
    },
    usuario_ft: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 5,
      maxLength: 10,
    },
    contrasenia_ft: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 7,
      maxLength: 10,
      encrypt: true
    },
    id_prs: {         // Nombre del fk para la relación
      model: 'persona',   // Nombre del modelo a relacionar (padre) 
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    }
  },
};

