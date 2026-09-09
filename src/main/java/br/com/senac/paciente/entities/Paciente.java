package br.com.senac.paciente.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Integer idade;
    @Column
    private LocalDate dataEntrada;

    public Paciente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate data) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
