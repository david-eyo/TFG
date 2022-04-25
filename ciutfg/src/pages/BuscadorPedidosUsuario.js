import React, { useState } from 'react';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import BuscadorPedidosUsuarioComp from '../componentes/BuscadorPedidosUsuarioComp';



function BuscadorPedidosUsuario({token, username}) {


  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);


  return (
    <div>
      {loading && <Loader />}
      {error && (
        <Message
          msg={`Error ${error.status}: ${error.statusText}`}
          bgColor="#dc3545"
        />
      )}
      <BuscadorPedidosUsuarioComp
        token={token}
        setError={setError}
        user={username}
      />

    </div>
  );
}

export default BuscadorPedidosUsuario;