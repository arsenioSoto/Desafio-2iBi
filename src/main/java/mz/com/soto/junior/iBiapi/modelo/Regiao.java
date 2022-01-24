package mz.com.soto.junior.iBiapi.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regiao")
public class Regiao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "codigo_subregiao")
	private SubRegiao subRegiao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SubRegiao getSubRegiao() {
		return subRegiao;
	}

	public void setSubRegiao(SubRegiao subRegiao) {
		this.subRegiao = subRegiao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regiao other = (Regiao) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
