import React from "react";
import './CrudTableRow.css';

const CrudTableRow2 = ({ el, setDataToEdit }) => {
  let {id, nombre, precio, nuestros_productos, oferta, valoracion, numero_valoraciones } = el;
  
  return (
    <tr>
      
      <td>{id}</td>
      <td>{nombre}</td>
      <td>{precio}</td>
      <td>{nuestros_productos.toString()}</td>
      <td>{oferta.toString()}</td>
      <td>{valoracion}</td>
      <td>{numero_valoraciones}</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => setDataToEdit(el)}><i className="fa fa-pencil"/></button>
      </td>
    </tr>
  );
};

export default CrudTableRow2;