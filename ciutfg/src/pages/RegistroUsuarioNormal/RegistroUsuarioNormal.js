import React, { useState } from 'react';
import "./RegistroUsuarioNormal.css"
import { Form, Button } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css'
import moment from 'moment';
import MessageAdvertencia from '../../componentes/MessageAdvertencia';
import { Navigate} from 'react-router';
import {useTranslation} from "react-i18next";

function RegistroUsuarioNormal() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [nombre, setNombre] = useState('');
    const [apellidos, setApellidos] = useState('');
    const [email, setEmail] = useState('');
    const [ciudad, setCiudad] = useState('');
    const [direccion, setDireccion] = useState('');
    const [cp, setCp] = useState('');
    const [fechaNacimiento, setFechaNacimiento] = useState('');
    const [fecha, setFecha] = useState(moment().subtract(18, 'years')._d);
    const [tlf, setTlf] = useState('');
    const [error, setError] = useState(null);
    const [errorUsername, setErrorUsername] = useState(null);
    const [errorPassword, setErrorPassword] = useState(null);
    const [errorNombre, setErrorNombre] = useState(null);
    const [errorApellidos, setErrorApellidos] = useState(null);
    const [errorEmail, setErrorEmail] = useState(null);
    const [errorCiudad, setErrorCiudad] = useState(null);
    const [errorCP, setErrorCP] = useState(null);
    const [errorDireccion, setErrorDireccion] = useState(null);
    const [errorTlf, setErrorTlf] = useState(null);
    const [problemaTexto, setProblemaTexto] = useState ('');
    const [privacidad, setPrivacidad] = useState(false);
    const [activo, setActivo]= useState(false);
    const [bien, setBien]= useState(false);

    const [t, i18n] = useTranslation("global");



    const Submit = async (e) => {

        e.preventDefault();

        await fetch('http://localhost:5000/secure/auth/user/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                username,
                password,
                nombre,
                apellidos,
                email,
                ciudad,
                cp,
                direccion,
                fechaNacimiento,
                tlf
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
        if (error){
            if (error.status === 409 ){
                setProblemaTexto("Nombre de usuario ya existe. Por favor, seleccione otro."); 
            } else{
                setProblemaTexto("Compruebe que todos los campos no contengan ninguna advertencia sobre ellos"+
                 "y vuelva a completar el formulario.")
            }
        } else{
            setError('');
            setBien(true);
            window.confirm("Usuario registrado correctamente");

        }
        

    }

    const onChangeFechaNacimniento = (fecha) => {
        let dia, mes, ano, resultado;

        if (fecha != null) {
            ano = fecha.getFullYear();
            if (parseInt(fecha.getDate()) < 10) {
                dia = '0' + fecha.getDate();
            } else {
                dia = fecha.getDate();
            }
            if (parseInt(fecha.getMonth() + 1) < 10) {
                mes = '0' + (fecha.getMonth() + 1).toString();
            } else {
                mes = (fecha.getMonth() + 1);
            }
            resultado = ano + '-' + mes + '-' + dia;

        }

        setFechaNacimiento(resultado);
        setFecha(fecha);

    }

    const onChangeUsername = (username) => {

        setUsername(username);
        var tamano = username.length;
        if (tamano < 1) {
            setErrorUsername(1);
        } else {
            setErrorUsername(null);
        }

    }


    const onChangePassword = (password) => {

        setPassword(password);
        var tamano = password.length;
        if (tamano < 2) {
            setErrorPassword(1);
        } else {
            setErrorPassword(null);
        }
    }

    const onChangeNombre = (nombre) => {

        setNombre(nombre);
        var tamano = nombre.length;
        if (tamano < 2 || tamano > 40) {
            setErrorNombre(1);
        } else {
            setErrorNombre(null);
        }

    }
    const onChangeApellidos = (apellidos) => {
        setApellidos(apellidos);
        var tamano = apellidos.length;
        if (tamano < 2 || tamano > 100) {
            setErrorApellidos(1);
        } else {
            setErrorApellidos(null);
        }

    }
    const onChangeEmail = (email) => {
        setEmail(email);
        var tamano = email.length;
        if (tamano < 1) {
            setErrorEmail(1);
        } else {
            setErrorEmail(null);
        }
    }
    const onChangeCiudad = (ciudad) => {
        setCiudad(ciudad);
        var tamano = ciudad.length;
        if (tamano < 2 || tamano > 40) {
            setErrorCiudad(1);
        } else {
            setErrorCiudad(null);
        }
    }
    const onChangeCp = (cp) => {
        setCp(cp);
        var tamano = cp.length;
        if (tamano < 4 || tamano > 6) {
            setErrorCP(1);
        } else {
            setErrorCP(null);
        }

    }
    const onChangeDireccion = (direccion) => {
        setDireccion(direccion);
        var tamano = direccion.length;
        if (tamano < 2 || tamano > 40) {
            setErrorDireccion(1);
        } else {
            setErrorDireccion(null);
        }
    }

    const onChangeTlf = (tlf) => {
        setTlf(tlf);
        var tamano = tlf.length;
        if ( tamano < 8) {
            setErrorTlf(1);
        } else {
            setErrorTlf(null);
        }
    }

    const handleChange1=e=> {
        setPrivacidad(e.target.checked);
    };

    const changeActivo=()=> {
        if (activo){
            setActivo(false);
        }else{
            setActivo(true);
        }
    };



    return (

        <div className='login'>
            <h1> {t("RegistroUsuarioNormal.Formulario de registro")}</h1>
            <Form onSubmit={Submit}>
                <Form.Group className="mb-3" controlId="formBasicUsername">
                    <Form.Label style={{width: '50%', marginTop: '1rem'}}>{t("RegistroUsuarioNormal.Nombre de usuario")}</Form.Label>
                    {errorUsername && (
                        <MessageAdvertencia
                            msg={`El nombre de usuario debe tener como mínimo 1 caracter`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca nombre de usuario"
                        onChange={e => onChangeUsername(e.target.value)}
                    />
                </Form.Group>

                {bien &&
                    <Navigate to="/login"></Navigate>
                }
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>{t("RegistroUsuarioNormal.Contraseña")}</Form.Label>
                    {errorPassword && (
                    <MessageAdvertencia
                        msg={`La contraseña debe tener al menos, 2 caracteres`}
                        bgColor="#ff9113"
                    />
                )}
                    <Form.Control type="password" placeholder="Introduzca Contraseña"
                        onChange={e => onChangePassword(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicNombre">
                    <Form.Label>{t("RegistroUsuarioNormal.Nombre")}</Form.Label>
                    {errorNombre && (
                        <MessageAdvertencia
                            msg={`El nombre debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca nombre de pila."
                        onChange={e => onChangeNombre(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicApellidos">
                    <Form.Label>{t("RegistroUsuarioNormal.Apellidos")}</Form.Label>
                    {errorApellidos && (
                        <MessageAdvertencia
                            msg={`Los apellidos deben tener entre 2 y 100 caracteres `}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca apellidos."
                        onChange={e => onChangeApellidos(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicCiudad">
                    <Form.Label>{t("RegistroUsuarioNormal.Ciudad")}</Form.Label>
                    {errorCiudad && (
                        <MessageAdvertencia
                            msg={`La ciudad debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca ciudad de residencia"
                        onChange={e => onChangeCiudad(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicDireccion">
                    <Form.Label>{t("RegistroUsuarioNormal.Dirección")}</Form.Label>
                    {errorDireccion && (
                        <MessageAdvertencia
                            msg={`Direccion debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca calle, portal y piso de residencia"
                        onChange={e => onChangeDireccion(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicCp">
                    <Form.Label>{t("RegistroUsuarioNormal.CP")}</Form.Label>
                    {errorCP && (
                        <MessageAdvertencia
                            msg={`El CP debe tener entre 4 y 6 dígitos`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca código postal."
                        onChange={e => onChangeCp(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>{t("RegistroUsuarioNormal.Email")}</Form.Label>
                    {errorEmail && (
                        <MessageAdvertencia
                            msg={`El email debe tener al menos 1 caracter`}
                            bgColor="#ff9113"
                        />
                    )}

                    <Form.Control type="email" placeholder="Introduzca email de usuario"
                        onChange={e => onChangeEmail(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label>{t("RegistroUsuarioNormal.Fecha de Nacimiento")}</Form.Label>
                    <DatePicker selected={fecha}
                        onChange={date => onChangeFechaNacimniento(date)}
                        dateFormat='yyyy-MM-dd'
                        maxDate={moment().subtract(18, 'years')._d}
                        scrollableMonthYearDropdown />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicTlf">
                    <Form.Label>{t("RegistroUsuarioNormal.Teléfono")}</Form.Label>
                    {errorTlf && (
                        <MessageAdvertencia
                            msg={`El teléfono debe tener como mínimo 9 caracteres `}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca su teléfono personal."
                        onChange={e => onChangeTlf(e.target.value)} />
                </Form.Group>

                {error && (
                    <MessageAdvertencia
                        msg={ `Error ${error.status}:`+problemaTexto}
                        bgColor="#dc3545"
                    />
                )}

                <div style ={{marginTop: '2rem'}}> 
                    <input
                        type="checkbox"
                        name="Privacidad"
                        placeholder="He leído y acepto los términos de privacidad"
                        value={privacidad}
                        onChange={handleChange1}
                    />
                    <label style={{ marginLeft: '0%', width: '50%'}} htmlFor="checkedOferta" className="etiqueta-check"><b>{t("RegistroUsuarioNormal.He leído y acepto la política de privacidad de la empresa.")}</b></label>
                </div>
                {!activo &&
                    <div style={{ marginTop: '2rem', marginRight: '10%'}}>
                        <Button className="boton" variant="info" onClick={changeActivo} >
                        {t("RegistroUsuarioNormal.Leer Política de privacidad de la empresa")}
                        </Button>
                    </div>
                }

                {activo &&
                    <div style={{ marginTop: '2rem', marginRight: '10%'}}>
                        <Button className="boton" variant="danger" onClick={changeActivo} >
                        {t("RegistroUsuarioNormal.Cerrar Política de privacidad de la empresa")}
                        </Button>
                    </div>
                }


                {activo && 
                    <div className="privacidad">
                        <p>
                            <h4><b>{t("RegistroUsuarioNormal.POLÍTICA DE PRIVACIDAD")}</b></h4>
                            {t("RegistroUsuarioNormal.Parte1")}
                            <br></br>
                            <br></br>
                            <b>{t("RegistroUsuarioNormal.Parte2")}</b>
                            <br></br>

                            {t("RegistroUsuarioNormal.Parte3")}
                            <br></br>
                            <br></br>
                            <b>{t("RegistroUsuarioNormal.Parte4")}</b>
                            <br></br>

                            {t("RegistroUsuarioNormal.Parte5")}
                            {t("RegistroUsuarioNormal.Parte6")}                            
                            <br></br>
                            <br></br>
                            <b>{t("RegistroUsuarioNormal.Parte7")}</b>
                            <br></br>

                            {t("RegistroUsuarioNormal.Parte8")}
                            <br></br>
                            <br></br>
                            <b>{t("RegistroUsuarioNormal.Parte9")}</b>
                            <br></br>

                            {t("RegistroUsuarioNormal.PP10")}
                            {t("RegistroUsuarioNormal.PP11")}
                            {t("RegistroUsuarioNormal.PP12")}
                        </p>
                    </div>
                }

                


                <br></br>

                <Button className="boton" style={{ marginLeft: '0%'}} variant="primary" type="submit" disabled={!privacidad}>
                    {t("RegistroUsuarioNormal.Registrarse aqui")}
                </Button>

            </Form>


        </div>
    )
}

export default RegistroUsuarioNormal;
