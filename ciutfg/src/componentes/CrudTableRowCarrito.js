import React,{useState} from "react";
import './CrudTableRow.css';

const CrudTableRowCarrito = ({ el, setDataToEdit, deleteData, setFormulario }) => {

  let {id, productos, cantidad  } = el;
  let productId = productos.id;
  let datosEditados={id,productId, cantidad};

  const handleChange = (datosEditados) => {

    setDataToEdit(datosEditados);
    setFormulario(true);

  }

  return (
    <tr>    
      <td>{id}</td>
      <td>{productos.nombre}</td>
      <td>{cantidad} kgs</td>
      <td>{productos.precio} €/kg</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => handleChange(datosEditados)}><i className="fa fa-pencil"/></button>
        <button className="btn btn-outline-danger" onClick={() => deleteData(id)}><i className="fa fa-trash"/></button>
      </td>
    </tr>
  );
};

export default CrudTableRowCarrito;