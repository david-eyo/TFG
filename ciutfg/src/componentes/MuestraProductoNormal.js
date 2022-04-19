import React, { useState, useEffect } from "react";
import { Card } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import './MuestraProductoNormal.css';
import ReactStars from 'react-rating-stars-component';
import Message from "./Message";


const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: false,
  oferta: false,
  id: null,
  valoracion: null,
};

const MuestraProductoNormal = ({ el, rateProduct, dataToEdit, setDataToEdit, token }) => {
  const [form, setForm] = useState(initailForm);
  const [cantidad, setCantidad] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [problemaTexto, setProblemaTexto] = useState('');

  let { nombre, precio, valoracion, numero_valoraciones } = el;
  const estiloBoton = {
    marginBottom: '-4rem',
    marginLeft: '10rem',
    marginTop: '0rem'
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

  const anadirCarrito = async (e) => {
    e.preventDefault();
    setLoading(true);

    let error1;


    let resp= await fetch('http://localhost:5000/carrito', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+token },
        body: JSON.stringify({    
            productId: el.id,
            cantidad
        
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

    if(error){
      if (error.status === 409){
        setProblemaTexto("El producto ya ha sido añadido con anterioridad");
      }else{
        setProblemaTexto("La cantidad requerida excede a la cantidad existente del producto");
      }
    }
}


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
            {token &&
            <div className="grid">
              <input style= {{width: '4rem', marginLeft: '10rem', marginBottom: '-2rem'}} type="number" Placeholder = "kgs"
              onChange={(e) => {setCantidad(e.target.value)}}></input>

              <Button variant="primary" className="btn btn-warning" style={estiloBoton}
              onClick={anadirCarrito}><i style={estiloCarrito} className="fa fa-cart-arrow-down" /></Button>
            </div>
            }
            <div className="grid">
              <ReactStars
                size={27}
                value={valoracion}
                onChange={handleValoracion}
              />
              <a style={estiloNumVal}>({numero_valoraciones})</a>
            </div>
          </div>
          {error && (
          <Message
            msg={`Error: ${problemaTexto}`}
            bgColor="#dc3545"
          />
        )}
        </Card.Body>
      </Card>
    </div>



  );
};

export default MuestraProductoNormal;