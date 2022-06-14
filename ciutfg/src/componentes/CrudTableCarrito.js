import {React, useState} from "react";
import CrudTableRowCarrito from "./CrudTableRowCarrito";
import {useTranslation} from "react-i18next";



const CrudTableCarrito = ({ data, setDataToEdit, deleteData }) => {
    const [busqueda, setBusqueda] = useState("");
    const [t, i18n] = useTranslation("global");

  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>{t("CrudTableCarrito.Id")}</th>
            <th>{t("CrudTableCarrito.Producto")}</th>
            <th>{t("CrudTableCarrito.Cantidad")}</th>
            <th>{t("CrudTableCarrito.Precio")}</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRowCarrito
                el={el}
                setDataToEdit={setDataToEdit}
                deleteData={deleteData}
              />
            ))
          ) : (
            <tr>
              <td colSpan="3">{t("CrudTableCarrito.Sin artículos en el carrito de la compra")}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTableCarrito;