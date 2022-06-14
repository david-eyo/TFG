import React, {useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import Message from '../componentes/Message';
import "../componentes/RegistroHorario.css"
import {useTranslation} from "react-i18next";


function RegistroHorario({token, username}) {

    const [localizacion, setLocalizacion] = useState('');
    const [tipo_trabajo, setTipo_trabajo] = useState('');
    const [observaciones, setObservaciones] = useState('');
    const [error, setError] = useState(null);
    const [error2, setError2] = useState(null);
    const [nada, setNada]= useState('');
    const [t, i18n] = useTranslation("global");


    









    

    const onChangeObservaciones = (res) => {
        setObservaciones(res);
    }



    const errorAux = (err, status, statusText) => { return { err: err, status: status, statusText: statusText } }

    const Submit = async (e) => {

        e.preventDefault();

        if(localizacion !== '' && tipo_trabajo !== '' && localizacion !== undefined && tipo_trabajo !== undefined){
            await fetch('http://localhost:5000/trabajo', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json',
                            'Authorization': 'Bearer '+token },
                body: JSON.stringify({
                    username,
                    localizacion,
                    tipo_trabajo,
                })
            }).then((res) =>
                res.ok
                    ? res.json()
                    : Promise.reject({
                        err: true,
                        status: res.status || "00",
                        statusText: res.statusText || "Ocurrió un error",
                    })                   
            ).catch((err) => setError(err));
            if (error2){
                setError2(null);
            }
        }else{
            const erro= errorAux(true, 420, "No se han cubierto todos los campos de inicio de jornada adecuadamente");
            setError(erro);
        }
    }

    const Submit2 = async (e) => {

        e.preventDefault();

        await fetch('http://localhost:5000/trabajo', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+token },
            body: JSON.stringify({
                username,
                observaciones
            })
        }).then((res) =>{ 
                      
            res.ok
            ? res.json()
            : Promise.reject({
                err: true,
                status: res.status || "00",
                statusText: res.statusText || "Ocurrió un error",
            })


        },
         
        ).catch((err) => setError2(err));

        if(error){
            setError(null);
        }
        
    }

    if (error && error.status===404){
            error.statusText="Se está intentando iniciar una jornada laboral que ya está iniciada.";
    }

    if (error2){
        if (error2.status===409){
            error2.statusText="La jornada laboral ya la has finalizado, inicie una nueva, por favor :=)";
        }
        if (error2.status===400){
            error2.statusText="Compruebe que el campo observaciones está cubierto.";
        }
    }


    return (
        <div>
            <div className= "registro">
                <h5><i>{t("RegistroHorario.Marca aquí que ya has empezado tu jornada laboral!")}</i></h5>
                {error &&
                    <Message
                            msg={`Error: ${error.statusText}`}
                            bgColor="#dc3545"
                    />        
                }

                <Form onSubmit={Submit}>
                    <Form.Select size="sm" onChange={e => setLocalizacion(e.target.value)} style = {{marginBottom: '2rem', marginTop: '2rem'}}>
                                <option>--Seleccione localización</option>
                                <option>A BARCA</option>
                                <option>CÁMARA</option>
                                <option>FINCA FRESAS</option>
                                <option>OTRO...</option>
                    </Form.Select>

                    <Form.Select size="sm" onChange={e => setTipo_trabajo(e.target.value)} style = {{marginBottom: '2rem'}}>
                                <option>--Seleccione tipo trabajo</option>
                                <option>RECOLLER</option>
                                <option>CLASIFICAR</option>
                                <option>OTRO...</option>
                    </Form.Select>

                    <Button className="boton" variant="primary" type="submit">
                    {t("RegistroHorario.Iniciar trabajo!")}
                    </Button>
                </Form>
            </div>

            <div className= "registro2">
                <h5><i>{t("RegistroHorario.Marca aquí que ya has finalizado tu jornada laboral!")}</i></h5>
                <Form onSubmit={Submit2}>
                    {error2 &&
                        <Message
                                msg={`Error: ${error2.statusText}`}
                                bgColor="#dc3545"
                        />        
                    }
                    <Form.Group className="mb-3" controlId="formBasicApellidos">
                        <Form.Label>{t("RegistroHorario.Observaciones")}</Form.Label>
                        <Form.Control type="text" placeholder=""
                            onChange={e => onChangeObservaciones(e.target.value)} />
                    </Form.Group>

                    <Button className="boton" variant="primary" type="submit">
                            {t("RegistroHorario.Finalizar trabajo!")}
                    </Button>
                </Form>
            </div>

        </div>


        
    )
}

export default RegistroHorario;