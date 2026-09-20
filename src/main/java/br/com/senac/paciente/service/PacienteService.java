package br.com.senac.paciente.service;

import br.com.senac.paciente.dto.PacienteRequestDto;
import br.com.senac.paciente.entities.Paciente;
import br.com.senac.paciente.repo.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente criar(PacienteRequestDto paciente) {
        this.validarPaciente(paciente);

        Paciente pacienteSaida = new Paciente();
        pacienteSaida.setNome(paciente.getNome());
        pacienteSaida.setIdade(paciente.getIdade());
        pacienteSaida.setDataEntrada(paciente.getDataEntrada());

        return pacienteRepository.save(pacienteSaida);
    }

    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        pacienteRepository.delete(paciente);
    }

    public Paciente listarPorId(Long id){
        Optional<Paciente> pacienteResult = pacienteRepository.findById(id);

        if (pacienteResult.isPresent()) {
            return pacienteResult.get();
        }

        throw new RuntimeException("Paciente não Encontrado!");
    }

    public Paciente atualizar(Long id, PacienteRequestDto paciente) {
        Optional<Paciente> pacienteResult = pacienteRepository.findById(id);

        this.validarPaciente(paciente);

        if (pacienteResult.isPresent()) {
            Paciente pacientePersist = pacienteResult.get();
            pacientePersist.setNome(paciente.getNome());
            pacientePersist.setIdade(paciente.getIdade());
            pacientePersist.setDataEntrada(paciente.getDataEntrada());
            pacientePersist.setId(id);

            return pacienteRepository.save(pacientePersist);
        }

        throw new RuntimeException("Paciente não encontrado!");
    }



    private void validarPaciente (PacienteRequestDto paciente) {
        if (paciente.getNome() == null || paciente.getNome().isBlank()) {
            throw new RuntimeException("Campo NOME é obrigatório");

        }

        if (paciente.getIdade() == null )  {
            throw new RuntimeException("Campo IDADE é obrigatório!");

        }

        if (paciente.getIdade() < 18 ) {
            throw new RuntimeException("Paciente menor de idade, entrada não permitida");
        }
    }
}