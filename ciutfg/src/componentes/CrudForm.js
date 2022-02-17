import React, { useState, useEffect } from "react";
import './CrudForm.css';

const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: "",
  oferta: "",
  id: null,

};

const CrudForm = ({ createData, updateData, dataToEdit, setDataToEdit }) => {
  const [form, setForm] = useState(initailForm);

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

    if (!form.cantidad || !form.nombre || !form.precio) {
      alert("Datos incompletos");
      return;
    }

    if (form.id === null) {
      createData(form);
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
    <div>
      <br />
      <h3>{dataToEdit ? "Editar producto" : "Añadir Producto"}</h3>
      <form onSubmit={handleSubmit}>
        <br />
        <div>
          <label for="exampleInputEmail1" className="input-label">Nombre del producto:</label>
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
          <label for="exampleInputEmail1" className="input-label">Precio del producto:</label>
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
          <label for="exampleInputEmail1" className="input-label">Cantidad del producto:</label>
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
          <label for="exampleInputEmail1">Nuestros productos:</label>
          <input
            type="checkbox"
            name="nuestros_productos"
            placeholder="Nuestros productos"
            onChange={handleChange}
            value={form.nuestros_productos}
          />
        </div>
        <br />
        <div>
          <label for="exampleInputEmail1">Oferta:</label>
          <input
            type="checkbox"
            name="Oferta"
            placeholder="oferta"
            onChange={handleChange}
            value={!!form.oferta}
          /></div>
        <br />
        <input type="submit" value="Enviar" />
        <input type="reset" value="Limpiar" onClick={handleReset} />
      </form>
    </div>
  );
};

export default CrudForm;