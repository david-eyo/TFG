import React, { useState, useEffect } from "react";
import './CrudFormUsuarioAdministracion.css';
import './CrudTableRow.css';
import {useTranslation} from "react-i18next";

const initailForm = {
    username: "",
    nombre: "",
    apellidos: "",
    email: "",
    ciudad: "",
    cp: "",
    direccion: "",
    fechaNacimiento: "",
    tlf: ""
};

const CrudFormUsuarioAdministracion = ({ updateUsuario, dataToEdit, setDataToEdit }) => {
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
        //if (!form.cantidad) {
        //  alert("Datos incompletos");
        //  return;
        //}
        updateUsuario(form);
        handleReset();
    };

    const handleReset = (e) => {
        setForm(initailForm);
        setDataToEdit(null);
    };

    return (
        <div className="formulario">
            <h3><b><i>{t("CrudFormUsuarioAdministracion.Formulario de edición de usuario")}</i></b></h3>
            <form onSubmit={handleSubmit}>
                <br></br>
                <div>
                    <label htmlFor="nombre" className="input-label">{t("CrudFormUsuarioAdministracion.Nombre")}:</label>
                    <input
                        type="text"
                        name="nombre"
                        onChange={handleChange}
                        value={form.nombre}
                        id="nombre"
                    />
                </div>
                <br></br>
                <div>
                    <label htmlFor="apellidos" className="input-label">{t("CrudFormUsuarioAdministracion.Apellidos")}:</label>
                    <input
                        type="text"
                        name="apellidos"
                        onChange={handleChange}
                        value={form.apellidos}
                        id="apellidos"
                    /></div>
                <br></br>

                <div>
                    <label htmlFor="email" className="input-label">{t("CrudFormUsuarioAdministracion.Email")}:</label>
                    <input
                        type="text"
                        name="email"
                        onChange={handleChange}
                        value={form.email}
                        id="email"
                    /></div>
                <br></br>
                <div>
                    <label htmlFor="ciudad" className="input-label">{t("CrudFormUsuarioAdministracion.Ciudad")}:</label>
                    <input
                        type="text"
                        name="ciudad"
                        onChange={handleChange}
                        value={form.ciudad}
                        id="ciudad"
                    /></div>
                <br></br>
                <div>
                    <label htmlFor="cp" className="input-label">{t("CrudFormUsuarioAdministracion.Cp")}:</label>
                    <input
                        type="text"
                        name="cp"
                        onChange={handleChange}
                        value={form.cp}
                        id="cp"
                    /></div>
                <br></br>
                <div>
                    <label htmlFor="direccion" className="input-label">{t("CrudFormUsuarioAdministracion.Direccion")}:</label>
                    <input
                        type="text"
                        name="direccion"
                        onChange={handleChange}
                        value={form.direccion}
                        id="direccion"
                    />
                </div>
                <br></br>
                <div>
                    <label htmlFor="tlf" className="input-label">{t("CrudFormUsuarioAdministracion.Teléfono")}:</label>
                    <input
                        type="text"
                        name="tlf"
                        onChange={handleChange}
                        value={form.tlf}
                        id="tlf"
                    />
                </div>
                <br></br>
                <input type="submit" className="btn btn-success marginright" style={{ marginBottom: '5rem' }} value={t("CrudFormUsuarioAdministracion.Enviar")} />
                <input type="reset" className="btn btn-secondary" value={t("CrudFormUsuarioAdministracion.Limpiar")} style={{ marginBottom: '5rem' }} onClick={handleReset} />
            </form >
        </div >
    );
};

export default CrudFormUsuarioAdministracion;