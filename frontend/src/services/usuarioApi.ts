const BASE_URL = "http://localhost:8080/api/usuarios";

export type TipoUsuario =
    "ADMIN" |
    "GERENTE_FABRICA" |
    "OPERADOR" |
    "VENDEDOR" |
    "CLIENTE";

export interface UsuarioRequest {
    nome: string;
    email: string;
    senha: string;
    tipo: TipoUsuario;
}
export interface UsuarioResponse {
    id: string;
    nome: string;
    email: string;
    tipo: TipoUsuario;
    ativo: boolean;
}
export interface ApiErro {
    mensagem: string;
    erros?: Record<string, string>;
}
export async function cadastrarUsuario(dados: UsuarioRequest): Promise<UsuarioResponse> {
    const resposta = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dados),
    });
    if (!resposta.ok) {
        const erro: ApiErro = await resposta.json();
        throw erro;
    }
    return resposta.json();
}

export async function listarUsuarios(): Promise<UsuarioResponse[]> {
    const resposta = await fetch(BASE_URL);
    if (!resposta.ok) {
        const erro: ApiErro = await resposta.json();
        throw erro;
    }

    return resposta.json();
}