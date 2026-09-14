import { useEffect, useState } from "react";
import { listarVeiculos, type VeiculoResponse, type ApiErro } from "../services/veiculoApi";
import "./style/ListaVeiculos.css";

export function ListaVeiculos() {

    const [veiculos, setVeiculos] = useState<VeiculoResponse[]>([]);
    const [carregando, setCarregando] = useState(true);
    const [erro, setErro] = useState<string | null>(null);

    useEffect(() => {
        carregar();
    }, []);

    async function carregar() {
        setCarregando(true);
        setErro(null);

        try {
            const dados = await listarVeiculos();
            setVeiculos(dados);
        } catch (e) {
            const apiErro = e as ApiErro;
            setErro(apiErro.mensagem ?? "Erro ao carregar veículos.");
        } finally {
            setCarregando(false);
        }
    }

    return (
        <div className="lista-veiculos">
            <h1>Veículos</h1>

            {carregando && <p className="mensagem">Carregando...</p> }
            {erro && <p className="mensagem erro">{erro}</p> }
            {!carregando && !erro && (
                <table>
                    <thead>
                    <tr>
                        <th>Chassi</th>
                        <th>Modelo</th>
                        <th>Marca</th>
                        <th>Ano</th>
                        <th>Cor</th>
                        <th>Preço</th>
                        <th>Status</th>
                    </tr>
                    </thead>

                    <tbody>
                        {veiculos.map((v) => (
                            <tr key={v.id}>
                                <td>{v.chassi}</td>
                                <td>{v.modelo}</td>
                                <td>{v.marca}</td>
                                <td>{v.anoFabricacao}</td>
                                <td>{v.cor}</td>
                                <td>{v.preco.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</td>
                                <td>
                                    <span className={`status status-${v.status.toLowerCase()}`}>{v.status}</span>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}