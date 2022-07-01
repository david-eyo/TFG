import React,{useState, useEffect} from "react";
import './CrudTableRow.css';
import {useTranslation} from "react-i18next";

const CrudTableRowTrabajo2 = ({ el, setObservaciones, setDetalleUsuario, minutos, setMinutos, minis, setMinis }) => {

  let {id,trabajador, inicioTrabajo, finalTrabajo, localizacion, tipo_trabajo, observaciones} = el;

  const [pasado, setPasado]=useState(false);
  const [t, i18n] = useTranslation("global");

  const onChangeObservaciones = () => {

    setObservaciones(observaciones);
    setDetalleUsuario(trabajador);

}


  if (finalTrabajo){
    var minutosAux=(parseInt(finalTrabajo.substring(11, 13))*60+parseInt(finalTrabajo.substring(14, 16)))-(parseInt(inicioTrabajo.substring(11, 13))*60+parseInt(inicioTrabajo.substring(14, 16)));
  }

  
  
  if ( pasado === false && minutosAux > 0){
    let auxiliar=minutos+minutosAux;
    setMinutos(auxiliar);
    minis.push(minutosAux);
    setPasado(true);
  }
  

  return (
    
    <tr>    
      <td>{id}</td>
      <td>{trabajador.username}</td>
      <td>{inicioTrabajo.substring(0, 10)}</td>
      <td>{inicioTrabajo.substring(11, 16)}</td>
      {!finalTrabajo && <td>{t("CrudTableRowTrabajo2.Sin finalizar.")}</td>}
      {finalTrabajo && <td>{finalTrabajo.substring(0, 10)}</td>}
      {!finalTrabajo && <td>{t("CrudTableRowTrabajo2.Sin finalizar.")}</td>}
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