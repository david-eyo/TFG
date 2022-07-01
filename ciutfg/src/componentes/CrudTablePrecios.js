import {React, useState} from "react";
import CrudTableRowPrecios from "./CrudTableRowPrecios";
import {Form, FormControl, Button} from 'react-bootstrap';
import {useTranslation} from "react-i18next";



const style = {
    width: '25rem',
    marginRight: '2rem',
    marginLeft: 'auto',
    marginTop: '1rem'
}

const CrudTablePrecios = ({ findHistoricoPreciosByIdProducto, data }) => {
    const [busqueda, setBusqueda] = useState("");
    const [t, i18n] = useTranslation("global");


    const handleChange=e=>{
        setBusqueda(e.target.value);
        findHistoricoPreciosByIdProducto(e.target.value);
    }



  return (
    <div>
      <div style={style}>
                <Form className="d-flex">
                    <FormControl
                        type="search"
                        placeholder="Buscar por id de producto"
                        className="me-2"
                        aria-label="Search"
                        value = {busqueda}
                        onChange={handleChange}
                    />
                    <Button variant="outline-success" onClick={() => handleChange()}>{t("CrudTablePrecios.Buscar")}</Button>
                </Form>
            </div>
      <table className="table">
        <thead>
          <tr>
            <th>{t("CrudTablePrecios.Id")}</th>
            <th>{t("CrudTablePrecios.Fecha Inicio")}</th>
            <th>{t("CrudTablePrecios.Hora Inicio")}</th>
            <th>{t("CrudTablePrecios.Fecha Fin")}</th>
            <th>{t("CrudTablePrecios.Hora Fin")}</th>
            <th>{t("CrudTablePrecios.Nombre producto")}</th>
            <th>{t("CrudTablePrecios.Precio")}</th>
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
              <td colSpan="3">{t("CrudTablePrecios.Sin datos")}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTablePrecios;