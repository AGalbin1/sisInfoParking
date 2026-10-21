package sisInfo_pr3Parking.chat;

import java.time.LocalDate;
import java.time.LocalTime;

// Clase de getter y setters de Mensaje (declarado usando los datos de la tabla)

public final class MensajeVO {
	
	private String contenido;
	private LocalDate fecha;
	private LocalTime hora;
	private int idChat;
	private String correoUsuario;
	private int id;

	public MensajeVO(String contenido, LocalDate fecha, LocalTime hora, int idChat, String correoUsuario, int id) {
		this.contenido = contenido;
		this.fecha = fecha;
		this.hora = hora;
		this.idChat = idChat;
		this.correoUsuario = correoUsuario;
		this.id = id;
	}
	// Constructor generado por el Eclipse para solventar el error de las fechas.
	// No dejaba declarar un LocalDate.class para poder pasarlo por el constructor.
	public MensajeVO(int int1, String string, Object object, Object object2, int int2, String string2) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public int getIdChat() {
		return idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
}
