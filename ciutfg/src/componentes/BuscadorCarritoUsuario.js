import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { Form, FormControl, Button } from 'react-bootstrap';
import CrudTableRowCarrito from './CrudTableRowCarrito';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorCarritoUsuario({ findCarritoByUsername, setDataToEdit, deleteData}) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);


    const handleChange = e => {
        setBusqueda(e.target.value);
        var aux = e.target.value;

        let prueba = findCarritoByUsername(e.target.value);
        setData(findCarritoByUsername(e.target.value));
        if (data != null) {
            setData2(data);
        }
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
                    {data2.length > 0 ? (
                        data2.map((el) => (
                            <CrudTableRowCarrito
                                el={el}
                                setDataToEdit={setDataToEdit}
                                deleteData={deleteData}
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

