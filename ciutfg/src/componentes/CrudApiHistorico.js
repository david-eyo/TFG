import React, { useState } from "react";
import { helpHttp } from "../helpers/helpHttp";
import CrudTablePrecios from "./CrudTablePrecios";

const CrudApiHistorico = () => {
  const [db, setDb] = useState([]);
  const [error, setError] = useState(null);

  let api = helpHttp();
  let url = "http://localhost:5000/historico";

  const findHistoricoPreciosByIdProducto = (idProducto) => {

    let options = {
      headers: { "content-type": "application/json" },
    };
    let url2 = url + "?idProducto=" + idProducto;
    api.get(url2, options).then((res) => {  
      if (!res.err) {
        setDb(res);
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
        <CrudTablePrecios
        findHistoricoPreciosByIdProducto={findHistoricoPreciosByIdProducto}
        data= {db}
        />
      </article>
    </div>
  );
};

export default CrudApiHistorico;