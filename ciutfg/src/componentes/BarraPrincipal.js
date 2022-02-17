import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import "./BarraPrincipal.css"
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';

export default function BarraPrincipal() {
    return (
        <>
            <header className= "bg-react">
                <Navbar bg="green" expand="lg" variant='dark'>
                    <Container>
                        <Navbar.Brand href="#home" className="nombre_empresa">
                            <img alt='logo' src={require('../imagenes/logo.png')} className="logo"/>
                            Frutas Río Ulla  
                        </Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="me-auto">
                                <Nav.Link href="#home">Inicio</Nav.Link>
                                <NavDropdown title="Productos" id="basic-nav-dropdown">
                                    <NavDropdown.Item href="#action/3.1">Ofertas</NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.2">Nuestros productos</NavDropdown.Item>
                                </NavDropdown>
                                <Nav.Link href="#link3">At. Cliente</Nav.Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>

            </header>
        </>
    );
}