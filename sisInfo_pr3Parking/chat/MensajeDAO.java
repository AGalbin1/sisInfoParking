package sisInfo_pr3Parking.chat;

import java.util.List;
import java.util.Optional;

public interface MensajeDAO {
	
	void crear(MensajeVO mensaje) ;
	
	Optional<MensajeVO> encontrarPorID(int id) ;
	
	List<MensajeVO> mostrarTodos();
	
	// El update y el delete lo he puesto como tipo booleano, solo en caso de que no existan.
	// Para el borrado, solo paso el ID del mensaje.
	//Para la actualización paso todos los valores
	boolean update(MensajeVO mensaje);
	
	boolean delete(int id);
	
}
