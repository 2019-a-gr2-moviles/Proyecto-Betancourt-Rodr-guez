/**
 * Comida.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre_cmd: {
      type: 'string',
      minLength: 5,
      maxLength: 15,
      required: true,
    },
    precio_cmd: {
      type: 'number',
      min: 0,
      required: true
    },
    description: {
      type: 'string',
      maxLength: 100,
      allowNull: true
    },
    detalleDeComida:{  // Nombre atributo de la relación
      collection: 'detalle', // Nombre del modelo a relacionar
      via: 'id_cmd'        // Nombre del campo a hacer la relacion
    },
    id_ft: {
      model: 'foodtruck'
    },
  },

};

