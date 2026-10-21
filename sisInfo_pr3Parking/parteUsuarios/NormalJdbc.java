package sisInfo_pr3Parking.parteUsuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class NormalJdbc implements NormalDAO {
	
	private final DataSource dataSource;
	
	public NormalJdbc (DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void crearUsuarioNormal(NormalVO normal) {
		String sqlCargaUsuario = "INSERT INTO sisinf_p3.normal (correo, alta, puntos) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlCargaUsuario)) {
			
			ps.setString(1, normal.getCorreo());
			ps.setBoolean(2, normal.isAlta());
			ps.setInt(3, normal.getPuntos());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public Optional<NormalVO> busquedaPorID(String correo) {
		String sqlBusqueda = "SELECT correo, alta, puntos FROM sisinf_p3.normal WHERE correo = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBusqueda)) {
			
			ps.setString(1, correo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if(! rs.next()) {
					return Optional.empty();
				} else {
					return Optional.of(busqueda(rs));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<NormalVO> busquedaTotal() {
		String sqlTodos = "SELECT correo, alta, puntos FROM sisinf_p3.normal ORDER BY correo";
		List<NormalVO> listaUsuarios = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlTodos);
			 ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				listaUsuarios.add(busqueda(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean update(NormalVO normal) {
		String sqlActualizar = "UPDATE sisinf_p3.normal SET alta = ?, puntos = ?, WHERE correo = ?";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlActualizar)) {
			ps.setBoolean(1, normal.isAlta());
			ps.setInt(2, normal.getPuntos());
			ps.setString(3, normal.getCorreo());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean delete(String correo) {
		String sqlBorrado = "DELETE FROM sisinf_p3.normal WHERE correo = ?";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBorrado)) {
			
			ps.setString(1, correo);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	private NormalVO busqueda(ResultSet rs) throws SQLException {
		return new NormalVO (
				rs.getString("correo"),
				rs.getBoolean("alta"),
				rs.getInt("puntos"));
				
	}

}
