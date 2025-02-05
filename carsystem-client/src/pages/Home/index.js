import {useEffect, useState} from "react";
import carsystem_api from "../../services/carsystem_api";
import {Link} from "react-router-dom";
import "./home.css"


function Home() {
    const [carros, setCarros] = useState([]);
    const[loading, setLoading] = useState(true);


    useEffect(() => {
        async function loadCarros() {
            const response = await carsystem_api.get("/api/v1/cars");
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
            <div className="lista-filmes">
                {carros.map((carro) => {
                    return (
                        <article key={carro.id}>
                            <strong>{carro.title}</strong>
                            <img src={`${carro.image}`}
                                 alt={carro.title}/>
                            <strong>R$ {carro.price}</strong>
                            <Link to={`/carro/${carro.id}`}>Acessar</Link>
                        </article>
                    )
                })}
            </div>
        </div>
    )
}

export default Home;