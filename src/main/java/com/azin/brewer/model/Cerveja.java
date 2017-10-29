package com.azin.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.azin.brewer.validation.SKU;

@Entity
@Table(name="cerveja")
public class Cerveja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotBlank(message="O campo sku e obrigatório")
	@SKU
	private String sku;
	
	@NotBlank(message="O campo nome e obrigatório")
	private String nome;
	
	@Size(min = 1, max = 50)
	@NotBlank(message="O campo descricao e obrigatório")
	private String descricao;
	
	@NotBlank(message="O campo valor e obrigatório")
	private BigDecimal valor;
	
	@Column(name="teor_alcoolico")
	@NotBlank(message="O campo teor alcoolico e obrigatório")
	@DecimalMax(value="100.0" , message="A teor alcoolico  deve ser menor ou igual a 100")
	private BigDecimal teorAlcoolico;
	
	@NotBlank(message="O campo comissao e obrigatório")
	@DecimalMax(value="100.0" , message="A comissao deve ser menor ou igual a 100")
	private BigDecimal comissao;
	
	@Column(name="quantidade_estoque")
	@Max(value=9999, message="A quantidade deve ser menor que 9999")
	@NotBlank(message="O campo quantidade estoque e obrigatório")
	private Integer quantidadeEstoque;

	@Enumerated(EnumType.STRING)
	@NotBlank(message="O campo origem e obrigatório")
	private Origem origem;
	
	@Enumerated(EnumType.STRING)
	@NotBlank(message="O campo sabor e obrigatório")
	private Sabor sabor;
	
	@ManyToOne()
	@JoinColumn(name="codigo_estilo")
	@NotBlank(message="O campo estilo e obrigatório")
	private Estilo estilo;
	
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
