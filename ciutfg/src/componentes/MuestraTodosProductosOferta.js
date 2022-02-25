import {React, useEffect, useState} from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";

const MuestraTodosProductosOferta = ({ data, getAllProductsOferta, rateProduct, dataToEdit, setDataToEdit  }) => {

    useEffect(() => {
        data=getAllProductsOferta();
      }, [data]);
    return (
        <div>
            <hr/>
            <br/>
            <h3>Productos Oferta(Normal)</h3>
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

export default MuestraTodosProductosOferta;