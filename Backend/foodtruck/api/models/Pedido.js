/**
 * Pedido.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    fecha_pd: {
      type: 'string',
      maxLength: 10,
      required: true
    },
    monto_total: {
      type: 'number',
      min: 0,
    },
    tarjeta_ped: {
      type: 'string',
      maxLength: 16,
      required: true
    },
    id_cl: {
      model: 'cliente'
    },
    detalleDePedido:{  // Nombre atributo de la relación
      collection: 'detalle', // Nombre del modelo a relacionar
      via: 'id_pd'        // Nombre del campo a hacer la relacion
    },
  },

};

