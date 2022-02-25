import React from "react";
import './CrudTableRow.css';

const CrudTableRowPrecios = ({ el }) => {
  
  let {id, fechaIni, fechaFin, nombreProducto, precio } = el;
  console.log(el);

  return (
    <tr>
      
      <td>{id}</td>
      <td>{fechaIni}</td>
      <td>{fechaFin}</td>
      <td>{nombreProducto}</td>
      <td>{precio}</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
      </td>
    </tr>
  );
};

export default CrudTableRowPrecios;