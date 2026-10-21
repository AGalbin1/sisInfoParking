package sisInfo_pr3Parking.parteUsuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class AdminJdbc implements AdminDAO {
	
	private final DataSource dataSource;
	
	public AdminJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void crearAdmin(AdminVO admin) {
		String sqlCrea = "INSERT INTO sisinf_p3.admin (correo) VALUES (?)";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlCrea)) {
			
			ps.setString(1, admin.getCorreo());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Optional<AdminVO> busquedaPorID(String correo) {
		String sqlBusqueda = "SELECT correo FROM sisinf_p3.admin WHERE correo = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBusqueda)) {
			 
			ps.setString(1, correo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					return Optional.empty();
				} else {
					return Optional.of(busqueda(rs));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Optional.empty();
	}

	
	public List<AdminVO> mostrarTodos() {
		String sqlMostrado = "SELECT correo FROM sisinf_p3.admin ORDER BY correo";
		
		// Cargo todos los valores en una lista que se mostrará posteriormente.
		List<AdminVO> listaAdmins = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sqlMostrado);
			 ResultSet rs = ps.executeQuery()) {
			
			// Mientras sigue recorriendo la tabla, en caso de que llegue al tope salta.
			while (rs.next()) {
				listaAdmins.add(busqueda(rs)); // paso el ResultSet con la query a busqueda
				// transforma los datos en objetos, para cargarlos eficientemente en un ArrayList
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//Retorna todos los datos cargados en el arrayList.
		return listaAdmins;
	}

	@Override
	public boolean delete(String correo) {
		String sqlBorrado = "DELETE FROM sisinf_p3.admin WHERE correo = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBorrado)) {
			
			ps.setString(1, correo);
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//Transformo los datos muestreados en objeto.
	private AdminVO busqueda(ResultSet rs) throws SQLException {
		return new AdminVO ( 
			rs.getString("correo"));
		
	}
}
