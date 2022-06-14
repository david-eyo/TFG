import {React, useState} from "react";
import CrudTableRowItemPedido from "./CrudTableRowItemPedido";
import {useTranslation} from "react-i18next";



const CrudTableItemPedido = ({ data }) => {
  const [t, i18n] = useTranslation("global");

    
  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>{t("CrudTableItemPedido.Id")}</th>
            <th>{t("CrudTableItemPedido.Producto")}</th>
            <th>{t("CrudTableItemPedido.Cantidad")}</th>
            <th>{t("CrudTableItemPedido.Precio")}</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRowItemPedido
                el={el}
              />
            ))
          ) : (
            <tr>
              <td colSpan="3">{t("CrudTableItemPedido.Sin artículos en el carrito de la compra")}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTableItemPedido;