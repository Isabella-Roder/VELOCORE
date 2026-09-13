import { useState } from "react";
import { CadastroUsuario } from "./pages/CadastroUsuario";
import { ListaUsuarios } from "./pages/ListaUsuarios";
import './App.css'

type Tela = "cadastro" | "lista";

function App() {
  const [tela, setTela] = useState<Tela>("cadastro");

  return (
      <div>
          <nav style={{ display: "flex", justifyContent: "center", gap: "1rem", padding: "1.5rem 0" }}>
            <button onClick={() => setTela("cadastro")}>Cadastrar</button>
            <button onClick={() => setTela("lista")}>Listar</button>
          </nav>
        {tela === "cadastro" ? <CadastroUsuario/> : <ListaUsuarios/>}
      </div>
  );
}

export default App
