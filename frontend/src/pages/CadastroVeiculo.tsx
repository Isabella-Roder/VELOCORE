import { useState } from "react";
import { cadastrarVeiculo, type StatusVeiculo, type ApiErro } from "../services/veiculoApi"
import "./style/CadastroVeiculo.css";

const STATUS: StatusVeiculo[] = ["EM_PRODUCAO", "DISPONIVEL", "VENDIDO", "MANUTENCAO"];

export function CadastroVeiculo() {

    const [chassi, setChassi] = useState("");
    const [modelo, setModelo] = useState("");
    const [marca, setMarca] = useState("");
    const [anoFabricacao, setAnoFabricacao] = useState("");
    const [cor, setCor] = useState("");
    const [preco, setPreco] = useState("");
    const [status, setStatus] = useState<StatusVeiculo>("EM_PRODUCAO");

    const [mensagem, setMensagem] = useState<string | null>(null);
    const [erros, setErros] = useState<Record<string, string>>({});
    const [enviando, setEnviando] = useState(false);

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        setMensagem(null);
        setErros({});
        setEnviando(true);

        try {
            const veiculo = await cadastrarVeiculo({
                chassi,
                modelo,
                marca,
                anoFabricacao: Number(anoFabricacao),
                cor,
                preco: Number(preco),
                status
            });

            setMensagem(`Veiculo ${veiculo.modelo} cadastrado com sucesso!`);
            setChassi("");
            setModelo("");
            setMarca("");
            setAnoFabricacao("");
            setCor("");
            setPreco("");
            setStatus("EM_PRODUCAO");
        } catch (erro) {
            const apiErro = erro as ApiErro;
            setMensagem(apiErro.mensagem ?? "Erro ao cadastrar veiculo.");
            setErros(apiErro.erros ?? {});
        } finally {
            setEnviando(false);
        }
    }

    return (
        <form onSubmit={handleSubmit} className="cadastro-veiculo">
            <h1>Cadastro de veiculo</h1>

            <div className="campo">
                <label htmlFor="chassi">Chassi</label>
                <input type={"text"} value={chassi} id="chassi" onChange={(e) => setChassi(e.target.value)} required/>
                {erros.chassi && <p className="erro-campo">{erros.chassi}</p> }
            </div>

            <div className="campo">
                <label htmlFor="modelo">Modelo</label>
                <input type={"text"} id="modelo" value={modelo} onChange={(e) => setModelo(e.target.value)} required />
                {erros.modelo && <p className="erro-campo">{erros.modelo}</p> }
            </div>

            <div className="campo">
                <label htmlFor="marca">Marca</label>
                <input type="text" id="marca" value={marca} onChange={(e) => setMarca(e.target.value)} required />
                {erros.marca && <p className="erro-campo">{erros.marca}</p> }
            </div>

            <div className="campo">
                <label htmlFor="anoFabricacao">Ano de fabricação</label>
                <input id="anoFabricacao" type="number" value={anoFabricacao} onChange={(e) => setAnoFabricacao(e.target.value)} required />
                {erros.anoFabricacao && <p className="erro-campo">{erros.anoFabricacao}</p> }
            </div>

            <div className="campo">
                <label htmlFor="cor">Cor</label>
                <input id="cor" type="text" value={cor} onChange={(e) => setCor(e.target.value)} required />
                {erros.cor && <p className="erro-campo">{erros.cor}</p> }
            </div>

            <div className="campo">
                <label htmlFor="preco">Preço</label>
                <input id="preco" type="number" step="0.01" value={preco} onChange={(e) => setPreco(e.target.value)} required />
                {erros.preco && <p className="erro-campo">{erros.preco}</p> }
            </div>

            <div className="campo">
                <label htmlFor="status">Status</label>
                <select id="status" value={status} onChange={(e) => setStatus(e.target.value as StatusVeiculo)} required>
                    {STATUS.map((s) => (
                        <option key={s} value={s}>{s}</option>
                    ))}
                </select>
            </div>

            <button type={"submit"} disabled={enviando}>
                {enviando ? "Enviando..." : "Cadastrar"}
            </button>
            {mensagem && <p className="mensagem">{mensagem}</p> }
        </form>
    )
}