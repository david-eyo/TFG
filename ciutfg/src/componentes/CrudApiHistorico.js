import React, { useEffect, useState } from "react";
import { helpHttp } from "../helpers/helpHttp";
import CrudTablePrecios from "./CrudTablePrecios";

const CrudApiHistorico = () => {
  const [db, setDb] = useState([]);
  const [db2, setDb2] = useState(null);

  const [error, setError] = useState(null);

  let api = helpHttp();
  let url = "http://localhost:5000/historico";
  var respuesta = null;

  const findHistoricoPreciosByIdProducto = (idProducto) => {

    let options = {
      headers: { "content-type": "application/json" },
    };
    let url2 = url + "?idProducto=" + idProducto;
    api.get(url2, options).then((res) => {  
      if (!res.err) {
        setDb({res});
        setError(null);
      } else {
        setDb([]);
        setError(res);
      }
    });
  };

  console.log(db);


  return (
    <div>
      <article className="grid-1-2">
        <h1>Histórico de precios</h1>
        <CrudTablePrecios
        findHistoricoPreciosByIdProducto={findHistoricoPreciosByIdProducto}
        data= {db}
        />
      </article>
    </div>
  );
};

export default CrudApiHistorico;