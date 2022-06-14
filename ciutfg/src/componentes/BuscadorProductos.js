import {React, useState} from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import CrudTableRow2 from "./CrudTableRow2";
import {Form, FormControl, Button} from 'react-bootstrap';
import {useTranslation} from "react-i18next";


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
    const [t, i18n] = useTranslation("global");


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
            <h3>{t("BuscadorProductos.Buscador productos(Administración)")}</h3>
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
                    <Button variant="outline-success" onClick={() => findDataByName(busqueda)}>{t("BuscadorProductos.Buscar")}</Button>
                </Form>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th>{t("BuscadorProductos.Nombre")}</th>
                        <th>{t("BuscadorProductos.Precio")}</th>
                        <th>{t("BuscadorProductos.Cantidad")}</th>
                        <th>{t("BuscadorProductos.Nuestros productos")}</th>
                        <th>{t("BuscadorProductos.Oferta")}</th>
                        <th>{t("BuscadorProductos.Valoracion media")}</th>
                        <th>{t("BuscadorProductos.Cantidad valoraciones")}</th>
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
                            <td colSpan="3">{t("BuscadorProductos.Sin datos")}</td>
                        </tr>
                    )}
                </tbody>
            </table>

        </div>
    );
}

