import React from "react";
import './CrudTableRow.css';

const CrudTableRowCarrito = ({ el, setDataToEdit, deleteUsuario }) => {

  let {id, username, nombre, apellidos, email, ciudad, cp, direccion, fechaNacimiento, tlf} = el;
  let datosEditados={id, username, nombre, apellidos, email, ciudad, cp, direccion, fechaNacimiento, tlf};
  return (
    <tr>    
                        <td>{id}</td>
                        <td>{username}</td>
                        <td>{nombre}</td>
                        <td>{apellidos}</td>
                        <td>{email}</td>
                        <td>{ciudad}</td>
                        <td>{cp}</td>
                        <td>{direccion}</td>
                        <td>{fechaNacimiento.substring(0, 10)}</td>
                        <td>{tlf}</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => setDataToEdit(datosEditados)}><i className="fa fa-pencil"/></button>
        <button className="btn btn-outline-danger" onClick={() => deleteUsuario(id)}><i className="fa fa-trash"/></button>
      </td>
    </tr>
  );
};

export default CrudTableRowCarrito;