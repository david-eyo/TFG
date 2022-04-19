import React, {useState, useEffect} from 'react';
import CrudTablePedido from '../componentes/CrudTablePedido';
import Message from '../componentes/Message';
import Loader from '../componentes/Loader';
import CrudTableItemPedido from '../componentes/CrudTableItemPedido';
import { Button } from 'react-bootstrap';



function MisPedidos({token, username}) {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState('');
    const [db, setDb] = useState(null);

    const handleResp = async () => {
        setLoading(true);


        let resp = await fetch('http://localhost:5000/pedidos?username='+username, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+token },
        }).then((res) =>
            res.ok
                ? res.json()
                : Promise.reject({
                    err: true,
                    status: res.status || "00",
                    statusText: res.statusText || "Ocurrió un error",
                })
        ).catch((err) => setError(err));

        setLoading(false);

        setDb(resp);
   
      };
      
    useEffect(() => {

          handleResp().catch(console.error);


    },[])

    const [detalle, setDetalle] = useState();
    const [detalleUsuario, setDetalleUsuario] = useState();


    return (
        <div>
            { detalle &&
              <div className="detalleProducto" style = {{marginBottom: '2rem', marginTop: '2rem'}}>
                <h4 style ={{float: 'left'}}><i>Detalle del pedido</i></h4>
                  <br></br>
                  <br></br>
                  <p><b>Id del pedido:</b> {detalle.id}</p>
                  <p><b>Fecha del pedido:</b> {detalle.fechaPedido.substring(0,10)}</p>
                  <p><b>Estado del pedido:</b> {detalle.estado}</p>

                <CrudTableItemPedido 
                  data={detalle.item_pedido}
                />
                <br></br>

                <Button variant="danger" onClick = { () => setDetalle()}>Quitar detalle total <i className="fa fa-close"/></Button>
              </div>
            }
            <br></br>
            <h3 style={{float: 'left', marginLeft: '2rem'}}><i><b>Lista de Mis Pedidos</b></i></h3>
            <br></br>
            <br></br>
            {error && (
                    <Message
                        msg={`Error ${error.status}: ${error.statusText}`}
                        bgColor="#dc3545"
                    />
            )}
            {loading && <Loader />}
            {db &&
                <CrudTablePedido 
                data={db}
                setDetalle= {setDetalle}
                />
            }

        </div>
    );
}

export default MisPedidos;