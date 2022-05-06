import React, { useState, useEffect } from "react";
import CrudFormCarritoAdministracion from "../componentes/CrudFormCarritoAdministracion";
import CrudTableCarrito from "../componentes/CrudTableCarrito";
import Loader from '../componentes/Loader';
import { helpHttp } from "../helpers/helpHttp";
import Message from "../componentes/Message";


import {
  BrowserRouter as Router,
  Link
} from "react-router-dom";
import PayPal from "../componentes/PayPal";

const initailForm = {
    cantidad: "",
    id: null,
    productos: null,
    usuario: null,
  };

const Carrito = ({token}) => {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState('');
    const [db, setDb] = useState(initailForm);
    const [dataToEdit, setDataToEdit] = useState(null);
    const [db2, setDb2] = useState(null);
    const [cantidad, setCantidad] = useState(0);


    let api = helpHttp();
    let url = "http://localhost:5000/carrito";

    const deleteData = (id) => {
        let isDelete = window.confirm(
          `¿Estás seguro de eliminar el producto del carrito con el id '${id}'?`
        );
    
        if (isDelete) {
          let endpoint = `${url}/${id}`;
          let options = {
            headers: { "content-type": "application/json",
                        "Authorization": "Bearer "+token},
          };
    
          api.del(endpoint, options).then((res) => {
            //console.log(res);
            if (!res.err) {
              let newData = db2.filter((el) => el.id !== id);
              setDb2(newData);
            } else {
              setError(res);
            }
          });
        } else {
          return;
        }
      };

      const updateCarrito = (data) => {
        let endpoint = `${url}/${data.id}`;
    
        let options = {
          body: data,
          headers: { "content-type": "application/json",
          "Authorization": "Bearer "+token},
        };
    
        api.put(endpoint, options).then((res) => {
          //console.log(res);
          if (!res.err) {
            let newData = db2.map((el) => (el.id === data.id ? data : el));
            setDb2(newData);
          } else {
            setError(res);
          }
        });
        
      };

      const realizarPedido = () => {
          let endpoint = `http://localhost:5000/pedidos`;
    
          let options = {
            headers: { "content-type": "application/json",
            "Authorization": "Bearer "+token},
          };
      
          api.post(endpoint, options).then((res) => {
            //console.log(res);
            if (res.err) {
              setError(res);
            }
          });
      };

    const handleResp = async () => {
        setLoading(true);


        let resp = await fetch('http://localhost:5000/carrito', {
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+token },
        }).then((res) =>
            res.ok
                ? res.json()
                : Promise.reject({
                    err: true,
                    status: res.status || "00",
                    statusText: res.statusText || "Ocurrió un error",
                })
        ).catch((err) => setError(err));

        setLoading(false);

        setDb(resp);
   
      };
      
    useEffect(() => {

          handleResp().catch(console.error);


    },[])


    const [pasado, setPasado]= useState(false);
    var prueba = 0;

    if(!pasado){
      for (var i = 0; i < db.length; i++) {
        prueba= (db[i].cantidad*db[i].productos.precio)+prueba;
        setCantidad(prueba); 
        if (i == db.length-1){
          setPasado(true);
        }       
      }
    }

    

    const [compra, setCompra] = useState(false);

    

    return (
        <div>
          <br></br>
          <h3 style={{float: 'left', marginLeft: '2rem'}}><b><i>Mi Carrito de la compra</i></b></h3>
          <br></br>
          <br></br>
            <article className="grid-1-2">
                {error && (
                    <Message
                        msg={`Error ${error.status}: ${error.statusText}`}
                        bgColor="#dc3545"
                    />
                )}
                {loading && <Loader />}
                {dataToEdit && 
                                <CrudFormCarritoAdministracion
                                updateCarrito={updateCarrito}
                                dataToEdit={dataToEdit}
                                setDataToEdit={setDataToEdit}
                                 />
                }

                <CrudTableCarrito 
                data={db}
                setDataToEdit={setDataToEdit}
                deleteData={deleteData}
                />
              
            <br></br>
            <br></br>

            <h4 style = {{float: 'right', marginRight: '5rem'}}><b>Total: </b>{cantidad}€</h4>

            <br></br>
            <br></br>

            {compra ? (
              <PayPal 
              cantidad = {cantidad}
              realizarPedido= {realizarPedido}
              setError= {setError}
              />
            ) : (
              <button style = {{float: 'right', marginRight: '5rem'}} className="btn btn-warning" onClick={() => setCompra(true)}>
                Realizar compra<i style={{marginLeft: '1rem'}} className="fa fa-exchange"/>
              </button>
            )




            }

            </article>
        </div>
    );
};

export default Carrito;