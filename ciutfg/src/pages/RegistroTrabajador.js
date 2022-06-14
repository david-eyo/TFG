import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css'
import moment from 'moment';
import MessageAdvertencia from '../componentes/MessageAdvertencia';
import { Navigate} from 'react-router';
import {useTranslation} from "react-i18next";

function RegistroTrabajador(token) {

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
    const [dni, setDni] = useState('');
    const [nss, setNss] = useState('');
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
    const [errorDni, setErrorDni] = useState(null);
    const [errorNss, setErrorNss] = useState(null);
    const [problemaTexto, setProblemaTexto] = useState ('');
    const [t, i18n] = useTranslation("global");



    const Submit = async (e) => {

        e.preventDefault();

        await fetch('http://localhost:5000/secure/auth/trabajador/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        "Authorization": "Bearer "+token.token},
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
                tlf,
                dni,
                nss
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
            window.confirm("Usuario registrado correctamente");
            return <Navigate push to={{
                pathname: '/login',
              }}
              />
        }
        

    }

    console.log(error);

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

    const onChangeDni = (dni) => {

        setDni(dni);
        var tamano = dni.length;
        if (tamano !== 9) {
            setErrorDni(1);
        } else {
            setErrorDni(null);
        }

    }

    const onChangeNss = (nss) => {

        setNss(nss);
        var tamano = nss.length;
        if (tamano !== 9) {
            setErrorNss(1);
        } else {
            setErrorNss(null);
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


    return (
        <div className='login'>
            <h1>{t("RegistroTrabajador.Formulario de registro")}</h1>
            <Form onSubmit={Submit}>
                <Form.Group className="mb-3" controlId="formBasicUsername">
                    <Form.Label>{t("RegistroTrabajador.Nombre de usuario")}</Form.Label>
                    {errorUsername && (
                        <MessageAdvertencia
                            msg={`El nombre de usuario debe tener como mínimo 1 caracter`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="Introduzca usuario"
                        onChange={e => onChangeUsername(e.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>{t("RegistroTrabajador.Contraseña")}</Form.Label>
                    {errorPassword && (
                    <MessageAdvertencia
                        msg={`La contraseña debe tener al menos, 2 caracteres`}
                        bgColor="#ff9113"
                    />
                )}
                    <Form.Control type="password" placeholder="Contraseña"
                        onChange={e => onChangePassword(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicNombre">
                    <Form.Label>{t("RegistroTrabajador.Nombre")}</Form.Label>
                    {errorNombre && (
                        <MessageAdvertencia
                            msg={`El nombre debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder=""
                        onChange={e => onChangeNombre(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicApellidos">
                    <Form.Label>{t("RegistroTrabajador.Apellidos")}</Form.Label>
                    {errorApellidos && (
                        <MessageAdvertencia
                            msg={`Los apellidos deben tener entre 2 y 100 caracteres `}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder=""
                        onChange={e => onChangeApellidos(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicCiudad">
                    <Form.Label>{t("RegistroTrabajador.Ciudad")}</Form.Label>
                    {errorCiudad && (
                        <MessageAdvertencia
                            msg={`La ciudad debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="p.e: Santiago de Compostela"
                        onChange={e => onChangeCiudad(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicDireccion">
                    <Form.Label>{t("RegistroTrabajador.Dirección")}</Form.Label>
                    {errorDireccion && (
                        <MessageAdvertencia
                            msg={`Direccion debe tener entre 2 y 40 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="p.e: Calle Ejemplo, n1, 3ºB"
                        onChange={e => onChangeDireccion(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicCp">
                    <Form.Label>{t("RegistroTrabajador.CP")}</Form.Label>
                    {errorCP && (
                        <MessageAdvertencia
                            msg={`El CP debe tener entre 4 y 6 dígitos`}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="p.e: 15000"
                        onChange={e => onChangeCp(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>{t("RegistroTrabajador.Email")}</Form.Label>
                    {errorEmail && (
                        <MessageAdvertencia
                            msg={`El email debe tener al menos 1 caracter`}
                            bgColor="#ff9113"
                        />
                    )}

                    <Form.Control type="email" placeholder="Introduzca usuario"
                        onChange={e => onChangeEmail(e.target.value)} />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicFechaNacimiento">
                    <Form.Label>{t("RegistroTrabajador.Fecha de Nacimiento")}</Form.Label>
                    <DatePicker selected={fecha}
                        onChange={date => onChangeFechaNacimniento(date)}
                        dateFormat='yyyy-MM-dd'
                        maxDate={moment().subtract(18, 'years')._d}
                        scrollableMonthYearDropdown />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicTlf">
                    <Form.Label>{t("RegistroTrabajador.Teléfono")}</Form.Label>
                    {errorTlf && (
                        <MessageAdvertencia
                            msg={`El teléfono debe tener como mínimo 9 caracteres `}
                            bgColor="#ff9113"
                        />
                    )}
                    <Form.Control type="text" placeholder="p.e: 981981981"
                        onChange={e => onChangeTlf(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicDni">
                    <Form.Label>{t("RegistroTrabajador.DNI/NIF")}</Form.Label>
                    {errorDni && (
                        <MessageAdvertencia
                            msg={`El DNI/NIF debe tener 9 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}

                    <Form.Control type="text" placeholder="Introduzca DNI/NIF"
                        onChange={e => onChangeDni(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicNss">
                    <Form.Label>{t("RegistroTrabajador.NSS")}</Form.Label>
                    {errorNss && (
                        <MessageAdvertencia
                            msg={`El Numero de la Seguridad Social debe tener 9 caracteres`}
                            bgColor="#ff9113"
                        />
                    )}

                    <Form.Control type="text" placeholder="Introduzca número de la Seguridad Social"
                        onChange={e => onChangeNss(e.target.value)} />
                </Form.Group>

                {error && (
                    <MessageAdvertencia
                        msg={ `Error ${error.status}:`+problemaTexto}
                        bgColor="#dc3545"
                    />
                )}

                <Button className="boton" variant="primary" type="submit">
                {t("RegistroTrabajador.Registrarse")}
                </Button>

            </Form>


        </div>
    )
}

export default RegistroTrabajador;
