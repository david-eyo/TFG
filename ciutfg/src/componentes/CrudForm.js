import React, { useState, useEffect } from "react";
import './CrudForm.css';
import './CrudTableRow.css';

const initailForm = {
  nombre: "",
  precio: "",
  cantidad: "",
  nuestros_productos: false,
  oferta: false,
  id: null,

};

const CrudForm = ({ createData, updateData, dataToEdit, setDataToEdit }) => {
  const [form, setForm] = useState(initailForm);
  const [checked1, setChecked1] = useState(false);
  const [checked2, setChecked2] = useState(false);

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
      <h3>{dataToEdit ? "Editar producto" : "Añadir Producto"}</h3>
      <form onSubmit={handleSubmit}>
        <br />
        <div>
          <label htmlFor="exampleInputEmail1" className="input-label">Nombre del producto:</label>
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
          <label htmlFor="exampleInputEmail1" className="input-label">Precio del producto:</label>
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
          <label htmlFor="exampleInputEmail1" className="input-label">Cantidad del producto:</label>
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
          <label htmlFor="checkedNuesstrosProductos" className="etiqueta-check">Nuestros productos:</label>
          <input
            type="checkbox"
            name="nuestros_productos"
            placeholder="Nuestros productos"
            onChange={handleChange1}
          />
        </div>
        <br />
        <div>
          <label htmlFor="checkedOferta" className="etiqueta-check">Oferta:</label>
          <input
            type="checkbox"
            name="Oferta"
            placeholder="Oferta"
            onChange={handleChange2}
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