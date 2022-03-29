import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import BuscadorUsuario from '../componentes/BuscadorUsuario';
import CrudFormUsuarioAdministracion from '../componentes/CrudFormUsuarioAdministración';



function BuscadorUsuarios({token}) {

  const [db2, setDb2] = useState(null);

  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  let api = helpHttp();
  let url = "http://localhost:5000/secure/auth";


  const findUsuarioByUsername = (nombre) => {
    let options = {
      headers: { "content-type": "application/json",
                "Authorization": "Bearer "+token },
    };

    let url2 = url + "?username=" + nombre;

    api.get(url2, options).then((res) => {
      if (!res.err) {
        setDb2(res);
        setError(null);
      } else {
        setDb2([])
        setError(res);
      }
    });
    return db2;
  };

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
        let newData = db2.map((el) => (el.id === data.id ? data : el));
        setDb2(newData);
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

  console.log(dataToEdit);
  return (
    <div>
      {loading && <Loader />}
      {error && (
        <Message
          msg={`Error ${error.status}: ${error.statusText}`}
          bgColor="#dc3545"
        />
      )}
      <BuscadorUsuario
        findUsuarioByUsername={findUsuarioByUsername}
        setDataToEdit={setDataToEdit}
        deleteUsuario={deleteUsuario}
      />
      {dataToEdit && (
              <CrudFormUsuarioAdministracion
              updateUsuario={updateUsuario}
              dataToEdit={dataToEdit}
              setDataToEdit={setDataToEdit}
            />
        )}

    </div>
  );
}

export default BuscadorUsuarios;