import Table from "react-bootstrap/Table";
import { useState, useEffect } from "react";

function TablaJugador() {
  const [players, setPlayers] = useState([]);

  useEffect(() => {
    const getPlayers = () => {
      fetch("http://localhost:9090/api/jugador/")
        .then((respuesta) => respuesta.json())
        .then((respuesta) => setPlayers(respuesta));
    };
    getPlayers();
  }, []);

  return (
    <>
      <h1 className="title-table">Tabla Jugadores</h1>
      <Table striped bordered hover variant="dark" className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Altura</th>
            <th>Peso</th>
            <th>Nacionalidad</th>
            <th>Mano Habil</th>
            <th>Sexo</th>
            <th>Opciones</th>
          </tr>
        </thead>
        <tbody>
          {players.map((player) => (
            <tr key={player.id}>
              <td>{player.id}</td>
              <td>{player.nombres}</td>
              <td>{player.apellidos}</td>
              <td>{player.altura}</td>
              <td>{player.peso}</td>
              <td>{player.nacionalidad}</td>
              <td>{player.mano_habil}</td>
              <td>{player.genero}</td>
              <td>Eliminar</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
}

export default TablaJugador;
