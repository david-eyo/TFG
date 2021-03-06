import React, { useState, useEffect } from "react";
import './CrudForm.css';
import './CrudTableRow.css';
import {useTranslation} from "react-i18next";


const initailForm = {
  cantidad: "",
  productId: null,
};

const CrudFormCarritoAdministracion = ({ updateCarrito, dataToEdit, setDataToEdit, setFormulario}) => {
  const [form, setForm] = useState(initailForm);
  const [t, i18n] = useTranslation("global");


  useEffect(() => {
    if (dataToEdit) {
      setForm(dataToEdit);
    } else {
      setForm(initailForm);
    }
  }, [dataToEdit]);

  const handleChange = (e) => {

    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });

  };


  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.cantidad) {
      alert("Datos incompletos");
      return;
    }
    updateCarrito(form);
    handleReset();
  };

  const handleReset = (e) => {
    setForm(initailForm);
    setDataToEdit(null);
    setFormulario(false);
  };

  return (
    <div className= "formul">
      <h3>{"Formulario de edición de carrito"}</h3>
      <form onSubmit={handleSubmit}>
        <br />
        <div>
          <label htmlFor="cantidad" className="input-label">{t("CrudFormCarritoAdministracion.Cantidad")}:</label>
          <input
            type="number"
            name="cantidad"
            onChange={handleChange}
            value={form.cantidad}
            id="cantidad"
          />
        </div>
        <br></br>
        <input type="submit" className="btn btn-success marginright" value={t("CrudFormCarritoAdministracion.Enviar")} />
        <input type="reset" className="btn btn-secondary" value={t("CrudFormCarritoAdministracion.Limpiar")} onClick={handleReset} />
      </form>
    </div>
  );
};

export default CrudFormCarritoAdministracion;