import { useState, type FormEvent } from "react";
import { cadastrarUsuario, type TipoUsuario, type ApiErro } from "../services/usuarioApi";
import "./style/CadastroUsuario.css";

const TIPOS: TipoUsuario[] = ["ADMIN", "GERENTE_FABRICA", "OPERADOR", "VENDEDOR", "CLIENTE"];

export function CadastroUsuario() {

    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [tipo, setTipo] = useState<TipoUsuario>("CLIENTE");

    const [mensagem, setMensagem] = useState<string | null>(null);
    const [erros, setErros] = useState<Record<string, string>>({});
    const [enviando, setEnviando] = useState(false);

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();

        setMensagem(null);
        setErros({});
        setEnviando(true);

        try {
            const usuario = await cadastrarUsuario({nome, email, senha, tipo});

            setMensagem(`Usuário ${usuario.nome} cadastrado com sucesso!`);
            setNome("");
            setEmail("");
            setSenha("");
            setTipo("CLIENTE");

        } catch (erro) {
            const apiErro = erro as ApiErro;
            setMensagem(apiErro.mensagem ?? "Erro ao cadastrar usuário.");
            setErros(apiErro.erros ?? {});
        } finally {
            setEnviando(false);
        }
    }

    return (
        <form onSubmit={handleSubmit} className="cadastro-usuario">
            <h1>Cadastro de Usuário</h1>

            <div className="campo">
                <label htmlFor="nome">Nome</label>
                <input id="nome" value={nome} onChange={(e) => setNome(e.target.value)} required/>
                {erros.nome && <p style={{ color: "red"}}>{erros.nome}</p> }
            </div>

            <div className="campo">
                <label htmlFor="email">E-mail</label>
                <input id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                {erros.email && <p style={{ color: "red" }}>{erros.email}</p> }
            </div>

            <div className="campo">
                <label htmlFor="senha">Senha</label>
                <input id="senha" type={"password"} value={senha} onChange={(e) => setSenha(e.target.value)} required />
                {erros.senha && <p style={{ color: "red" }}>{erros.senha}</p> }
            </div>

            <div className="campo">
                <label htmlFor="tipo">Tipo</label>
                <select id="tipo" value={tipo} onChange={(e) => setTipo(e.target.value as TipoUsuario)} required>
                    {TIPOS.map((t) => (
                        <option key={t} value={t}>{t}</option>
                    ))}
                </select>
            </div>
            <button type={"submit"} disabled={enviando}>
                {enviando ? "Enviando..." : "Cadastrar"}
            </button>
            {mensagem && <p className="mensagem">{mensagem}</p> }
        </form>
    );
}