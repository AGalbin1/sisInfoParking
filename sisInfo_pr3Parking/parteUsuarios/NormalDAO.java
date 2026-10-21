package sisInfo_pr3Parking.parteUsuarios;

import java.util.List;
import java.util.Optional;

public interface NormalDAO {
	
	void crearUsuarioNormal(NormalVO normal);
	
	Optional<NormalVO> busquedaPorID(String correo);
	
	List<NormalVO> busquedaTotal();
	
	// Misma ocurrencia que en usuario con el update y el delete.
	
	boolean update(NormalVO normal);
	boolean delete(String correo);
}
