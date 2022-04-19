import React,{useState} from "react";
import './CrudTableRow.css';

const CrudTableRowPedido = ({ el, setDetalle }) => {

  let {id, usuario, item_pedido,estado, fechaPedido} = el;
  const [cantidad, setCantidad]= useState(0);
  const [pasado, setPasado]= useState(false);
  var prueba = 0;

  if(!pasado){
    for (var i = 0; i < item_pedido.length; i++) {
      prueba= (item_pedido[i].cantidad*item_pedido[i].precio.precio)+prueba;
      setCantidad(prueba); 
      if (i == item_pedido.length-1){
        setPasado(true);
      }       
    }
  }



  return (
    <tr>    
      <td>{id}</td>
      <td>{estado}</td>
      <td>{fechaPedido.substring(0, 10)}</td>
      <td>{cantidad}€</td>
      <td>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright" onClick={() => setDetalle(el)}><i className="fa fa-eye"/></button>
      </td>
    </tr>
  );
};

export default CrudTableRowPedido;