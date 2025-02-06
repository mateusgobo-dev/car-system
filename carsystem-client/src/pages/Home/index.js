import {React, useEffect, useState} from "react";
import carsystem_api from "../../services/carsystem_api";
import {Link, useParams} from "react-router-dom";
import "./home.css"
import {toast} from "react-toastify";

function recuperarMarcas() {
    async function recuperarMarcasPromisse() {
        await carsystem_api.get("/api/v1/brand")
            .then(response => {
                const marcas = response.data
                localStorage.removeItem("@marcas")
                localStorage.setItem("@marcas", JSON.stringify(marcas));
            }).catch(reason => {
                toast.error(`Falha ao recuperar marcas, erro => ${reason.error}`)
            });
    }

    recuperarMarcasPromisse();
}

function recuperarCategorias() {
    async function recuperarCategoriasPromisse() {
        await carsystem_api.get("/api/v1/category")
            .then(response => {
                const categorias = response.data;
                localStorage.removeItem("@categorias")
                localStorage.setItem("@categorias", JSON.stringify(categorias));
            }).catch(reason => {
                toast.error(`Falha ao recuperar categorias, erro => ${reason.error}`)
            });
    }

    recuperarCategoriasPromisse();
}

function recuperarCores() {
    async function recuperarCoresPromisse() {
        await carsystem_api.get("/api/v1/color")
            .then(response => {
                const cores = response.data;

                localStorage.removeItem("@cores")
                localStorage.setItem("@cores", JSON.stringify(cores));
            }).catch(reason => {
                toast.error(`Falha ao recuperar cores, erro => ${reason.error}`)
            });
    }

    recuperarCoresPromisse();
}

function validarPreenchimentoFormulario(veiculo, potencia) {
    let isValid = true;
    if (veiculo === undefined || veiculo === '') {
        toast.warn("Informe o veículo");
        document.getElementById('veiculo').focus();
        isValid = false;
    } else if (potencia === undefined || potencia === '') {
        toast.warn("Informe a potencia");
        document.getElementById('potencia').focus();
        isValid = false;
    }
    return isValid;
}

function Home() {
    recuperarCores();
    recuperarCategorias();
    recuperarMarcas();

    const {rule} = useParams();
    const [carros, setCarros] = useState([]);
    const [loading, setLoading] = useState(true);
    const [cores] = useState(JSON.parse(localStorage.getItem("@cores")));
    const [marcas] = useState(JSON.parse(localStorage.getItem("@marcas")));
    const [categorias] = useState(JSON.parse(localStorage.getItem("@categorias")));
    const [potencia, setPotencia] = useState("");
    const [veiculo, setVeiculo] = useState("");

    useEffect(() => {
        async function loadCarros() {
            const response = await carsystem_api.get("/api/v1/car");
            setCarros(response.data)
            setLoading(false);
        }
        loadCarros();
    }, [rule]);

    let cor = 0;
    let marca = 0;
    let categoria = 0;

    function selecionarCor(value) {
        cor = value;
    }

    function selecionarMarca(value) {
        marca = value;
    }

    function selecionarCategoria(value) {
        categoria = value;
    }

    function salvarCarro() {
        let validarFormulario = validarPreenchimentoFormulario(veiculo, potencia);
        if (validarFormulario) {
            const carDto = {
                id: null,
                vehicle: veiculo,
                potency: potencia,
                colorId: cor,
                brandId: marca,
                categoryId: categoria
            }

            async function saveCar() {
                await carsystem_api.post("/api/v1/car", JSON.stringify(carDto))
                    .then(response => {
                        if (response.status === 200) {
                            toast.info(`Veículo ${veiculo} criado com sucesso`);
                        } else {
                            toast.error(`Erro ao salvar veículo ${veiculo}, erro => ${response.status} - ${response.statusText}`);
                        }
                    })
                    .catch(reason => {
                        toast.error(`Erro ao salvar veículo ${veiculo}, erro => ${reason.error}`);
                    });
            }

            saveCar();
        }
    }

    if (loading) {
        return (
            <div className="loading">
                Carregando lista de carros ...
            </div>
        )
    }
    return (
        <div className="container">
            <div className="lista-carros">
                {rule === 'list'  &&
                    carros.length === 0 &&
                    <div>
                        <span>Você não possui carros cadastrados...</span><br/>
                        <Link to="/create">Criar carro</Link>
                    </div>
                }
                {rule === 'list' && carros.length > 0 &&
                    <div>
                        <Link to="/create" style={{float: 'right'}}>Criar carro</Link>
                        {carros.map((carro) => {
                            return (
                                <article key={carro.id}>
                                    <strong>{carro.vehicle}</strong>
                                    <strong>{carro.potency}</strong>
                                    <Link to={`/carro/${carro.id}`}>Acessar</Link>
                                </article>
                            )
                        })
                        }
                    </div>
                }
                {rule !== 'list'  &&
                    <div className="container">
                        <div className="row row-cols-12">
                            <div className="col-md-4">
                                <label htmlFor="corOption" className="form-label">Selecione a cor</label><br/>
                                <select name="corOption" className="form-control"
                                        onChange={(e) => selecionarCor(e.target.value)}>
                                    {cores.map((cor) => {
                                        return (
                                            <option key={cor.id} value={cor.id}>{cor.description}</option>
                                        )
                                    })}
                                </select>
                            </div>
                            <div className="col-md-4">
                                <label htmlFor="marcaOption" className="form-label">Selecione a marca</label><br/>
                                <select name="marcaOption" className="form-control"
                                        onChange={(e) => selecionarMarca(e.target.value)}>
                                    {marcas.map((marca) => {
                                        return (
                                            <option key={marca.id} value={marca.id}>{marca.name}</option>
                                        )
                                    })}
                                </select>
                            </div>
                            <div className="col-md-4">
                                <label htmlFor="categoriaOption" className="form-label">Selecione a
                                    categoria</label><br/>
                                <select name="categoriaOption" className="form-control"
                                        onChange={(e) => selecionarCategoria(e.target.value)}>
                                    {categorias.map((categoria) => {
                                        return (
                                            <option key={categoria.id}
                                                    value={categoria.id}>{categoria.name}</option>
                                        )
                                    })}
                                </select>
                            </div>
                        </div>
                        <div className="row row-cols-12">
                            <div className="col-md-8">
                                <label htmlFor="veiculo" className="form-label">Veículo</label>
                                <input type="text" className="form-control" id="veiculo"
                                       onChange={(e) => setVeiculo(e.target.value)}/>
                            </div>
                            <div className="col-md-4">
                                <label htmlFor="potencia" className="form-label">Potência</label>
                                <input type="text" className="form-control" id="potencia"
                                       onChange={(e) => setPotencia(e.target.value)}/>
                            </div>
                        </div>
                        <div className="container-fluid" style={{marginTop: "20px"}}>
                            <button onClick={() => salvarCarro()}>Salvar</button>
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}
export default Home;