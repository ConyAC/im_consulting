package cl.koritsu.im.domain;

public class Respondente {
	
	Long id;
	String stakeholder;
	String segmento;
	String subsegmento;
	String criticidad;
	String actitud;
	String type;
	String observacion;
	
	public Respondente(Long id, String stakeholder, String segmento, String subsegmento, String criticidad,
			String actitud, String type, String observacion) {
		super();
		this.id = id;
		this.stakeholder = stakeholder;
		this.segmento = segmento;
		this.subsegmento = subsegmento;
		this.criticidad = criticidad;
		this.actitud = actitud;
		this.type = type;
		this.observacion = observacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStakeholder() {
		return stakeholder;
	}
	public void setStakeholder(String stakeholder) {
		this.stakeholder = stakeholder;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getSubsegmento() {
		return subsegmento;
	}
	public void setSubsegmento(String subsegmento) {
		this.subsegmento = subsegmento;
	}
	public String getCriticidad() {
		return criticidad;
	}
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	public String getActitud() {
		return actitud;
	}
	public void setActitud(String actitud) {
		this.actitud = actitud;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
