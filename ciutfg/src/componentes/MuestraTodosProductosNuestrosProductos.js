import {React, useEffect} from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";
import {useTranslation} from "react-i18next";

const MuestraTodosProductosNuestrosProductos = ({ data, getAllProductsNuestrosProductos, rateProduct, dataToEdit, setDataToEdit, token  }) => {

    const [t, i18n] = useTranslation("global");
    
    useEffect(() => {
        data=getAllProductsNuestrosProductos();
      }, [data]);
    return (
        <div>
            {data.length > 0 ? (
                data.map((el) => (
                    <MuestraProductoNormal
                        key={el.nombre}
                        precio={el.precio}
                        el={el}
                        rateProduct={rateProduct}
                        dataToEdit={dataToEdit}
                        setDataToEdit={setDataToEdit}
                        token={token}
                    />
                ))
            ) : (
                <tr>
                    <td colSpan="3">{t("MuestraTodosProductosNuestrosProductos.Sin Productos")}</td>
                </tr>
            )}
        </div>
    );
};

export default MuestraTodosProductosNuestrosProductos;