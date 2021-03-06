import React, { useState } from 'react';
import { helpHttp } from "../helpers/helpHttp";
import Loader from '../componentes/Loader';
import BuscadorProductosNormales from '../componentes/BuscadorProductosNormales';
import Message from "../componentes/Message";



function BuscadorProductos({token}) {

    const [db, setDb] = useState(null);
    const [db2, setDb2] = useState([]);
    const [dataToEdit, setDataToEdit] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    let api = helpHttp();
    let url = "http://localhost:5000/productos";

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


    return (
        <div>
            <article className="grid-1-2">
                {loading && <Loader />}
                {error && (
                    <Message
                        msg={`Error ${error.status}: ${error.statusText}`}
                        bgColor="#dc3545"
                    />
                )}
                <BuscadorProductosNormales
                    findDataByName={findDataByName}
                    updateData={updateData}
                    setDataToEdit={setDataToEdit}
                    token={token}
                />

            </article>
        </div>
    );
}

export default BuscadorProductos;