package com.example.tenismesa.controller;

import com.example.tenismesa.models.Jugador;
import com.example.tenismesa.models.Participante;
import com.example.tenismesa.repository.RepoJugador;
import com.example.tenismesa.repository.RepoParticipante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugador/")
public class jugadorController {
    private RepoJugador repoJugador;
    private RepoParticipante repoParticipante;
    public jugadorController(RepoJugador repoJugador, RepoParticipante repoParticipante) {
        this.repoJugador = repoJugador;
        this.repoParticipante = repoParticipante;
    }
    @PostMapping("add")
    public void agregar(@RequestBody Jugador jugador){
        Participante p = new Participante();
        p.setNombre(jugador.getNombre());
        p.setRanking(jugador.getRanking());
        Participante pR = repoParticipante.save(p);
        jugador.setId(pR.getId());
        repoJugador.save(jugador);
    }
    @PutMapping("{id}")
    public void editar(@RequestBody Jugador jugador,@PathVariable("id") Long id){
        Participante p = new Participante();
        p.setNombre(jugador.getNombre());
        p.setRanking(jugador.getRanking());
        p.setId(id);
        Participante pR = repoParticipante.save(p);
        jugador.setId(pR.getId());
        jugador.setId(id);
        repoJugador.save(jugador);
    }
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable("id") Long id){
        Jugador j = repoJugador.findById(id).get();
        Participante p = repoParticipante.findById(id).get();
        repoJugador.delete(j);
        repoParticipante.delete(p);
    }
    @GetMapping("mostrar")
    public ResponseEntity<List<Jugador>> mostrarJugadores(){

        return new ResponseEntity<>(repoJugador.jugadores(), HttpStatus.OK);
    }
}
