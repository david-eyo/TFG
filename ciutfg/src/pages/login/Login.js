import React, { useState } from 'react';
import "./Login.css"
import { Form, Button } from 'react-bootstrap';
import {
    Link, Navigate
} from "react-router-dom";
import MessageAdvertencia from '../../componentes/MessageAdvertencia';
import Loader from '../../componentes/Loader';
import Message from '../../componentes/Message';
import {useTranslation} from "react-i18next";




function Login({setToken, setRol, setUsername, setDeslogueado, deslogueado}) {

    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [log, setLog]= useState(false);
    
    const [problemaTexto, setProblemaTexto] = useState ('');
    const [loading, setLoading] = useState(false);

    const [t, i18n] = useTranslation("global");
 

    

    const Submit = async (e) => {

        

        e.preventDefault();
        setLoading(true);
        var token1 = null;
        var user1 = null;

        let resp= await fetch('http://localhost:5000/secure/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userName: user,
                password
            })
        }).then((res) =>
            res.ok
                ?res.json()
                : Promise.reject({
                    err: true,
                    status: res.status || "00",
                    statusText: res.statusText || "Ocurrió un error",
                })
        ).catch((err) => setError(err));

        if (resp){
            token1 = resp.token;
            setError(undefined);
            setLog(true);
            
        } else{
            setProblemaTexto("Usuario y/o contraseña incorrectos."); 
            setLoading(false);
        }
        
        setToken(token1);
        setLoading(false);


        var url = 'http://localhost:5000/secure/auth?username='+user;

        let resp1= await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+ token1 },
        }).then((res) =>
            res.ok
                ?res.json()
                : Promise.reject({
                    err: true,
                    status: res.status || "00",
                    statusText: res.statusText || "Ocurrió un error",
                })
        ).catch((err) => setError(err));
        setRol(resp1.authorities[0].authority); 
        setUsername(resp1.username)    
    }


    return (
        <div className='login'>
            {deslogueado &&
                <div className = "deslogueo">
                
                <h3>{t("Login.Usuario deslogueado correctamente")}</h3>
                <Button variant="info" onClick={() => setDeslogueado(false)}>{t("Login.Cerrar")}</Button>
                </div>
            }
            <h1>{t("Login.Iniciar Sesión")}</h1>
            <div style = {{marginLeft:'30%', marginRight: '30%', marginBottom: '1rem'}}>

            </div>


            {log &&
                <Navigate to="/" username={user}></Navigate>
            }


            
            <Form onSubmit={Submit}>
            {error && (
                <MessageAdvertencia
                    msg={`Error: ${problemaTexto}`}
                    bgColor="#dc3545"
                />
            )}
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>{t("Login.Usuario")}</Form.Label>
                    <Form.Control type="text" placeholder="Introduzca usuario"
                        onChange={e => setUser(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label style={{width:'50%'}}>{t("Login.Introduzca contraseña")}</Form.Label>
                    <Form.Control type="password" placeholder="Contraseña"
                        onChange={e => setPassword(e.target.value)} />
                </Form.Group>
                {loading && <Loader />}

                <div style={{marginRight:'10%', marginTop: '3rem'}}>
                    <Button className="boton" variant="primary" type="submit" >
                    {t("Login.Iniciar Sesión")}
                    </Button>
                    <br />
                </div>


                <Link to="/reguser">{t("Login.¿Todavía no se ha registrado? Pulse aquí")}</Link>

            </Form>

        </div>
    )
}


export default Login;
