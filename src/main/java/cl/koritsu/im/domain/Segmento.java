package cl.koritsu.im.domain;

import java.util.Set;

public class Segmento {

	Long id;
	String nombre;
	Stakeholder stakeholder;
	Set<SubSegmento> subsegmento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Stakeholder getStakeholder() {
		return stakeholder;
	}
	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}
	public Set<SubSegmento> getSubsegmento() {
		return subsegmento;
	}
	public void setSubsegmento(Set<SubSegmento> subsegmento) {
		this.subsegmento = subsegmento;
	}
}
