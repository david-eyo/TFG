import React from "react";
import './CrudTableRow.css';

const CrudTableRowCarrito = ({ el, setDataToEdit, deleteData }) => {

  let {id, productos, cantidad  } = el;
  let productId = productos.id;
  let datosEditados={id,productId, cantidad};
  return (
    <tr>    
      <td>{id}</td>
      <td>{productos.nombre}</td>
      <td>{cantidad}</td>
      <td>{productos.precio}</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => setDataToEdit(datosEditados)}><i className="fa fa-pencil"/></button>
        <button className="btn btn-outline-danger" onClick={() => deleteData(id)}><i className="fa fa-trash"/></button>
      </td>
    </tr>
  );
};

export default CrudTableRowCarrito;