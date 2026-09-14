const BASE_URL = "http://localhost:8080/api/veiculos";

export type StatusVeiculo =
    "EM_PRODUCAO" |
    "DISPONIVEL" |
    "VENDIDO" |
    "MANUTENCAO";

export interface VeiculoRequest {
    chassi: string;
    modelo: string;
    marca: string;
    anoFabricacao: number;
    cor: string;
    preco: number;
    status: StatusVeiculo;
}

export interface VeiculoResponse {
    id: string;
    chassi: string;
    modelo: string;
    marca: string;
    anoFabricacao: number;
    cor: string;
    preco: number;
    status: StatusVeiculo;
}

export interface ApiErro {
    mensagem: string;
    erros?: Record<string, string>;
}

export async function cadastrarVeiculo(dados: VeiculoRequest): Promise<VeiculoResponse> {
    const resposta = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    });

    if (!resposta.ok) {
        const erro: ApiErro = await resposta.json();
        throw erro;
    }

    return resposta.json();
}

export async function listarVeiculos(): Promise<VeiculoResponse[]> {
    const resposta = await fetch(BASE_URL);

    if (!resposta.ok) {
        const erro: ApiErro = await resposta.json();
        throw erro;
    }

    return resposta.json();
}