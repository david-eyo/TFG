import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { Form, FormControl, Button } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css'
import './BuscadorPedidosComp.css'
import moment from 'moment';
import { helpHttp } from "../helpers/helpHttp";
import CrudTableRowPedido2 from './CrudTableRowPedido2';
import CrudTableItemPedido from './CrudTableItemPedido';
import {useTranslation} from "react-i18next";






export default function BuscadorPedidosComp({ token, setError}) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);


    const [username, setUsername] = useState('');
    const [fecha1, setFecha1] = useState('');
    const [fecha2, setFecha2] = useState('');
    const [fechaIni, setFechaIni] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [estado, setEstado] = useState('');

    const [detalle, setDetalle] = useState();
    const [detalleUsuario, setDetalleUsuario] = useState();
    const [t, i18n] = useTranslation("global");


    let api = helpHttp();
    let url = "http://localhost:5000/pedidos";

    //console.log(username);
    //console.log(estado);
    //console.log(fechaIni);
    //console.log(fechaFin);
    //console.log('-------------------------------------------------------------------------------------------');
  
  
    const findPedidosByUsername2 = (nombre) => {
      let options = {
        headers: { "content-type": "application/json",
                  "Authorization": "Bearer "+token },
      };
  
      let url2 = url + "?username=" + nombre;

  
      api.get(url2, options).then((res) => {
        if (!res.err) {
          setData2(res);
          setError(null);
        } else {
          setData2([])
          setError(res);
        }
      });
      return data2;
    };

    const findPedidosByEstado = (estadoent) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2 = url + "?estadoent=" + estadoent;
    
        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };
    
      const findPedidosByFecha = (fechaIni, fechaFin) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2= "";
        if (fechaFin){
            url2 = url + "?fechaIni=" + fechaIni + "&fechaFin=" + fechaFin;
        }else{
            url2 = url + "?fechaIni=" + fechaIni;
        }
        
    
        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };
      
    
      const findPedidosByUsuarioyFecha = (nombre, fechaIni, fechaFin) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer " + token},
        };
    
        let url2= "";
        if (fechaFin){
            url2 = url + "?username=" + nombre + "&fechaIni=" + fechaIni + "&fechaFin=" + fechaFin;
        }else{
            url2 = url + "?username=" + nombre + "&fechaIni=" + fechaIni;
        }
    
        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };
    
      const findPedidosByUsuarioyEstado = (nombre, estado) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2 = url + "?username=" + nombre+ "&estadoent="+ estado;

        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };
    
      const findPedidosByEstadoyFecha = (estadoent, fechaIni, fechaFin) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2= "";
        if (fechaFin){
            url2 = url + "?estadoent=" + estadoent + "&fechaIni=" + fechaIni + "&fechaFin=" + fechaFin;
        }else{
            url2 = url + "?estadoent=" + estadoent + "&fechaIni=" + fechaIni;
        }
    
        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };
    
      const findPedidosByUsuarioEstadoyFecha = (nombre, estadoent, fechaIni, fechaFin) => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2= "";
        if (fechaFin){
            url2 = url + "?username=" + nombre + "&estadoent=" + estadoent + "&fechaIni=" + fechaIni + "&fechaFin=" + fechaFin;
        }else{
            url2 = url + "?username=" + nombre + "&estadoent=" + estadoent + "&fechaIni=" + fechaIni;
        }
    
        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData2(res);
            setError(null);
          } else {
            setData2([])
            setError(res);
          }
        });
        return data2;
      };

    const onChangeFechaIni = (fecha1) => {
        let dia, mes, ano, resultado;
        if (fecha1 !== null) {
            ano = fecha1.getFullYear();
            if (parseInt(fecha1.getDate()) < 10) {
                dia = '0' + fecha1.getDate();
            } else {
                dia = fecha1.getDate();
            }
            if (parseInt(fecha1.getMonth() + 1) < 10) {
                mes = '0' + (fecha1.getMonth() + 1).toString();
            } else {
                mes = (fecha1.getMonth() + 1);
            }
            resultado = ano + '-' + mes + '-' + dia;

        }

        setFechaIni(resultado);
        setFecha1(fecha1);

    }

    const onChangeFechaFin = (fecha2) => {
        let dia, mes, ano, resultado;

        if (fecha2 != null) {
            ano = fecha2.getFullYear();
            if (parseInt(fecha2.getDate()) < 10) {
                dia = '0' + fecha2.getDate();
            } else {
                dia = fecha2.getDate();
            }
            if (parseInt(fecha2.getMonth() + 1) < 10) {
                mes = '0' + (fecha2.getMonth() + 1).toString();
            } else {
                mes = (fecha2.getMonth() + 1);
            }
            resultado = ano + '-' + mes + '-' + dia;

        }

        setFechaFin(resultado);
        setFecha2(fecha2);

    }

    const updatePedido = (data) => {

        let isDelete = window.confirm(
            `¿Estás seguro de cambiar el estado del pedido '${data.id}': ${data.estado}?`
          );
        
        if (isDelete){

            let endpoint = `${url}/${data.id}`;

    
            let options = {
              headers: { "content-type": "application/json",
              "Authorization": "Bearer "+token},
            };
        
            api.put(endpoint, options).then((res) => {
              //console.log(res);
              if (!res.err) {
                let newData = data2.map((el) => (el.id === data.id ? data : el));
                setData2(newData);
              } else {
                setError(res);
              }
            });

        }else{
            return;
        }

        
      };

    const onChangeUsername = (res) => {
        setUsername(res);
    }

    const onChangeEstado = (res) => {
        if(res === "--seleccione estado del envio (opcional)--"){
            setEstado('');
        }else{
            setEstado(res);
        }
    }


    
    const realizarBusquedaPedido = () => {
        if (username == '' && (fechaIni == '' || fechaIni === undefined) && (fechaFin == '' || fechaFin === undefined) && estado == ''){

        } else {
            if(username != '' && (fechaIni == '' || fechaIni === undefined) && estado == ''){
                findPedidosByUsername2(username);
            } else if (username == '' && (fechaIni == '' || fechaIni === undefined) && estado != ''){
                findPedidosByEstado(estado);

            } else if (username == '' && !(fechaIni == '' || fechaIni === undefined) && estado == ''){
                if (fechaFin == '' || fechaFin === undefined){
                    findPedidosByFecha(fechaIni);
                }else{

                }

            } else if (username != '' && !(fechaIni == '' || fechaIni === undefined) && estado == ''){
                if (fechaFin == '' || fechaFin === undefined){
                    findPedidosByUsuarioyFecha(username,fechaIni);
                }else{
                    findPedidosByUsuarioyFecha(username,fechaIni, fechaFin);
                }
            } else if (username != '' && (fechaIni == '' || fechaIni === undefined) && estado != ''){
                findPedidosByUsuarioyEstado(username, estado);

            } else if (username == '' && !(fechaIni == '' || fechaIni === undefined) && estado != ''){
                if (fechaFin == '' || fechaFin === undefined){
                    findPedidosByEstadoyFecha(estado, fechaIni);
                }else{
                    findPedidosByEstadoyFecha(estado, fechaIni, fechaFin);
                }
            }else{
                if (fechaFin == '' || fechaFin === undefined){
                    findPedidosByUsuarioEstadoyFecha(username, estado, fechaIni);
                }else{
                    findPedidosByUsuarioEstadoyFecha(username, estado, fechaIni, fechaFin);
                }

            }
        }
    }

    
    return (
        <div>
            <div className = "formulario" onSubmit={() => realizarBusquedaPedido()}>
                    <h4><i>{t("BuscadorPedidosComp.Formulario de Búsqueda de Pedidos")}</i></h4>
                    <br></br>
                    <FormControl
                        type="search"
                        placeholder="Nombre de usuario"
                        className="me-2"
                        aria-label="Search"
                        value={username}
                        onChange={e => onChangeUsername(e.target.value)}
                    />
                    <br></br>
                    <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                        <Form.Label style = {{width: '10rem'}}>{t("BuscadorPedidosComp.Fecha Inicio")}</Form.Label>
                        <DatePicker selected={fecha1}
                            onChange={date => onChangeFechaIni(date)}
                            dateFormat='yyyy-MM-dd'
                            scrollableMonthYearDropdown />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                        <Form.Label>{t("BuscadorPedidosComp.Fecha Fin")}</Form.Label>
                        <DatePicker selected={fecha2}
                            placeholder="Nombre de usuario"
                            onChange={date => onChangeFechaFin(date)}
                            dateFormat='yyyy-MM-dd'
                            scrollableMonthYearDropdown />
                    </Form.Group>
                    <br></br>
                    <br></br>
                    <Form.Select size="sm" onChange={e => onChangeEstado(e.target.value)}>
                        <option>--seleccione estado del envio (opcional)--</option>
                        <option>NOTIFICADO</option>
                        <option>ENVIADO</option>
                        <option>ENTREGA</option>
                        <option>ENTREGADO</option>
                    </Form.Select>

                    <br></br>
                    <br></br>
                    <Button variant="success" onClick={() => realizarBusquedaPedido()}>{t("BuscadorPedidosComp.Buscar")}</Button>
                  </div>













            { detalle &&
              <div className="detalleProducto" style = {{marginBottom: '2rem'}}>
                <h4 style ={{float: 'left'}}><i>Detalle del pedido</i></h4>
                  <br></br>
                  <br></br>
                  <p><b>{t("BuscadorPedidosComp.Id del pedido:")}</b> {detalle.id}</p>
                  <p><b>{t("BuscadorPedidosComp.Fecha del pedido:")}</b> {detalle.fechaPedido.substring(0,10)}</p>
                  <p><b>{t("BuscadorPedidosComp.Estado del pedido:")}</b> {detalle.estado}</p>
                  <p><b>{t("BuscadorPedidosComp.Nombre de usuario del comprador:")}</b> {detalle.usuario.username}</p>

                <CrudTableItemPedido 
                  data={detalle.item_pedido}
                />
                {!detalleUsuario &&
                
                <Button variant="success" onClick = { () => setDetalleUsuario(detalle.usuario)}>{t("BuscadorPedidosComp.Ver detalle del usuario")} </Button>
                }
                {detalleUsuario &&
                <Button variant="danger" onClick = { () => setDetalleUsuario()}>{t("BuscadorPedidosComp.Quitar detalle del usuario")} <i className="fa fa-close"/></Button>
                }
                <br></br>
                <br></br>
                {detalleUsuario && 
                <div className= "detalleUsuario">
                  <h4><i>{t("BuscadorPedidosComp.Detalle de usuario")}</i></h4>
                  <br></br>
                  <div style = {{float: 'left'}}>
                  <p><b>{t("BuscadorPedidosComp.Id usuario:")}</b> {detalleUsuario.id}</p>
                  <p><b>{t("BuscadorPedidosComp.Nombre:")}</b> {detalleUsuario.nombre}</p>
                  <p><b>{t("BuscadorPedidosComp.Apellidos:")}</b> {detalleUsuario.apellidos}</p>
                  <p><b>{t("BuscadorPedidosComp.Direccion de envíos:")}</b> {detalleUsuario.direccion}, {detalleUsuario.ciudad}, {detalleUsuario.cp}.</p>
                  </div>
                  <p><b>{t("BuscadorPedidosComp.Email:")}</b> {detalleUsuario.email}</p>
                  <p><b>{t("BuscadorPedidosComp.Fecha de Nacimiento:")}</b> {detalleUsuario.fechaNacimiento.substring(0,10)}</p>
                  <p><b>{t("BuscadorPedidosComp.Teléfono:")}</b>{detalleUsuario.tlf}</p>
                  
                  
                </div>
                }
                <Button variant="danger" onClick = { () => setDetalle()}>{t("BuscadorPedidosComp.Quitar detalle total")} <i className="fa fa-close"/></Button>
              </div>
            }
            
            <table style ={{marginBottom: '5rem'}} className="table">
                <thead>
                    <tr>
                    <th>{t("BuscadorPedidosComp.Id")}</th>
                    <th>{t("BuscadorPedidosComp.Nombre")}</th>
                    <th>{t("BuscadorPedidosComp.Apellidos")}</th>
                    <th>{t("BuscadorPedidosComp.Direccion")}</th>
                    <th>{t("BuscadorPedidosComp.Ciudad")}</th>
                    <th>{t("BuscadorPedidosComp.CP")}</th>
                    <th>{t("BuscadorPedidosComp.Estado")}</th>
                    <th>{t("BuscadorPedidosComp.Fecha")}</th>
                    <th>{t("BuscadorPedidosComp.Importe")}</th>
                    </tr>
                </thead>
                <tbody>
                {data2.length > 0 ? (
                    data2.map((el) => (
                    <CrudTableRowPedido2
                        el={el}
                        updatePedido={updatePedido}
                        setDetalle={setDetalle}
                    />
            ))
          ) : (
            <tr>
              <td colSpan="3">{t("BuscadorPedidosComp.Sin pedidos realizados")}</td>
            </tr>
          )}
                </tbody>
            </table>
        </div>
    );
}


