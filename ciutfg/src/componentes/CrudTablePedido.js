import {React} from "react";
import CrudTableRowPedido from "./CrudTableRowPedido";



const CrudTablePedido = ({ data, setDetalle }) => {

  return (
    <div>
      <table className="table" style={{marginBottom: '5rem'}}>
        <thead>
          <tr>
            <th>Id</th>
            <th>Estado</th>
            <th>Fecha</th>
            <th>Gastado</th>
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
              <td colSpan="3">Sin pedidos realizados hasta ahora :(</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTablePedido;