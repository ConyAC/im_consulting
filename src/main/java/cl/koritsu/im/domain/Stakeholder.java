package cl.koritsu.im.domain;

import java.util.Set;

public class Stakeholder {
	
	Long id;
	String nombre;
	Set<Segmento> segmento;
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
	public Set<Segmento> getSegmento() {
		return segmento;
	}
	public void setSegmento(Set<Segmento> segmento) {
		this.segmento = segmento;
	}
	public Set<SubSegmento> getSubsegmento() {
		return subsegmento;
	}
	public void setSubsegmento(Set<SubSegmento> subsegmento) {
		this.subsegmento = subsegmento;
	}
	
}
