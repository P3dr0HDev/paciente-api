package br.com.senac.paciente.controller;

import br.com.senac.paciente.dto.PacienteRequestDto;
import br.com.senac.paciente.entities.Paciente;
import br.com.senac.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok((pacienteService.listar()));
    }

    @PostMapping("/criar")
    public ResponseEntity<Paciente> criar (@RequestBody PacienteRequestDto paciente) {
        try {
            return ResponseEntity.status(201).body(pacienteService.criar(paciente));

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Paciente> atualizar (@PathVariable Long id,
                                               @RequestBody PacienteRequestDto paciente){
        return ResponseEntity.ok(pacienteService.atualizar(id, paciente));

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try{
            pacienteService.deletar(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
