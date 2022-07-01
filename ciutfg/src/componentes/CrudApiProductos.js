import React, { useEffect, useState } from "react";
import { helpHttp } from "../helpers/helpHttp";
import CrudForm from "./CrudForm";
import CrudTable from "./CrudTable";
import Loader from "./Loader";
import Message from "./Message";
import MuestraTodosProductosNormales from "./MuestraTodosProductosNormales";
import BuscadorProductos from "./BuscadorProductos";
import BuscadorProductosNormales from "./BuscadorProductosNormales";
import MuestraTodosProductosOferta from "./MuestraTodosProductosOferta";
import MuestraTodosProductosNuestrosProductos from "./MuestraTodosProductosNuestrosProductos"

const CrudApi = () => {
  const [db, setDb] = useState(null);
  const [db2, setDb2] = useState(null);
  const [db3, setDb3] = useState([]);
  const [db4, setDb4] = useState([]);
  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [add, setAdd] = useState(false);


  let api = helpHttp();
  let url = "http://localhost:5000/productos";

  useEffect(() => {
    setLoading(true);
    helpHttp()
      .get(url)
      .then((res) => {
        //console.log(res);
        if (!res.err) {
          setDb(res);
          setError(null);
        } else {
          setDb(null);
          setError(res);
        }
        setLoading(false);
      });
  }, [url]);

  const createData = (data) => {

    let options = {
      body: data,
      headers: { "content-type": "application/json" },
    };

    api.post(url, options).then((res) => {
      //console.log(res);
      if (!res.err) {
        setDb([...db, res]);
        console.log('llega');
        setAdd(true);
      } else {
        setError(res);
        console.log('llega2');
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


  const getAllProductsOferta = () => {

    let options = {
      headers: { "content-type": "application/json" },
    };

    let urlOferta = "http://localhost:5000/productos?oferta=true";
    api.get(urlOferta, options).then((res) => {
      if (!res.err) {
        setDb3(res);
        setError(null);
      } else {
        setDb3([]);
        setError(res);
      }
    });

    return db3;
  };

  const getAllProductsNuestrosProductos = () => {

    let options = {
      headers: { "content-type": "application/json" },
    };

    let urlOferta = "http://localhost:5000/productos?nuestros_productos=true";
    api.get(urlOferta, options).then((res) => {
      if (!res.err) {
        setDb4(res);
        setError(null);
      } else {
        setDb4([]);
        setError(res);
      }
    });

    return db3;
  };

  const updateData = (data) => {
    let endpoint = `${url}/${data.id}`;
    let options = {
      body: data,
      headers: { "content-type": "application/json" },
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

  const rateProduct = (data) => {
    let endpoint = `${url}/${data.id}`;
    let options = {
      body: data,
      headers: { "content-type": "application/json" },
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
      <article className="grid-1-2">
        {loading && <Loader />}
        {add &&
          <h1>Producto añadido correctamente</h1>
        }
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
        {db && (
          <MuestraTodosProductosNormales
            data={db}
            rateProduct={rateProduct}
            dataToEdit={dataToEdit}
            setDataToEdit={setDataToEdit}
          />
        )}
        <MuestraTodosProductosOferta
          data={db3}
          getAllProductsOferta={getAllProductsOferta}
          rateProduct={rateProduct}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
        <MuestraTodosProductosNuestrosProductos
          data={db4}
          getAllProductsNuestrosProductos={getAllProductsNuestrosProductos}
          rateProduct={rateProduct}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
        <BuscadorProductosNormales
          findDataByName={findDataByName}
          updateData={updateData}
          setDataToEdit={setDataToEdit}
        />
        {loading && <Loader />}
        {error && (
          <Message
            msg={`Error ${error.status}: ${error.statusText}`}
            bgColor="#dc3545"
          />
        )}
      </article>
    </div>
  );
};

export default CrudApi;