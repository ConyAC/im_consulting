package cl.koritsu.im.domain.enums;

public enum Permiso {

	CREAR_EMPRESA(1, "Create Company"),
	CREAR_USUARIO(2,"Create User"),
	;
	
	int i;
	String description;
	
	private Permiso(int i, String description) {
		this.i = i;
		this.description = description;
	}
	
	public int getCorrelative(){
		return i;
	}
	
	public static Permiso getPermiso(int i){
		for(Permiso e : Permiso.values())
			if(e.getCorrelative() == i )
				return e;
		throw new RuntimeException("Permiso Invalido");
	}

	@Override
	public String toString(){
		return description;
	}
}
