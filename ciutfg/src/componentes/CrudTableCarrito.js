import {React, useState} from "react";
import CrudTableRowCarrito from "./CrudTableRowCarrito";



const CrudTableCarrito = ({ data, setDataToEdit, deleteData }) => {
    const [busqueda, setBusqueda] = useState("");

  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio</th>
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
              <td colSpan="3">Sin artículos en el carrito de la compra</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTableCarrito;