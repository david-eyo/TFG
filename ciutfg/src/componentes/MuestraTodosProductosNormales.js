import React from "react";
import MuestraProductoNormal from "./MuestraProductoNormal";
import {useTranslation} from "react-i18next";

const MuestraTodosProductosNormales = ({ data, rateProduct, dataToEdit, setDataToEdit  }) => {
    const [t, i18n] = useTranslation("global");
    return (
        <div>
            <hr/>
            <h3>{t("MuestraTodosProductosNormales.Productos(Normal)")}</h3>
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
                    <td colSpan="3">{t("MuestraTodosProductosNormales.Sin Productos")}</td>
                </tr>
            )}
        </div>
    );
};

export default MuestraTodosProductosNormales;