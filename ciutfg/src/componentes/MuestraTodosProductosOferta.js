import {React, useEffect} from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";

const MuestraTodosProductosOferta = ({ data, getAllProductsOferta, rateProduct, dataToEdit, setDataToEdit  }) => {

    useEffect(() => {
        data=getAllProductsOferta();
      }, [data]);
    return (
        <div>
            <h3>Productos Oferta(Normal)</h3>
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

export default MuestraTodosProductosOferta;