import React, { useState } from 'react';
import "./Login.css"
import { Form, Button } from 'react-bootstrap';
import {
    Link
} from "react-router-dom";
import MessageAdvertencia from '../../componentes/MessageAdvertencia';
import Loader from '../../componentes/Loader';




function Login({setToken, token}) {

    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    
    const [problemaTexto, setProblemaTexto] = useState ('');
    const [loading, setLoading] = useState(false);
 

    

    const Submit = async (e) => {

        

        e.preventDefault();
        setLoading(true);
        var token1 = null;

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
            
        } else{
            setProblemaTexto("Usuario y/o contraseña incorrectos."); 
            setLoading(false);
        }
        setToken(token1);
        setLoading(false);
        

    }
    console.log("Login:"+token);

    return (
        <div className='login'>
            <h1>Iniciar Sesión</h1>
            <div style = {{marginLeft:'30%', marginRight: '30%', marginBottom: '1rem'}}>

            </div>
            <Form onSubmit={Submit}>
            {error && (
                <MessageAdvertencia
                    msg={`Error: ${problemaTexto}`}
                    bgColor="#dc3545"
                />
            )}
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Usuario</Form.Label>
                    <Form.Control type="text" placeholder="Introduzca usuario"
                        onChange={e => setUser(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Introduzca contraseña</Form.Label>
                    <Form.Control type="password" placeholder="Contraseña"
                        onChange={e => setPassword(e.target.value)} />
                </Form.Group>
                {loading && <Loader />}

                <Button className="boton" variant="primary" type="submit">
                    Iniciar Sesión
                </Button>

                <br />

                <Link to="/reguser">¿Todavía no se ha registrado? Pulse aquí</Link>

            </Form>

        </div>
    )
}


export default Login;
