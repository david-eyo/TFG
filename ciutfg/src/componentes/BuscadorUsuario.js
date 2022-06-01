import { React, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { Form, FormControl, Button } from 'react-bootstrap';
import CrudTableRowUsuario from './CrudTableRowUsuario';
import { helpHttp } from '../helpers/helpHttp';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorUsuario({ findUsuarioByUsername, setDataToEdit, deleteUsuario, setError, token }) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);

   
    const handleChange = e => {
        setBusqueda(e.target.value);

        let respuesta=findUsuarioByUsername2(e.target.value)
        setData(respuesta);

    }

    const buscaManual = (nombre) => {
        
        let respuesta=findUsuarioByUsername2(nombre)
        setData(respuesta);
    }

    let api = helpHttp();
    let url = "http://localhost:5000/secure/auth";

    const findUsuarioByUsername2 = (nombre) => {
        let options = {
          headers: {"Content-Type": "application/json",
                    "Authorization": "Bearer "+token },
        };
    
        let url2 = url + "?username=" + nombre;

        console.log(url2);
    
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
                    <Button variant="outline-success" onClick={() => buscaManual(busqueda)}>Buscar</Button>
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
                    {data && data.user ? (                                                 
                            <CrudTableRowUsuario
                                el={data.user}
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

