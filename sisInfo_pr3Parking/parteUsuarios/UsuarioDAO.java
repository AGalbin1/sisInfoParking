package sisInfo_pr3Parking.parteUsuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
	
	void crearUsuario(UsuarioVO usuario);
	
	Optional<UsuarioVO> busquedaPorID(String correo);
	
	List<UsuarioVO> mostrarTodos();
	
	// Si existe devuelve true, tanto el update como el delete, igual que la parte de Mensajes y Chat
	
	boolean update(UsuarioVO usuario);
	boolean delete(String correo);
}
