import React, { useState } from 'react';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import BuscadorPedidosComp from '../componentes/BuscadorPedidosComp';



function BuscadorPedidos({token}) {


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
      <BuscadorPedidosComp
        token={token}
        setError={setError}
      />

    </div>
  );
}

export default BuscadorPedidos;