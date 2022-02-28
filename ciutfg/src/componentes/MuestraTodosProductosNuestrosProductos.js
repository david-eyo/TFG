import {React, useEffect} from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";

const MuestraTodosProductosNuestrosProductos = ({ data, getAllProductsNuestrosProductos, rateProduct, dataToEdit, setDataToEdit  }) => {

    useEffect(() => {
        data=getAllProductsNuestrosProductos();
      }, [data]);
    return (
        <div>
            <hr/>
            <br/>
            <h3>Nuestros Productos(Normal)</h3>
            {data.length > 0 ? (
                data.map((el) => (
                    <MuestraProductoNormal
                        key={el.nombre}
                        precio={el.precio}
                        el={el}
                        rateProduct={rateProduct}
                        dataToEdit={dataToEdit}
                        setDataToEdit={setDataToEdit}
                    />
                ))
            ) : (
                <tr>
                    <td colSpan="3">Sin Productos</td>
                </tr>
            )}
        </div>
    );
};

export default MuestraTodosProductosNuestrosProductos;