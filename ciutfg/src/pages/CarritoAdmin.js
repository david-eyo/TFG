import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import CrudFormCarritoAdministracion from "../componentes/CrudFormCarritoAdministracion";
import BuscadorCarritoUsuario from '../componentes/BuscadorCarritoUsuario';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";



function CarritoAdmin({token}) {

  const [db2, setDb2] = useState(null);

  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  let api = helpHttp();
  let url = "http://localhost:5000/carrito";

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

  return (
    <div>
      {loading && <Loader />}
      {error && (
        <Message
          msg={`Error ${error.status}: ${error.statusText}`}
          bgColor="#dc3545"
        />
      )}


      <BuscadorCarritoUsuario
        token={token}
        setError={setError}
        dataToEdit={dataToEdit}
        setDataToEdit={setDataToEdit}
      />
    </div>
  );
}

export default CarritoAdmin;