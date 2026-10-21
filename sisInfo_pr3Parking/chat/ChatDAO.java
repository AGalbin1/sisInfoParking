package sisInfo_pr3Parking.chat;

import java.util.List;
import java.util.Optional;

public interface ChatDAO {
	void crear(ChatVO chat) ;
	
	Optional<ChatVO> encontrarPorID(int id) ;
	
	List<ChatVO> mostrarTodos();
	
	// El update y el delete lo he puesto como tipo booleano, solo en caso de que no existan.
	// Para el borrado, solo paso el ID del chat.
	//Para la actualización paso todos los valores
	boolean update(ChatVO chat);
	
	boolean delete(int id);
	
}
