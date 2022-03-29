import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { Form, FormControl, Button } from 'react-bootstrap';
import CrudTableRowUsuario from './CrudTableRowUsuario';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorUsuario({ findUsuarioByUsername, setDataToEdit, deleteUsuario }) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);
    let prueba = [];


    const handleChange = e => {
        setBusqueda(e.target.value);

        setData(findUsuarioByUsername(e.target.value));
        if (data.user == null) {
            setData2([]);
        }else {
            setData2(data);
        }
    }
    console.log(data2);


    
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
                    <Button variant="outline-success" onClick={() => findUsuarioByUsername(busqueda)}>Buscar</Button>
                </Form>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Email</th>
                        <th>Ciudad</th>
                        <th>Cp</th>
                        <th>Direccion</th>
                        <th>Fecha Nacimiento</th>
                        <th>Tlf</th>
                    </tr>
                </thead>
                <tbody>
                    {data2.length ===undefined ? (                                                 
                            <CrudTableRowUsuario
                                el={data2.user}
                                setDataToEdit={setDataToEdit}
                                deleteUsuario={deleteUsuario}
                            />                       
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

