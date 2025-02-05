import {useEffect, useState} from "react";
import carsystem_api from "../../services/carsystem_api";
import {Link} from "react-router-dom";
import "./home.css"


function Home() {
    const [carros, setCarros] = useState([]);
    const[loading, setLoading] = useState(true);


    useEffect(() => {
        async function loadCarros() {
            const response = await carsystem_api.get("/api/v1/car");
            setCarros(response.data)
            setLoading(false);
        }
        loadCarros();
    }, []);
    if(loading){
        return (
            <div className="loading">
                Carregando lista de carros ...
            </div>
        )
    }
    return (
        <div className="container">
            <div className="lista-carros">
                {carros.length == 0 && <span>Você não possui carros cadastrados...</span>}
                {carros.length == 0 && <Link to="/create">Novo carro</Link>}
                {carros.map((carro) => {
                    return (
                        <article key={carro.id}>
                            <strong>{carro.vehicle}</strong>
                            <strong>R$ {carro.potency}</strong>
                            <Link to={`/carro/${carro.id}`}>Acessar</Link>
                        </article>
                    )
                })}
            </div>
        </div>
    )
}

export default Home;