import React,{useState} from "react";
import './CrudTableRow.css';

const CrudTableRowTrabajo2 = ({ el, setObservaciones, setDetalleUsuario }) => {

  let {id,trabajador, inicioTrabajo, finalTrabajo, localizacion, tipo_trabajo, observaciones} = el;



  const onChangeObservaciones = () => {

    setObservaciones(observaciones);
    setDetalleUsuario(trabajador);

}

  return (
    
   
    <tr>    
      <td>{id}</td>
      <td>{trabajador.username}</td>
      <td>{inicioTrabajo.substring(0, 10)}</td>
      <td>{inicioTrabajo.substring(11, 16)}</td>
      {!finalTrabajo && <td>Sin finalizar.</td>}
      {finalTrabajo && <td>{finalTrabajo.substring(0, 10)}</td>}
      {!finalTrabajo && <td>Sin finalizar.</td>}
      {finalTrabajo && <td>{finalTrabajo.substring(11, 16)}</td>}
      <td>{localizacion}</td>
      <td>{tipo_trabajo}</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright" onClick={() => onChangeObservaciones()}><i className="fa fa-eye"/></button>
      </td>
    </tr>
  
  );
};

export default CrudTableRowTrabajo2;