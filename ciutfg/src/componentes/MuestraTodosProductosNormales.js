import React from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";

const MuestraTodosProductosNormales = ({ data, rateProduct, dataToEdit, setDataToEdit  }) => {
    return (
        <div>
            <hr/>
            <h3>Productos(Normal)</h3>
            {data.length > 0 ? (
                data.map((el) => (
                    <MuestraProductoNormal
                        key={el.id}
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

export default MuestraTodosProductosNormales;