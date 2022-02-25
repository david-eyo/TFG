import './App.css';
import BarraPrincipal from './componentes/BarraPrincipal';
import CrudApi from './componentes/CrudApiProductos';
import CrudApiHistorico from './componentes/CrudApiHistorico';



function App() {
  return (
    <div className="App">
      <BarraPrincipal/>
      <CrudApiHistorico/>
    </div>
  );
}

export default App;
