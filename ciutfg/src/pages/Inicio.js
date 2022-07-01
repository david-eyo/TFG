import React, { useState } from 'react';
import MuestraTodosProductosOferta from '../componentes/MuestraTodosProductosOferta';
import { helpHttp } from "../helpers/helpHttp";
import Loader from '../componentes/Loader';
import Message from "../componentes/Message";
import Pagination from "react-bootstrap/Pagination";
import MyPagination from '../componentes/MyPagination';
import MessageAdvertencia from '../componentes/MessageAdvertencia';


function Inicio({token, username}) {
    const [db, setDb] = useState(null);
    const [db3, setDb3] = useState([]);
    const [dataToEdit, setDataToEdit] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    let api = helpHttp();
    let url = "http://localhost:5000/productos";



    const getAllProductsOferta = () => {

        let options = {
            headers: { "content-type": "application/json" },
        };
        var integer = ''+(page-1);

        let urlOferta = "http://localhost:5000/productos?page="+integer+"&size=5";
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

    const rateProduct = (data) => {
        console.log(data);
        let endpoint = `${url}/${data.id}`;
        let options = {
            body: data,
            headers: { "content-type": "application/json",
                       'Authorization': 'Bearer '+token },
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
                
                <MuestraTodosProductosOferta
                    data={db3}
                    getAllProductsOferta={getAllProductsOferta}
                    rateProduct={rateProduct}
                    dataToEdit={dataToEdit}
                    setDataToEdit={setDataToEdit}
                    token={token}
                />
            </article>

            <MyPagination totPages={10} currentPage={page} pageClicked={(ele) => {setPage(ele)}}>

            </MyPagination>

        </div>
    );



}

export default Inicio;