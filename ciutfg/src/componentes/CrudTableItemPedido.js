import {React, useState} from "react";
import CrudTableRowItemPedido from "./CrudTableRowItemPedido";



const CrudTableItemPedido = ({ data }) => {

    
  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRowItemPedido
                el={el}
              />
            ))
          ) : (
            <tr>
              <td colSpan="3">Sin artículos en el carrito de la compra</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTableItemPedido;