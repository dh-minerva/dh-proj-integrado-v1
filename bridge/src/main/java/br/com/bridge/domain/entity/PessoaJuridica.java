package br.com.bridge.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_empresas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PessoaJuridica extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "sobre_instituicao")
	private String sobreInstituicao;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "empresas")
	private Set<Turma> turmasOfertadas = new HashSet<>();
	
}