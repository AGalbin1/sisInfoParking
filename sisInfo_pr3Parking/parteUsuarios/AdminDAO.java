package sisInfo_pr3Parking.parteUsuarios;

import java.util.List;
import java.util.Optional;

public interface AdminDAO {
	
	void crearAdmin(AdminVO admin);
	
	Optional<AdminVO> busquedaPorID(String correo);
	
	
	//En caso de que haya mas 1 administrador dado de alta.
	List<AdminVO> mostrarTodos();
	
	//Update no tiene sentido realizarlo, ya que solo tiene 1 clave.
	
	boolean delete(String correo);
}
