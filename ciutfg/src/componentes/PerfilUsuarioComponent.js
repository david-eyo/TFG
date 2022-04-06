import React from "react";
import "./PerfilUsuarioComponent.css"

const PerfilUsuarioComponent = ({ data, setDataToEdit, deleteUsuario }) => {

let {id, username, nombre, apellidos, email, rol, ciudad, cp, direccion, fechaNacimiento, tlf}= data.user;

let datosEditados={id, username, nombre, apellidos, email, rol, ciudad, cp, direccion, fechaNacimiento, tlf};
  return (
    <div>
      <div className= "perfil-usuario">
          <h3 align="left"><b><i>Mi Perfil</i></b></h3>
          <h5><b>Id usuario: </b>{data.user.id}</h5>
          <br/>
          <h5><b>Username:</b> {data.user.username}</h5>
          <br/>
          <h5><b>Nombre:</b> {data.user.nombre}</h5>
          <br/>
          <h5><b>Apellidos:</b> {data.user.apellidos}</h5>
          <br/>
          <h5><b>Email:</b> {data.user.email}</h5>
          <br/>
          <h5><b>Tipo usuario:</b> {data.user.rol}</h5>
          <br/>
          <h5><b>Ciudad:</b> {data.user.ciudad}</h5>
          <br/>
          <h5><b>CP:</b> {data.user.cp}</h5>
          <br/>
          <h5><b>Dirección:</b> {data.user.direccion}</h5>
          <br/>
          <h5><b>Fecha de Nacimiento:</b> {data.user.fechaNacimiento.substring(0, 10)}</h5>
          <br/>
          <h5><b>Teléfono:</b> {data.user.tlf}</h5>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
        <button className="btn btn-outline-info marginright"  onClick={() => setDataToEdit(datosEditados)}><i className="fa fa-pencil"/></button>
        <button className="btn btn-outline-danger" onClick={() => deleteUsuario(id)}><i className="fa fa-trash"/></button>
      </div>
    </div>
  );
};

export default PerfilUsuarioComponent;