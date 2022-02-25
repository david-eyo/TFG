import React, { useState, useEffect} from "react";
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

const MuestraProductoNormal = ({el, rateProduct, dataToEdit, setDataToEdit }) => {
  const [form, setForm] = useState(initailForm);

  let { nombre, precio, valoracion} = el;
  const estiloBoton = {
    marginBottom: '-4rem',
    marginLeft: '9rem'
  }

  const estiloGrid = {
    marginTop: '-1rem'
  }

  useEffect(() => {
      setForm(initailForm);
    }, [dataToEdit]);


  const handleValoracion = (valoracion) => {
    form.id=el.id;
    form.nombre= el.nombre;
    form.precio= el.precio;
    form.cantidad= el.cantidad;
    form.valoracion=valoracion;
    rateProduct(form);

  };

  return (
    <div className="Tarjeta">
      <Card style={{ width: '16rem', borderRadius: '20px', backgroundColor: 'rgb(157, 255, 161)' }}>
        <div className="embed-responsive embed-responsive-16by9">
          <Card.Img variant="top" alt = "Foto de Producto" src='../imagenes/logo.png'/>
        </div>  
        <Card.Body>
          <Card.Title>{nombre}</Card.Title>
          <Card.Text>{precio}€</Card.Text>
          <div className="grid" style = {estiloGrid}>
            <Button variant="primary" className="btn btn-warning" style={estiloBoton}>Carrito</Button>
            <ReactStars
              size={27}
              value = {valoracion}
              onChange= {handleValoracion}        
            />
          </div>
        </Card.Body>
      </Card>
    </div>

    

  );
};

export default MuestraProductoNormal;