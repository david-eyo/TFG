import React from 'react';
import './Footer.css';
import {useTranslation} from "react-i18next";
import Button from 'react-bootstrap/Button';


function Footer() {
  const [t, i18n] = useTranslation("global");

  return (
    <footer className = "footer">
        <div style={{marginLeft: '2rem', float: 'left', marginBottom: '0.5rem'}}>
          <input type="image" style={{width: '1.5rem', height: '1rem', marginRight: '0.2rem'}} onClick={() => i18n.changeLanguage("es")} src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Bandera_de_Espa%C3%B1a_%28sin_escudo%29.svg/200px-Bandera_de_Espa%C3%B1a_%28sin_escudo%29.svg.png" />
          <input type="image" style={{width: '1.3rem', height: '1rem'}} onClick={() => i18n.changeLanguage("gal")} src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Bandeira_galega_civil.svg/1200px-Bandeira_galega_civil.svg.png" />
        </div>


    </footer>
  );
}

export default Footer;
