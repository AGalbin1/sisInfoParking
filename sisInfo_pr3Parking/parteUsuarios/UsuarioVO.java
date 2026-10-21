package sisInfo_pr3Parking.parteUsuarios;

public final class UsuarioVO {
	
	private String correo;
	private String nombre;
	private String contrasena;
	
	public UsuarioVO(String correo, String nombre, String contrasena) {
		super();
		this.correo = correo;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	

}
