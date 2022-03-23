import React, { useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import "./BarraPrincipal.css"
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';
import CrudApi from "./CrudApiProductos"
import CrudApiHistorico from "./CrudApiHistorico"
import Inicio from '../pages/Inicio';
import NuestrosProductos from '../pages/NuestrosProductos';
import BuscadorProductos from '../pages/BuscadorProductos';
import Login from '../pages/login/Login';
import BuscadorProductosAdministracion from '../pages/BuscadorProductosAdministracion';
import RegistroUsuarioNormal from '../pages/RegistroUsuarioNormal/RegistroUsuarioNormal';


import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";



export default function BarraPrincipal() {
    const [token, setToken] = useState('');
    console.log('Barra principal: ' + token);
    return (
        <>

            <header className="bg-react">
                <Navbar bg="green" expand="lg" variant='dark' style={{ paddingTop: '1rem', paddingBottom: '1rem' }}>
                    <Navbar.Brand href="#home" className="nombre_empresa" style={{ marginLeft: '2rem' }}>
                        <img alt='logo' src={require('../imagenes/logo.png')} className="logo" />
                        Frutas Río Ulla
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav" >
                        <Nav className="me-auto">

                            <Nav.Link as={Link} to="/" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Inicio</Nav.Link>

                            <Nav.Link as={Link} to="/nuestrosproductos" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Nuestros Productos</Nav.Link>

                            <Nav.Link as={Link} to="/buscadorproductos" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Buscar Productos</Nav.Link>

                            <Nav.Link as={Link} to="/adminbuscadorproductos" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>(A)Buscar y Editar Productos</Nav.Link>

                            <Nav.Link as={Link} to="/adminhistorico" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>(A)Historico Precios</Nav.Link>
                            <Nav.Link as={Link} to="/adminhistorico" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Atención al Cliente</Nav.Link>

                            {!token &&
                                <Nav.Link as={Link} to="/login" className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Login</Nav.Link>
                            }

                            {token &&
                                <Nav.Link className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }} onClick={e => setToken(null)}>Logout</Nav.Link>
                            }


                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </header>

            <Routes>
                <Route path="/productos" element={<CrudApi />} />
                <Route path="/historico" element={<CrudApiHistorico />} />
                <Route path="/" element={<Inicio />} />
                <Route path="/nuestrosproductos" element={<NuestrosProductos />} />
                <Route path="/buscadorProductos" element={<BuscadorProductos />} />
                <Route path="/adminbuscadorproductos" element={<BuscadorProductosAdministracion
                    token={token} />} />
                <Route path="/adminhistorico" element={<CrudApiHistorico />} />
                <Route path="/login" element={<Login token={token} setToken={setToken} />} />
                <Route path="/reguser" element={<RegistroUsuarioNormal />} />

            </Routes>

        </>
    );
}