import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import CrudForm from "../componentes/CrudForm";
import CrudTable from "../componentes/CrudTable";
import BuscadorProductos from '../componentes/BuscadorProductos';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";



function BuscadorProductosAdministracion({token}) {

  const [db, setDb] = useState(null);
  const [db2, setDb2] = useState(null);

  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  let api = helpHttp();
  let url = "http://localhost:5000/productos";

  const createData = (data) => {

    let options = {
      body: data,
      headers: { "content-type": "application/json",
                "Authorization": "Bearer "+token },
    };

    api.post(url, options).then((res) => {
      //console.log(res);
      if (!res.err) {
        console.log(res);
        setDb([...db, res]);
      } else {
        setError(res);
      }
    });
  };

  const findDataByName = (nombre) => {

    let options = {
      headers: { "content-type": "application/json" },
    };

    let url2 = url + "?nombre=" + nombre;
    api.get(url2, options).then((res) => {
      if (!res.err) {
        setDb2(res);
        setError(null);
      } else {
        setDb2([]);
        setError(res);
      }
    });

    return db2;
  };

  const updateData = (data) => {
    let endpoint = `${url}/${data.id}`;
    let options = {
      body: data,
      headers: { "content-type": "application/json",
      "Authorization": "Bearer "+token},
    };

    api.put(endpoint, options).then((res) => {
      //console.log(res);
      if (!res.err) {
        let newData = db.map((el) => (el.id === data.id ? data : el));
        setDb(newData);
      } else {
        setError(res);
      }
    });
  };

  const deleteData = (id) => {
    let isDelete = window.confirm(
      `¿Estás seguro de eliminar el registro con el id '${id}'?`
    );

    if (isDelete) {
      let endpoint = `${url}/${id}`;
      let options = {
        headers: { "content-type": "application/json" },
      };

      api.del(endpoint, options).then((res) => {
        //console.log(res);
        if (!res.err) {
          let newData = db.filter((el) => el.id !== id);
          setDb(newData);
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
      <CrudForm
        createData={createData}
        updateData={updateData}
        dataToEdit={dataToEdit}
        setDataToEdit={setDataToEdit}
      />
      {db && (
        <CrudTable
          data={db}
          setDataToEdit={setDataToEdit}
          deleteData={deleteData}
        />
      )}
      <BuscadorProductos
        findDataByName={findDataByName}
        setDataToEdit={setDataToEdit}
      />
    </div>
  );
}

export default BuscadorProductosAdministracion;