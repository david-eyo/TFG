import React from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";

const MuestraTodosProductosNormales = ({ data }) => {
    return (
        <div>
            <h3>Productos(Normal)</h3>
            {data.length > 0 ? (
                data.map((el) => (
                    <MuestraProductoNormal
                        key={el.id}
                        precio={el.precio}
                        el={el}
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