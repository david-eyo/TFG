import React, { useState, useEffect } from "react";
import './CrudForm.css';
import './CrudTableRow.css';
import {useTranslation} from "react-i18next";

const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: false,
  oferta: false,
  id: null,

};

const CrudForm = ({ createData, updateData, dataToEdit, setDataToEdit}) => {
  const [form, setForm] = useState(initailForm);
  const [checked1, setChecked1] = useState(false);
  const [checked2, setChecked2] = useState(false);
  const [image, setImage]=useState('');
  const [t, i18n] = useTranslation("global");

  const changeImagen = (e) =>{
    setImage(e.target.files[0]);
  }


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
  const handleChange1=e=> {
    setChecked1(e.target.checked);
    form.nuestros_productos=e.target.checked;
  };

  const handleChange2=e=> {
    setChecked2(e.target.checked);
    form.oferta=e.target.checked;
  };

  const handleSubmit = (e) => {
    e.preventDefault();



    if (!form.cantidad || !form.nombre || !form.precio) {
      alert("Datos incompletos");
      return;
    }

    if (form.id === null) {
      createData(form, image);
    } else {
      updateData(form);
    }

    handleReset();
  };

  const handleReset = (e) => {
    setForm(initailForm);
    setDataToEdit(null);
  };

  return (
    <div className= "cuadro">
      <h3>{dataToEdit ? "Editar producto" : "Añadir Producto"}</h3>
      <form onSubmit={handleSubmit}>
        <br />
        <div>
          <label htmlFor="exampleInputEmail1" className="input-label" style={{width: '20%'}}><b>{t("CrudForm.Nombre del producto")}:</b></label>
          <input
            type="text"
            name="nombre"
            placeholder="Fresas"
            onChange={handleChange}
            value={form.name}
            id="exampleInputEmail1"
          />
        </div>
        <br />
        <div>
          <label htmlFor="exampleInputEmail1" className="input-label" style={{width: '20%'}}><b>{t("CrudForm.Precio del producto")}:</b></label>
          <input
            type="number"
            name="precio"
            placeholder="Precio"
            min="0"
            step=".01"
            onChange={handleChange}
            value={form.precio}
          />
        </div>
        <br />
        <div>
          <label htmlFor="exampleInputEmail1" className="input-label" style={{width: '20%'}}><b>{t("CrudForm.Cantidad del producto")}:</b></label>
          <input
            type="number"
            name="cantidad"
            placeholder="Cantidad"
            onChange={handleChange}
            value={form.cantidad}
          />
        </div>
        <br />
        <div>
          <label htmlFor="checkedNuesstrosProductos" className="etiqueta-check" style={{width: '20%'}}><b>{t("CrudForm.Nuestros productos")}:</b></label>
          <input
            type="checkbox"
            name="nuestros_productos"
            placeholder="Nuestros productos"
            onChange={handleChange1}
          />
        </div>
        <br />
        <div>
          <label htmlFor="checkedOferta" className="etiqueta-check" style={{width: '20%'}}><b>{t("CrudForm.Oferta")}:</b></label>
          <input
            type="checkbox"
            name="Oferta"
            placeholder="Oferta"
            onChange={handleChange2}
          />
        </div>
        <br></br>
        <br></br>
        <div>
          <input
            type="file"
            name="file"
            placeholder="Sube una imagen"
            onChange={changeImagen}
          />
          
        </div>
        <br />
        <input type="submit" className="btn btn-success marginright" value="Enviar" />
        <input type="reset" className="btn btn-secondary" value="Limpiar" onClick={handleReset} />
      </form>
    </div>
  );
};

export default CrudForm;