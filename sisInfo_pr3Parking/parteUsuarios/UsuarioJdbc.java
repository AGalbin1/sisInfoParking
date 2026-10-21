package sisInfo_pr3Parking.parteUsuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class UsuarioJdbc implements UsuarioDAO {
	
	private final DataSource dataSource;
	
	public UsuarioJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void crearUsuario(UsuarioVO usuario) {
		String sqlCrear = "INSERT INTO sisinf_p3.usuario (correo, nombre, contrasena) VALUES (?, ?, ?)";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlCrear)) {
			
			// Declaración de los valores ? dentro de la consulta sql
			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getContrasena());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delete(int id) {
		String sqlBorrado = "DELETE FROM sisinf_p3.mensaje WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBorrado)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return false;
	}
	
	// ResultSet devuelve una fila, lo transformo en un objeto de tipo MensajeVO para-
	// -mostrarlo posteriormente en la búsqueda.
	public UsuarioVO busqueda(ResultSet rs) throws SQLException {
		return new UsuarioVO(
				rs.getString("correo"),
				rs.getString("nombre"),
				rs.getString("contrasena"));
	}


	public Optional<UsuarioVO> busquedaPorID(String correo) {
		String sqlBusqueda = "SELECT correo, nombre, contrasena FROM sisinf_p3.usuario WHERE correo = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBusqueda)) {
			
			ps.setString(1, correo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if( !rs.next()) { 
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


	@Override
	public boolean update(UsuarioVO usuario) {
		String sqlActualizar = "UPDATE sisinf_p3.usuario SET nombre = ?, contrasena = ? WHERE correo = ?";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlActualizar)) {
			
			// Declaración de los valores ? dentro de la consulta sql
			ps.setString(1,  usuario.getNombre());
			ps.setString(2, usuario.getContrasena());
			ps.setString(3, usuario.getCorreo());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean delete(String correo) {
		String sqlBorrado = "DELETE FROM sisinf_p3.usuario WHERE correo = ?";
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


	@Override
	public List<UsuarioVO> mostrarTodos() {
		String sqlMostrado = "SELECT correo, nombre, contrasena FROM sisinf_p3.usuario ORDER BY correo";
		List<UsuarioVO> listaUsuarios = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlMostrado);
			ResultSet rs = ps.executeQuery()) {
		
			while(rs.next()) {
				listaUsuarios.add(busqueda(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	

}
