import React from "react";
import CrudTableRow from "./CrudTableRow";
import {useTranslation} from "react-i18next";

const CrudTable = ({ data, setDataToEdit, deleteData }) => {
  const [t, i18n] = useTranslation("global");
  return (
    <div>
      <br/><br/><br/><br/><hr></hr>
      <h3>{t("CrudTable.Productos(Administración)")}</h3>
      <table className="table">
        <thead>
          <tr>
            <th>{t("CrudTable.Id")}</th>
            <th>{t("CrudTable.Nombre")}</th>
            <th>{t("CrudTable.Precio")}</th>
            <th>{t("CrudTable.Cantidad")}</th>
            <th>{t("CrudTable.Nuestros productos")}</th>
            <th>{t("CrudTable.Oferta")}</th>
            <th>{t("CrudTable.Valoracion media")}</th>
            <th>{t("CrudTable.Cantidad valoraciones")}</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRow
                key={el.nombre}
                precio={el.precio}
                el={el}
                setDataToEdit={setDataToEdit}
                deleteData={deleteData}
              />
            ))
          ) : (
            <tr>
              <td colSpan="3">{t("CrudTable.Sin datos")}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTable;