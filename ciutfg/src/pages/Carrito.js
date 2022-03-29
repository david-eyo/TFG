import React, { useState, useEffect } from "react";
import CrudTableCarrito from "../componentes/CrudTableCarrito";
import Loader from '../componentes/Loader';

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


    function sleep(ms) {
        return new Promise((resolve) => setTimeout(resolve, ms));
    }

    const handleResp = async () => {
        setLoading(true);
        var token1 = null;
        var user1 = null;


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



    


    return (
        <div>
            <article className="grid-1-2">
                <h1>Carrito</h1>
                {loading && <Loader />}
                <CrudTableCarrito data={db}/>
            </article>
        </div>
    );
};

export default Carrito;