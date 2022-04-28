import React, { useState } from 'react';
import Loader from "../componentes/Loader";
import Message from "../componentes/Message";
import BuscadorHorariosUsuario from '../componentes/BuscadorHorariosUsuario';



function MisHorarios({token, username}) {


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
      <BuscadorHorariosUsuario
        token={token}
        setError={setError}
        username={username}
      />

    </div>
  );
}

export default MisHorarios;