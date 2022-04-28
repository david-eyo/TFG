import React, { useState } from 'react';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import BuscadorHorariosAdministrador from '../componentes/BuscadorHorariosAdministrador';



function BuscadorHorariosAdmin({token}) {


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
      <BuscadorHorariosAdministrador
        token={token}
        setError={setError}
      />

    </div>
  );
}

export default BuscadorHorariosAdmin;