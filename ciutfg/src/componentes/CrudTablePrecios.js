import {React, useState} from "react";
import CrudTableRowPrecios from "./CrudTableRowPrecios";
import {Form, FormControl, Button} from 'react-bootstrap';




const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}

const CrudTablePrecios = ({ findHistoricoPreciosByIdProducto, data }) => {
    const [busqueda, setBusqueda] = useState("");
    const [data2, setData2] = useState([]);


    const handleChange=e=>{
        setBusqueda(e.target.value);
        findHistoricoPreciosByIdProducto(e.target.value);
    }



  return (
    <div>
      <br/><br/><br/><br/><hr></hr>
      <h3>Productos(Adminsitración)</h3>
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
                    <Button variant="outline-success" onClick={() => handleChange()}>Buscar</Button>
                </Form>
            </div>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Fecha Ini</th>
            <th>Fecha Fin</th>
            <th>Nombre producto</th>
            <th>Precio</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRowPrecios
                el={el}
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
};

export default CrudTablePrecios;