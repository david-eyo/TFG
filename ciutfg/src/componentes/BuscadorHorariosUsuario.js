import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import { Form,  Button } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import CrudTableRowTrabajo from './CrudTableRowTrabajo';
import "./ObservacionesTrabajo.css"




function BuscadorHorariosUsuario({token, username, setError}) {

    const [data, setData] = useState([]);
    const [fecha1, setFecha1] = useState('');
    const [fecha2, setFecha2] = useState('');
    const [fechaIni, setFechaIni] = useState('');
    const [fechaFin, setFechaFin] = useState('');
    const [localizacion, setLocalizacion] = useState('');
    const [tipo_trabajo, setTipo_trabajo] = useState('');
    const [observaciones, setObservaciones] = useState('');

    let api = helpHttp();
    let url = "http://localhost:5000/trabajo";

    console.log(localizacion);

    const findTrabajos = () => {
        let options = {
          headers: { "content-type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let urlaux = url + "?username=" + username;

        if((fechaIni !== null && fechaIni !== '' && fechaIni !== undefined) && (fechaFin === null|| fechaFin ===undefined || fechaFin === '')){
            console.log("Aqui");
            return;
        }

        let url2="";

        
        console.log(fechaIni);
        //console.log(localizacion);
        //console.log(tipo_trabajo);

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
            console.log(1);

            url2= urlaux+"&tipo_trabajo="+tipo_trabajo+"&localizacion="+localizacion;
        }else if ((fechaIni !== null && fechaIni !== "") && (localizacion!==null && localizacion !== "") && (tipo_trabajo!==null && tipo_trabajo !== "")){

            url2= urlaux+"&fechaInicio="+fechaIni+"&fechaFin="+fechaFin+"&localizacion="+localizacion+"&tipo_trabajo="+tipo_trabajo;
        }else {

            url2=urlaux;
        }
        console.log(url2);
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
        if(res === "--Seleccione localización"){
            setLocalizacion('');
        }else{
            setLocalizacion(res);
        }
    }

    const onChangeTipoTrabajo = (res) => {
        if(res === "--Seleccione tipo trabajo"){
            setTipo_trabajo('');
        }else{
            setTipo_trabajo(res);
        }
    }



    return(
        <div>
            <div className = "formulario">
                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label style = {{width: '10rem'}}>Fecha Inicio</Form.Label>
                    <DatePicker selected={fecha1}
                        onChange={date => onChangeFechaIni(date)}
                        dateFormat='yyyy-MM-dd'
                        scrollableMonthYearDropdown />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label style = {{width: '10rem'}}>Fecha Fin</Form.Label>
                    <DatePicker selected={fecha2}
                        onChange={date => onChangeFechaFin(date)}
                        dateFormat='yyyy-MM-dd'
                        scrollableMonthYearDropdown />
                </Form.Group>
                <Form.Select size="sm" onChange={e => onChangeLocalizacion(e.target.value)} style = {{marginBottom: '2rem', marginTop: '2rem'}}>
                            <option>--Seleccione localización</option>
                            <option>A BARCA</option>
                            <option>CÁMARA</option>
                            <option>FINCA FRESAS</option>
                            <option>OTRO...</option>
                </Form.Select>
                <Form.Select size="sm" onChange={e => onChangeTipoTrabajo(e.target.value)} style = {{marginBottom: '2rem'}}>
                            <option>--Seleccione tipo trabajo</option>
                            <option>RECOLLER</option>
                            <option>CLASIFICAR</option>
                            <option>OTRO...</option>
                </Form.Select>
                <Button variant="success" onClick={() => findTrabajos()}>Buscar</Button>
             </div>
             {observaciones &&
             <div className="observaciones">
                 <p><b>Observaciones: </b>{observaciones}</p>
                 <Button variant="danger" onClick={() => setObservaciones(null)}>Cerrar observaciones</Button>
             </div>}
             <table style ={{marginBottom: '5rem'}} className="table">
                <thead>
                    <tr>
                    <th>Id</th>
                    <th>Inicio Trabajo</th>
                    <th>Hora de inicio</th>
                    <th>Final Trabajo</th>
                    <th>Hora de fin</th>
                    <th>Localizacion</th>
                    <th>Tipo Trabajo</th>
                    </tr>
                </thead>
                <tbody> 
                {data.length > 0 ? (
                    data.map((el) => (
                    <CrudTableRowTrabajo                  
                        el={el}
                        setObservaciones={setObservaciones}
                    />
                    ))
                  ) : (
                <tr>
                  <td colSpan="3">Sin trabajos realizados</td>
                </tr>
              )}
              </tbody>
            </table>


        </div>
    )
}

export default BuscadorHorariosUsuario;