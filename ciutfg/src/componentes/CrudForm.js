import React, { useState, useEffect } from "react";

const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: "",
  oferta : "",
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
      <h3>{dataToEdit ? "Editar" : "Agregar"}</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          onChange={handleChange}
          value={form.name}
        />
        <input
          type="number"
          name="precio"
          placeholder="Precio"
          min="0" 
          step=".01"
          onChange={handleChange}
          value={form.constellation}
        />
        <input
          type="number"
          name="cantidad"
          placeholder="Cantidad"
          onChange={handleChange}
          value={form.constellation}
        />
        <input
          type="checkbox"
          name="nuestros_productos"
          placeholder="Nuestros productos"
          onChange={handleChange}
          value={form.constellation}
        />     
        <input
          type="checkbox"
          name="Oferta"
          placeholder="oferta"
          onChange={handleChange}
          value={form.constellation}
        />      
        <input type="submit" value="Enviar" />
        <input type="reset" value="Limpiar" onClick={handleReset} />
      </form>
    </div>
  );
};

export default CrudForm;