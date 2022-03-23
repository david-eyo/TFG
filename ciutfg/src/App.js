import './App.css';
import BarraPrincipal from './componentes/BarraPrincipal';
import Footer from './componentes/Footer';
import {
  BrowserRouter as Router,
} from "react-router-dom";








function App() {
  return (
    <div className="App">
      <Router>
        <BarraPrincipal 
        />
        <Footer/>
      </Router>
    </div>
  );
}

export default App;

