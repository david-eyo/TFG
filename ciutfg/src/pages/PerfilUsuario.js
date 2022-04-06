import React, { useState, useEffect } from "react";
import CrudFormUsuarioAdministracion from "../componentes/CrudFormUsuarioAdministración";
import Loader from '../componentes/Loader';
import PerfilUsuarioComponent from "../componentes/PerfilUsuarioComponent";
import { helpHttp } from "../helpers/helpHttp";
import "../componentes/PerfilUsuario.css"


const initailForm = {
    cantidad: "",
    id: null,
    productos: null,
    usuario: null,
};



const PerfilUsuario = ({ token }) => {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState('');
    const [db, setDb] = useState(null);
    const [dataToEdit, setDataToEdit] = useState(null);

    const handleResp = async () => {
        setLoading(true);
        var token1 = null;
        var user1 = null;


        let resp = await fetch('http://localhost:5000/secure/auth?username=', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
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


    }, [])

    let api = helpHttp();
    let url = "http://localhost:5000/secure/auth";

  const updateUsuario = (data) => {
    let endpoint = `${url}/user/${data.id}`;

    let options = {
      body: data,
      headers: { "content-type": "application/json",
      "Authorization": "Bearer "+token},
    };

    api.put(endpoint, options).then((res) => {
      //console.log(res);
      if (!res.err) {
        //let newData = db.map((el) => (el.id === data.id ? data : el));
        setDb(data);
      } else {
        setError(res);
      }
    });
    
  };

  const deleteUsuario = (id) => {
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
          //let newData = db2.filter((el) => el.id !== id);
          setDb(null);
        } else {
          setError(res);
        }
      });
    } else {
      return;
    }
  };






    return (
        <div>
            <article className="grid-1-2">
                {loading && <Loader />}
                <div className= "prueba">
                {db &&
                    <PerfilUsuarioComponent
                        data={db}
                        setDataToEdit={setDataToEdit}
                        deleteUsuario={deleteUsuario}
                    />
                }
                </div>
                <div className = "prueba">
                {dataToEdit && (
                    <CrudFormUsuarioAdministracion
                        updateUsuario={updateUsuario}
                        dataToEdit={dataToEdit}
                        setDataToEdit={setDataToEdit}
                    />
                )}
                </div>


            </article>
        </div>
    );
};

export default PerfilUsuario;