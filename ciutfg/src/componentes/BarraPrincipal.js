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
import Carrito from '../pages/Carrito';
import CarritoAdmin from '../pages/CarritoAdmin';
import BuscadorUsuarios from '../pages/BuscadorUsuarios';
import PerfilUsuario from '../pages/PerfilUsuario';
import MisPedidos from '../pages/MisPedidos';
import BuscadorPedidos from '../pages/BuscadorPedidos';
import BuscadorPedidosUsuario from '../pages/BuscadorPedidosUsuario';



export default function BarraPrincipal() {
    const [token, setToken] = useState('');
    const [rol, setRol] = useState('');
    const [username, setUsername] = useState('');

    const logout = (e) => {
        setToken(null);
        setRol(null);
        setUsername(null);
    }
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




                            {rol === "ROLE_ADMIN" &&
                             <NavDropdown title="Administración" id="navbarScrollingDropdown" style={{ marginLeft: '2rem', fontSize: 'larger' }}>
                                    <NavDropdown.Item as={Link} to="/adminbuscadorproductos" className={isActive =>
                                        "nav-link" + (!isActive ? " unselected" : "")
                                    } >Buscar y Editar Productos</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/adminhistorico" className={isActive =>
                                        "nav-link" + (!isActive ? " unselected" : "")
                                    } >(A)Historico Precios</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/buscausuario" className={isActive =>
                                        "nav-link" + (!isActive ? " unselected" : "")
                                    } >Buscar Usuario</NavDropdown.Item>
                                    <NavDropdown.Item as={Link} to="/buscacarritodeusuario" className={isActive =>
                                        "nav-link" + (!isActive ? " unselected" : "")
                                    } >Buscar Carrito Usuario</NavDropdown.Item>
                                </NavDropdown>   
                            }

                            <Nav.Link as={Link} to="/adminhistorico" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Atención al Cliente</Nav.Link>

                            {rol === "ROLE_USER" &&

                                <Nav.Link as={Link} to="/carrito" className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Carrito</Nav.Link>
                            }

                            {rol === "ROLE_USER" &&
                            
                            <NavDropdown title="Mis Pedidos" id="navbarScrollingDropdown" style={{ marginLeft: '2rem', fontSize: 'larger' }}>
                                <NavDropdown.Item as={Link} to="/mispedidos" className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } >Mis Pedidos</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/buscadorpedidosusuario" className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } >Buscar Pedidos Fechas</NavDropdown.Item>
                            </NavDropdown>
                            }

                            {rol === "ROLE_ADMIN" &&
                                <Nav.Link as={Link} to="/buscadorpedidos" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Pedidos</Nav.Link>
                            }
                            {!token &&
                                <Nav.Link as={Link} to="/login" className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Login</Nav.Link>
                            }
                            {token &&
                            <Nav.Link as={Link} to="/miperfil" className={isActive =>
                                "nav-link" + (!isActive ? " unselected" : "")
                            } style={{ marginLeft: '2rem', fontSize: 'larger' }}>Mi Perfil</Nav.Link>}

                            {token &&
                                <Nav.Link className={isActive =>
                                    "nav-link" + (!isActive ? " unselected" : "")
                                } style={{ marginLeft: '2rem', fontSize: 'larger' }} onClick={e => logout()}>Logout</Nav.Link>
                            }


                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </header>



            <Routes>
                <Route path="/productos" element={<CrudApi />} />
                <Route path="/historico" element={<CrudApiHistorico />} />
                <Route path="/" element={<Inicio token={token} />} />
                <Route path="/nuestrosproductos" element={<NuestrosProductos token={token} />} />
                <Route path="/buscadorProductos" element={<BuscadorProductos token={token} />} />
                <Route path="/adminbuscadorproductos" element={<BuscadorProductosAdministracion
                    token={token} />} />
                <Route path="/adminhistorico" element={<CrudApiHistorico />} />
                <Route path="/login" element={<Login token={token} setToken={setToken} setRol={setRol} setUsername= {setUsername} />} />
                <Route path="/reguser" element={<RegistroUsuarioNormal />} />
                <Route path="/carrito" element={<Carrito token={token} />} />
                <Route path="/buscacarritodeusuario" element={<CarritoAdmin token={token} />} />
                <Route path="/buscausuario" element={<BuscadorUsuarios token={token} />} />
                <Route path="/miperfil" element={<PerfilUsuario token={token} />} />
                <Route path="/mispedidos" element={<MisPedidos token={token} username={username} />} />
                <Route path="/buscadorpedidos" element={<BuscadorPedidos token={token} username={username} />} />
                <Route path="/buscadorpedidosusuario" element={<BuscadorPedidosUsuario token={token} username={username} />} />

            </Routes>

        </>
    );
}