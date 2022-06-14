import {React} from "react";
import CrudTableRowPedido from "./CrudTableRowPedido";
import {useTranslation} from "react-i18next";



const CrudTablePedido = ({ data, setDetalle }) => {
  const [t, i18n] = useTranslation("global");

  return (
    <div>
      <table className="table" style={{marginBottom: '5rem'}}>
        <thead>
          <tr>
            <th>{t("CrudTablePedido.Id")}</th>
            <th>{t("CrudTablePedido.Estado")}</th>
            <th>{t("CrudTablePedido.Fecha")}</th>
            <th>{t("CrudTablePedido.Gastado")}</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el) => (
              <CrudTableRowPedido
                el={el}
                setDetalle= {setDetalle}
              />
            ))
          ) : (
            <tr>
              <td colSpan="3">{t("CrudTablePedido.Sin pedidos realizados hasta ahora :(")}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTablePedido;