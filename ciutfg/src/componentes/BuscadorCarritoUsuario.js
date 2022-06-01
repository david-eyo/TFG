import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { Form, FormControl, Button } from 'react-bootstrap';
import CrudTableRowCarrito from './CrudTableRowCarrito';
import { helpHttp } from '../helpers/helpHttp';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorCarritoUsuario({ setDataToEdit, deleteData, token, setError, setFormulario}) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);

    let api = helpHttp();
    let url = "http://localhost:5000/carrito";
  
  
    const findCarritoByUsername = (nombre) => {
      let options = {
        headers: { "content-type": "application/json",
                  "Authorization": "Bearer "+token },
      };
  
      let url2 = url + "?username=" + nombre;
  
      api.get(url2, options).then((res) => {
        if (!res.err) {
          setData(res);
          setError(null);
        } else {
          setData([])
          setError(res);
  
        }
      });
    };


    const handleChange = e => {

        setBusqueda(e.target.value);
        findCarritoByUsername(e.target.value);

    }

    return (
        <div>
            <div style={style}>
                <Form className="d-flex">
                    <FormControl
                        type="search"
                        placeholder="Buscar"
                        className="me-2"
                        aria-label="Search"
                        value={busqueda}
                        onChange={handleChange}
                    />
                    <Button variant="outline-success" onClick={() => findCarritoByUsername(busqueda)}>Buscar</Button>
                </Form>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    {data && data.length > 0 ? (
                        data.map((el) => (
                            <CrudTableRowCarrito
                                el={el}
                                setDataToEdit={setDataToEdit}
                                deleteData={deleteData}
                                setFormulario={setFormulario}
                            />
                        ))
                    ) : (
                        <tr>
                            <td colSpan="3">Sin datos</td>
                        </tr>
                    )}
                </tbody>
            </table>

        </div>
    );
}

