package sisInfo_pr3Parking.parteUsuarios;

public final class NormalVO {
	
	private String correo;
	private boolean alta;
	private int puntos;
	
	public NormalVO(String correo, boolean alta, int puntos) {
		super();
		this.correo = correo;
		this.alta = alta;
		this.puntos = puntos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
}
