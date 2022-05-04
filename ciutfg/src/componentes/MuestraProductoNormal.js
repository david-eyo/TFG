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
  image: "",
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
  const [imagen, setImagen] = useState('');

  let { nombre, precio, image, valoracion, numero_valoraciones } = el;
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

  


    const fetchImage = async () => {
      const url = image.replace('8080', '5000');
      const res = await fetch(url);
      const imageBlob = await res.blob();
      const imageObjectURL = URL.createObjectURL(imageBlob);
      setImagen(imageObjectURL);
    };
  


  useEffect(() => {
    setForm(initailForm);
    fetchImage();
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
      <Card className="estiloTarjeta">
        <div className="embed-responsive embed-responsive-16by9">
          <Card.Img variant="top" alt="Foto de Producto" src={imagen} style = {{padding: '1rem 1rem 0rem 1rem', borderRadius: '30px', borderStyle: '5px solid'}}/> 
          
        </div>
        <Card.Body>
          <Card.Title><b><h4>{nombre}</h4></b></Card.Title>
          <Card.Text><b>{precio}€/kg</b></Card.Text>
          <div className="grid" style={estiloGrid}>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            {token &&
            <div className="grid">
              <input style= {{width: '4rem', marginLeft: '10rem', marginBottom: '-2rem'}} type="number" Placeholder = "kgs"
              onChange={(e) => {setCantidad(e.target.value)}}></input>

              <Button variant="outline-success" style={estiloBoton}
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