import React,{useState} from "react";
import './CrudTableRow.css';

const CrudTableRowItemPedido = ({ el }) => {

  let {id, producto, cantidad, precio  } = el;


  return (
    <tr>    
      <td>{id}</td>
      <td>{producto.nombre}</td>
      <td>{cantidad} kgs</td>
      <td>{precio.precio} €/kg</td>
    </tr>
  );
};

export default CrudTableRowItemPedido;