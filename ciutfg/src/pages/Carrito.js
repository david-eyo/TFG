import React, { useState, useEffect } from "react";
import CrudFormCarritoAdministracion from "../componentes/CrudFormCarritoAdministracion";
import CrudTableCarrito from "../componentes/CrudTableCarrito";
import Loader from '../componentes/Loader';
import { helpHttp } from "../helpers/helpHttp";
import Message from "../componentes/Message";

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



    


    return (
        <div>
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

            </article>
        </div>
    );
};

export default Carrito;