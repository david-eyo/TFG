import React from "react";
import './CrudTableRow.css';

const CrudTableRowPrecios = ({ el }) => {

  let { id, fechaIni, fechaFin, producto, precio } = el;
  console.log(el);
  if (fechaFin == null) {
    return (
      <tr>

        <td>{id}</td>
        <td>{fechaIni.substring(0, 10)}</td>
        <td>{fechaIni.substring(11, 19)}</td>
        <td></td>
        <td></td>
        <td>{producto.nombre}</td>
        <td>{precio}</td>
        <td>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        </td>
      </tr>
    );
  } else {
    return (
      <tr>
        <td>{id}</td>
        <td>{fechaIni.substring(0, 10)}</td>
        <td>{fechaIni.substring(11, 19)}</td>
        <td>{fechaFin.substring(0, 10)}</td>
        <td>{fechaFin.substring(11, 19)}</td>
        <td>{producto.nombre}</td>
        <td>{precio}</td>
        <td>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        </td>
      </tr>
    );
  };
}

export default CrudTableRowPrecios;