package sisInfo_pr3Parking.parteUsuarios;

// Tabla admin solo tiene campo correo.

public final class AdminVO {
	
	private String correo;
	
	public AdminVO(String correo) {
		this.correo = correo;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
