package sisInfo_pr3Parking.chat;

;

// Clase de VO (incluye getters y setters del chat)

public final class ChatVO  {
	private int id;
	private String correoNormal;
	private String correoAdmin;
	
	public ChatVO(int id, String correoNormal, String correoAdmin) {
		this.id = id;
		this.correoNormal = correoNormal;
		this.correoAdmin = correoAdmin;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCorreoNormal() {
		return correoNormal;
	}
	
	public String getCorreoAdmin() {
		return correoAdmin;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCorreoNormal(String correoNormal) {
		this.correoNormal = correoNormal;
	}
	
	public void setCorreoAdmin(String correoAdmin) {
		this.correoAdmin = correoAdmin;
	}
	

}
