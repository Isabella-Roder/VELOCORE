import { useEffect, useState } from "react";
import { listarUsuarios, type UsuarioResponse, type ApiErro } from "../services/usuarioApi";
import "./style/ListaUsuarios.css";

export function ListaUsuarios() {

    const [usuarios, setUsuarios] = useState<UsuarioResponse[]>([]);
    const [carregando, setCarregando] = useState(true);
    const [erro, setErro] = useState<string | null>(null);

    useEffect(() => {
        carregar();
    }, []);

    async function carregar() {
        setCarregando(true);
        setErro(null);

        try {
            const dados = await listarUsuarios();
            setUsuarios(dados);
        } catch (e) {
            const apiErro = e as ApiErro;
            setErro(apiErro.mensagem ?? "Erro ao carregar usuários.");
        } finally {
            setCarregando(false);
        }
    }

    return(
        <div className="lista-usuarios">
            <h1>Usuários</h1>

            {carregando && <p className="mensagem">Carregando...</p> }
            {erro && <p className="mensagem erro">{erro}</p> }

            {!carregando && !erro && (
                <table>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Tipo</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usuarios.map((u) => (
                            <tr key={u.id}>
                                <td>{u.nome}</td>
                                <td>{u.email}</td>
                                <td>{u.tipo}</td>
                                <td>
                                    <span className={u.ativo ? "status ativo" : "status inativo"}>
                                        {u.ativo ? "Ativo" : "Inativo"}
                                    </span>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    )
}