import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import { Form,  Button } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import CrudTableRowTrabajo2 from './CrudTableRowTrabajo2';
import "./BuscadorHorariosAdministrador.css";
import {useTranslation} from "react-i18next";




function BuscadorHorariosAdministrador({token, setError}) {

    const [data, setData] = useState([]);
    const [fecha1, setFecha1] = useState('');
    const [fecha2, setFecha2] = useState('');
    const [fechaIni, setFechaIni] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [localizacion, setLocalizacion] = useState('');
    const [username, setUsername] = useState('');
    const [tipo_trabajo, setTipo_trabajo] = useState('');
    const [observaciones, setObservaciones] = useState(null);
    const [detalleUsuario, setDetalleUsuario] = useState(null);
    const [detalle, setDetalle] = useState(false);
    const [user, setUser] = useState(null);
    const [sueldo, setSueldo] = useState(0);
    const [sueldomin, setSueldomin] = useState(0);
    const [t, i18n] = useTranslation("global");
    const [calculadora, setCalculadora]= useState(false);
    const [minutos, setMinutos]= useState(0);
    const [minis, setMinis]= useState([]);

    

    let api = helpHttp();
    let url = "http://localhost:5000/trabajo";

    const findTrabajos = () => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let urlaux = url + "?username=" + username;
        if((fechaIni !== null && fechaIni !== '' && fechaIni !== undefined) && (fechaFin === null|| fechaFin ===undefined || fechaFin === '')){
            return;
        }

        let url2="";

        setMinis([]);

        if(username!==null && username !== "" && username !== undefined){
            if((fechaIni!==null && fechaIni !== "" && fechaIni !== undefined) && (localizacion===null || localizacion === "") && (tipo_trabajo===null || tipo_trabajo === "")){

                url2=urlaux+"&fechaInicio="+fechaIni+"&fechaFin="+fechaFin;
            }else if ((fechaIni === null || fechaIni === "") && (localizacion!==null && localizacion !== '') && (tipo_trabajo===null || tipo_trabajo === "")){
    
                url2=urlaux+"&localizacion="+localizacion
            }else if ((fechaIni === null || fechaIni === "") && (localizacion===null || localizacion === "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    
                url2=urlaux+"&tipo_trabajo="+tipo_trabajo;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo===null || tipo_trabajo === "")){
    
                url2= urlaux+"&fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&localizacion="+localizacion;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion===null || localizacion === "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    ;
                url2= urlaux+"&fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&tipo_trabajo="+tipo_trabajo;
            }else if ((fechaIni === null || fechaIni === "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    
                url2= urlaux+"&tipo_trabajo="+tipo_trabajo+"&localizacion="+localizacion;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    
                url2= urlaux+"&fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&localizacion="+localizacion+"&tipo_trabajo="+tipo_trabajo;
            }else {
    
                url2=urlaux;
            }
        }else{
            if((fechaIni!==null && fechaIni !== "" && fechaIni !== undefined) && (localizacion===null || localizacion === "") && (tipo_trabajo===null || tipo_trabajo === "")){

                url2=url+"?fechaInicio="+fechaIni+"&fechaFin="+fechaFin;
            }else if ((fechaIni === null || fechaIni === "") && (localizacion!==null && localizacion !== '') && (tipo_trabajo===null || tipo_trabajo === "")){
    
                url2=url+"?localizacion="+localizacion
            }else if ((fechaIni === null || fechaIni === "") && (localizacion===null || localizacion === "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    
                url2=url+"?tipo_trabajo="+tipo_trabajo;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo===null || tipo_trabajo === "")){
    
                url2= url+"?fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&localizacion="+localizacion;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion===null || localizacion === "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    ;
                url2= url+"?fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&tipo_trabajo="+tipo_trabajo;
            }else if ((fechaIni === null || fechaIni === "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
                url2= url+"?tipo_trabajo="+tipo_trabajo+"&localizacion="+localizacion;
            }else if ((fechaIni !== null && fechaIni !== "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo!==null && tipo_trabajo !== "")){
    
                url2= url+"?fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&localizacion="+localizacion+"&tipo_trabajo="+tipo_trabajo;
            }else {
                url2=url;
            }

        }


        api.get(url2, options).then((res) => {
          if (!res.err) {
            setData(res);
            setError(null);
          } else {
            setData([])
            setError(res);
          }
        });
        return data;
        
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

    const onChangeLocalizacion = (res) => {
        if(res === "--Seleccione localizaci??n"){
            setLocalizacion('');
        }else{
            setLocalizacion(res);
        }
    }

    const onChangeUsername = (res) => {
        setUsername(res);
    }

    const onChangeTipoTrabajo = (res) => {
        if(res === "--Seleccione tipo trabajo"){
            setTipo_trabajo('');
        }else{
            setTipo_trabajo(res);
        }
    }

    var sum =0;

    console.log(minis);
    if(minis.length>0 && sueldomin){
        sum = ((minis.reduce((partialSum, a) => partialSum + a, 0))/2);

    }

    return(
        <div>
            <div className = "formulario">
                <Form.Group className="mb-3" controlId="formBasicNombre">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder=""
                        onChange={e => onChangeUsername(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label style = {{width: '10rem'}}>{t("BuscadorHorariosAdministrador.Fecha de Inicio")}</Form.Label>
                    <DatePicker selected={fecha1}
                        onChange={date => onChangeFechaIni(date)}
                        dateFormat='yyyy-MM-dd'
                        scrollableMonthYearDropdown />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label style = {{width: '10rem'}}>{t("BuscadorHorariosAdministrador.Fecha de Fin")}</Form.Label>
                    <DatePicker selected={fecha2}
                        onChange={date => onChangeFechaFin(date)}
                        dateFormat='yyyy-MM-dd'
                        scrollableMonthYearDropdown />
                </Form.Group>
                <Form.Select size="sm" onChange={e => onChangeLocalizacion(e.target.value)} style = {{marginBottom: '2rem', marginTop: '2rem'}}>
                            <option>--Seleccione localizaci??n</option>
                            <option>A BARCA</option>
                            <option>C??MARA</option>
                            <option>FINCA FRESAS</option>
                            <option>OTRO...</option>
                </Form.Select>
                <Form.Select size="sm" onChange={e => onChangeTipoTrabajo(e.target.value)} style = {{marginBottom: '2rem'}}>
                            <option>--Seleccione tipo trabajo</option>
                            <option>RECOLLER</option>
                            <option>CLASIFICAR</option>
                            <option>OTRO...</option>
                </Form.Select>
                <Button variant="success" onClick={() => findTrabajos()}>{t("BuscadorHorariosAdministrador.Buscar")}</Button>
             </div>

             {observaciones !== null &&
             <div className='detalleHorario'>
                 <h5 style={{marginBottom: '1rem'}}><b>{t("BuscadorHorariosAdministrador.Observaciones:")}</b> {observaciones}</h5>
                 {detalle &&
                    <Button style ={{marginBottom: '1rem'}}variant="danger" onClick = { () => setDetalle(false)}>Quitar detalle del trabajador <i className="fa fa-close"/></Button>
                 }
                {!detalle &&
                    <Button style ={{marginRight: '1rem'}} variant="success" onClick = { () => setDetalle(true)}>Detalle del trabajador <i className="fa fa-plus-square-o"/></Button>
                 }
                 {detalle && 
                    <div className= "detalleUsuario">
                        <h4><i>{t("BuscadorHorariosAdministrador.Detalle de usuario")}</i></h4>
                        <br></br>
                        <div style = {{float: 'left'}}>
                        <p><b>Id:</b> {detalleUsuario.id}</p>
                        <p><b>Nombre:</b> {detalleUsuario.nombre}</p>
                        <p><b>Apellidos:</b> {detalleUsuario.apellidos}</p>
                        <p><b>Direcci??n:"</b> {detalleUsuario.direccion}, {detalleUsuario.ciudad}, {detalleUsuario.cp}.</p>
                        </div>
                        <p><b>Email:</b> {detalleUsuario.email}</p>
                        <p><b>Fecha de Nacimiento:</b> {detalleUsuario.fechaNacimiento.substring(0,10)}</p>
                        <p><b>Tel??fono: </b>{detalleUsuario.tlf}</p>                   
                    </div>
                }
                 <Button variant="danger" onClick={() => setObservaciones(null)}>{t("BuscadorHorariosAdministrador.Cerrar observaciones")}
                 <i style={{marginLeft: '0.5rem'}} className="fa fa-close"/></Button>
             </div>
             }
            {!calculadora &&
                <Button variant="success" style={{marginBottom: '10rem', marginTop: '0rem'}} onClick={() => setCalculadora(true)}>Abrir calculadora</Button>
            }
            {calculadora &&
                
                <div style={{width:'30%', borderStyle:'solid', borderWidth: '3px',
                           paddingBlock: '2%',margin: '0 auto', paddingInline: '4%', borderRadius: '20px', marginBottom: '1rem'}}>
               <Button variant="danger" style ={{float: 'right'}} onClick={() => setCalculadora(false)}>Cerrar</Button>
               <br></br>
               <h2 style ={{float:'left'}}><i>{t("BuscadorHorariosAdministrador.Calculadora de salarios")}</i></h2>
               
               <br></br>
               <br></br>
               <br></br>
               <Form.Group className="mb-3" controlId="formBasicPassword" onChange={e => setSueldomin(e.target.value)}>
                   <Form.Label>{t("BuscadorHorariosAdministrador.Sueldo")}</Form.Label>
                   <Form.Control type="number" placeholder="Sueldo por hora" />
               </Form.Group>
               <br></br>
               <h3 style={{float:'right', marginRight: '2%'}}>{t("BuscadorHorariosAdministrador.Sueldo correspondiente:")} {(sum*(sueldomin/60)).toFixed(2)}???</h3>
           </div>
           }
             <table style ={{marginBottom: '5rem'}} className="table">
                <thead>
                    <tr>
                    <th>{t("BuscadorHorariosAdministrador.Id")}</th>
                    <th>Username</th>
                    <th>{t("BuscadorHorariosAdministrador.Inicio Trabajo")}</th>
                    <th>{t("BuscadorHorariosAdministrador.Hora de inicio")}</th>
                    <th>{t("BuscadorHorariosAdministrador.Final Trabajo")}</th>
                    <th>{t("BuscadorHorariosAdministrador.Hora de fin")}</th>
                    <th>{t("BuscadorHorariosAdministrador.Localizacion")}</th>
                    <th>{t("BuscadorHorariosAdministrador.Tipo Trabajo")}</th>
                    </tr>
                </thead>
                <tbody>
                {data.length > 0 ? (
                    data.map((el) => (
                    <CrudTableRowTrabajo2 
                        key={el.id}
                        el={el}
                        setObservaciones={setObservaciones}
                        setDetalleUsuario={setDetalleUsuario}
                        minutos={minutos}
                        setMinutos={setMinutos}
                        minis ={minis}
                        setMinis={setMinis}
                    />
                    ))
                  ) : (
                <tr>
                  <td colSpan="3">{t("BuscadorHorariosAdministrador.Sin trabajos realizados")}</td>
                </tr>
              )}
              </tbody>
            </table>





        </div>
    )
}

export default BuscadorHorariosAdministrador;