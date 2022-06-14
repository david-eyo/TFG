import React from "react";
import "./PerfilUsuarioComponent.css";
import {useTranslation} from "react-i18next";

const PerfilUsuarioComponent = ({ data, setDataToEdit, deleteUsuario }) => {

  const [t, i18n] = useTranslation("global");  

let {id, username, nombre, apellidos, email, rol, ciudad, cp, direccion, fechaNacimiento, tlf}= data.user;

let datosEditados={id, username, nombre, apellidos, email, rol, ciudad, cp, direccion, fechaNacimiento, tlf};
  return (
    <div>
      <div className= "perfil-usuario">
          <h3 align="left"><b><i>{t("PerfilUsuarioComponent.Mi Perfil")}</i></b></h3>
          <h5><b>{t("PerfilUsuarioComponent.Id usuario")}: </b>{data.user.id}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Username")}:</b> {data.user.username}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Nombre")}:</b> {data.user.nombre}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Apellidos")}:</b> {data.user.apellidos}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Email")}:</b> {data.user.email}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Tipo usuario")}:</b> {data.user.rol}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Ciudad")}:</b> {data.user.ciudad}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.CP")}:</b> {data.user.cp}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Dirección")}:</b> {data.user.direccion}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Fecha de Nacimiento")}:</b> {data.user.fechaNacimiento.substring(0, 10)}</h5>
          <br/>
          <h5><b>{t("PerfilUsuarioComponent.Teléfono")}:</b> {data.user.tlf}</h5>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => setDataToEdit(datosEditados)}><i className="fa fa-pencil"/></button>
        <button className="btn btn-outline-danger" onClick={() => deleteUsuario(id)}><i className="fa fa-trash"/></button>
      </div>
    </div>
  );
};

export default PerfilUsuarioComponent;