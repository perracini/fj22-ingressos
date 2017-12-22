package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.ingresso.model.descontos.Desconto;

@Entity
public class Ingresso {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	@Id
	@GeneratedValue
	private	Integer	id;
	@ManyToOne
	private	Sessao	sessao;
	@ManyToOne
	private	Lugar	lugar;
	private	BigDecimal	preco;
	@Enumerated(EnumType.STRING)
	private	TipoDeIngresso	tipoDeIngresso;

	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
		this.sessao = sessao;
		this.tipoDeIngresso = tipoDeIngresso;
		this.preco = this.tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	/**
	 * @deprecated hibernate only
	 */
	public Ingresso() {
	}

	public Ingresso(Sessao sessao, Desconto tipoDeDesconto) {
		this.sessao = sessao;
		this.preco = tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
	}

	public Sessao getSessao() {
		return sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
