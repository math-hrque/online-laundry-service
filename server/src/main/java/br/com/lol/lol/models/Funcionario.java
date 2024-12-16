package br.com.lol.lol.models;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="funcionario")
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_funcionario")
    private Long idFuncionario;

    @Temporal(TemporalType.DATE)
    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
