import React, { useState} from 'react';
import MuestraTodosProductosNuestrosProductos from '../componentes/MuestraTodosProductosNuestrosProductos';
import { helpHttp } from "../helpers/helpHttp";
import Loader from '../componentes/Loader';
import Message from "../componentes/Message";



function NuestrosProductos() {

    const [db, setDb] = useState(null);
    const [db4, setDb4] = useState([]);
    const [dataToEdit, setDataToEdit] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    let api = helpHttp();
    let url = "http://localhost:5000/productos";


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

        return db4;
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
                <MuestraTodosProductosNuestrosProductos
                    data={db4}
                    getAllProductsNuestrosProductos={getAllProductsNuestrosProductos}
                    rateProduct={rateProduct}
                    dataToEdit={dataToEdit}
                    setDataToEdit={setDataToEdit}
                />
            </article>
        </div>
    );
}

export default NuestrosProductos;