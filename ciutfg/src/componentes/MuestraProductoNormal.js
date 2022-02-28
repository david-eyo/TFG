import React, { useState, useEffect } from "react";
import { Card } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import './MuestraProductoNormal.css';
import ReactStars from 'react-rating-stars-component';


const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: false,
  oferta: false,
  id: null,
  valoracion: null,
};

const MuestraProductoNormal = ({ el, rateProduct, dataToEdit, setDataToEdit }) => {
  const [form, setForm] = useState(initailForm);

  let { nombre, precio, valoracion, numero_valoraciones } = el;
  const estiloBoton = {
    marginBottom: '-6rem',
    marginLeft: '10rem'
  }

  const estiloGrid = {
    marginTop: '-1rem'
  }

  const estiloNumVal = {
    paddingTop: '20rem'
  }

  const estiloCarrito = {
    fontSize: '38px',
  }

  useEffect(() => {
    setForm(initailForm);
  }, [dataToEdit]);


  const handleValoracion = (valoracion) => {
    form.id = el.id;
    form.nombre = el.nombre;
    form.precio = el.precio;
    form.cantidad = el.cantidad;
    form.valoracion = valoracion;
    rateProduct(form);

  };

  return (
    <div className="Tarjeta">
      <Card style={{ width: '16rem', borderRadius: '20px', backgroundColor: 'rgb(157, 255, 161)' }}>
        <div className="embed-responsive embed-responsive-16by9">
          <Card.Img variant="top" alt="Foto de Producto" src='../imagenes/logo.png' />
        </div>
        <Card.Body>
          <Card.Title>{nombre}</Card.Title>
          <Card.Text>{precio}€</Card.Text>
          <div className="grid" style={estiloGrid}>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            <Button variant="primary" className="btn btn-warning" style={estiloBoton}><i style={estiloCarrito} className="fa fa-cart-arrow-down" /></Button>
            <div className="grid">
              <ReactStars
                size={27}
                value={valoracion}
                onChange={handleValoracion}
              />
              <a style={estiloNumVal}>({numero_valoraciones})</a>
            </div>
          </div>
        </Card.Body>
      </Card>
    </div>



  );
};

export default MuestraProductoNormal;