import { useState } from "react";
import { CadastroUsuario } from "./pages/CadastroUsuario";
import { ListaUsuarios } from "./pages/ListaUsuarios";
import { CadastroVeiculo } from "./pages/CadastroVeiculo";
import { ListaVeiculos } from "./pages/ListaVeiculos";
import './App.css'

type Tela = "cadastro" | "lista" | "cadastroVeiculo" | "listaVeiculos";

function App() {
  const [tela, setTela] = useState<Tela>("cadastro");

  return (
      <div>
          <nav style={{ display: "flex", justifyContent: "center", gap: "1rem", padding: "1.5rem 0" }}>
            <button onClick={() => setTela("cadastro")}>Cadastrar Usuário</button>
            <button onClick={() => setTela("lista")}>Listar Usuários</button>
            <button onClick={() => setTela("cadastroVeiculo")}>Cadastrar Veículo</button>
              <button onClick={() => setTela("listaVeiculos")}>Listar veiculos</button>
          </nav>
        {tela === "cadastro" && <CadastroUsuario/>}
        {tela === "lista" && <ListaUsuarios/>}
        {tela === "cadastroVeiculo" && <CadastroVeiculo/>}
          {tela === "listaVeiculos" && <ListaVeiculos/>}
      </div>
  );
}

export default App
