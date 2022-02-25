import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import CrudTableRow2 from "./CrudTableRow2";
import { Form, FormControl, Button } from 'react-bootstrap';
import MuestraProductoNormal from './MuestraProductoNormal';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorProductosNormales({ findDataByName, setDataToEdit }) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);


    const handleChange = e => {
        setBusqueda(e.target.value);
        var aux = e.target.value;

        setData(findDataByName(e.target.value));
        if (data != null) {
            setData2(data);
        }
    }

    return (
        <div>
            <hr/>
            <h3>Buscador Productos(Normal)</h3>
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
                    <Button variant="outline-success" onClick={() => findDataByName(busqueda)}>Buscar</Button>
                </Form>
            </div>
            {data2.length > 0 ? (
                data2.map((el) => (
                    <MuestraProductoNormal
                        key={el.id}
                        precio={el.precio}
                        el={el}
                    />
                ))
            ) : (
                <tr>
                    <td colSpan="3">Producto no encontrado</td>
                </tr>
            )}
        </div>
    );
}


