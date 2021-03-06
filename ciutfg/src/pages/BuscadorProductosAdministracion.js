import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import CrudForm from "../componentes/CrudForm";
import CrudTable from "../componentes/CrudTable";
import BuscadorProductos from '../componentes/BuscadorProductos';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import {
  Navigate
} from "react-router-dom";


function BuscadorProductosAdministracion({token}) {

  const [db, setDb] = useState(null);
  const [db2, setDb2] = useState(null);

  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [error2, setError2] = useState(null);
  const [loading, setLoading] = useState(false);
  const [log, setLog] = useState(false);
  let api = helpHttp();
  let url = "http://localhost:5000/productos";

  const createData = (data, file) => {

    
    var productostr= JSON.stringify(data);
    var prueba= new FormData();
    prueba.set('productostr', productostr);
    if (file){
      prueba.set('file', file);
    }
    

    const response =  fetch("http://localhost:5000/productos/prueba", {
      method: 'POST',
      headers: {
                "Authorization": "Bearer "+token },
      body: prueba
    });

    if (!response.err) {
      console.log(response);
      setDb([...db, response]);
    } else {
      setError(response);
    }

  };

  const createData2 = async (data, file) => {

    var productostr= JSON.stringify(data);
    var prueba= new FormData();
    prueba.set('productostr', productostr);
    if (file){
      prueba.set('file', file);
    }

    await fetch("http://localhost:5000/productos/prueba", {
        method: 'POST',
        headers: {
                    "Authorization": "Bearer "+token},
                    body: prueba  
    }
   ).then((res) =>
        res.ok
            ? res.json()
            : Promise.reject({
                err: true,
                status: res.status || "00",
                statusText: res.statusText || "Ocurrió un error",
            })
    ).catch((err) => setError(err));
    if (error){
      console.log(error);
    } else{
        window.confirm("Producto guardado correctamente");
    }
    

}




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

  const updateData3 = (data) => {
    let endpoint = `${url}/${data.id}`;
    let options = {
      body: data,
      headers: { "content-type": "application/json",
      "Authorization": "Bearer "+token},
    };

    api.put(endpoint, options).then((res) =>
        res.json()
        ).catch((err) => setError2(err));
        if (error){
          console.log(error);
          setError2(error);
        } else{
          window.confirm("Producto actualizado correctamente");
          let newData = db.map((el) => (el.id === data.id ? data : el));
          setDb(newData);   
          setLog(true); 
          <Navigate to="/" ></Navigate>
      }
  };

  const updateData2 = async (data) => {

    let endpoint = `${url}/${data.id}`;
    let options = {
      method: 'PUT',
      body: data,
      headers: { "content-type": "application/json",
      "Authorization": "Bearer "+token},
    };
    await fetch( endpoint, options
   ).then((res) =>
        res.ok
            ? res.json()
            : Promise.reject({
                err: true,
                status: res.status || "00",
                statusText: res.statusText || "Ocurrió un error",
            })
    ).catch((err) => setError(err));
    if (error){
      console.log(error);
    } else{
      let newData = db.map((el) => (el.id === data.id ? data : el));
      setDb(newData);
      window.confirm("Producto guardado correctamente");
    }
    

}

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
        createData={createData2}
        updateData={updateData3}
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