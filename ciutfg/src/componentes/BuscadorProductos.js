import {React, useState} from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import CrudTableRow2 from "./CrudTableRow2";
import {Form, FormControl, Button} from 'react-bootstrap';


const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}



export default function BuscadorProductos({ findDataByName, setDataToEdit }) {
    const [busqueda, setBusqueda] = useState("");
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);


    const handleChange=e=>{
        setBusqueda(e.target.value);
        var aux=e.target.value;
        
        setData(findDataByName(e.target.value));
        if(data != null){            
            setData2(data);
        }
    }
    
    return (
        <div>
            <hr/>
            <h3>Buscador productos(Administración)</h3>
            <div style={style}>
                <Form className="d-flex">
                    <FormControl
                        type="search"
                        placeholder="Buscar"
                        className="me-2"
                        aria-label="Search"
                        value = {busqueda}
                        onChange={handleChange}
                    />
                    <Button variant="outline-success" onClick={() => findDataByName(busqueda)}>Buscar</Button>
                </Form>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Nuestros productos</th>
                        <th>Oferta</th>
                        <th>Valoracion media</th>
                        <th>Cantidad valoraciones</th>
                    </tr>
                </thead>
                <tbody>
                    {data2.length > 0 ? (
                        data2.map((el) => (
                            <CrudTableRow2
                                key={el.nombre}
                                precio={el.precio}
                                el={el}
                                setDataToEdit={setDataToEdit}
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

