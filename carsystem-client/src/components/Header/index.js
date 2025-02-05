import "./header.css"
import { Link } from "react-router-dom";
import {useEffect, useState} from "react";

function Header(){
    const profile = localStorage.getItem("@profile");
    const [usuario, setUsuario] = useState({})

    useEffect(() => {
        let usuario = {};
        if(profile === null) {
            usuario = {
                id: 0,
                name: 'Usuário',
                email: 'usuario@usuario.com'
            }
        }else{
            usuario = JSON.parse(profile);
        }
        setUsuario(usuario);
    }, [profile]);

    return(
        <header>
            <div style={{float: "left", width: "30%"}}>
                <Link className="logo" to="/">Car system</Link>
                {<label className="welcome">Bem vindo(a), {usuario.name}</label>}
            </div>
            <div style={{float: "right", width: "70%"}}>
                <Link className="marcas" to="/marcas/list">Marcas</Link>
                <Link className="autenticar" to="/usuario">Categorias</Link>
                <Link className="autenticar" to="/usuario">Carros</Link>
            </div>
        </header>
)
}

export default Header;