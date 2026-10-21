package sisInfo_pr3Parking.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class ChatJdbc implements ChatDAO{

	private final DataSource dataSource;
	
	public ChatJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void crear(ChatVO chat) {
		// Consulta SQL -> sisinf_p3 es el nombre de la BBDD declarada en el sql cargada en docker.
		// Ambos valores que introducirá el sistema y devuelve el ID autoincrementado.
		String consultaCrear = "INSERT INTO sisinf_p3.chat (correo_normal, correo_admin) VALUES (?. ?) RETURNING id";
		try (Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(consultaCrear)) {
			
			ps.setString(1, chat.getCorreoNormal());
			ps.setString(2,  chat.getCorreoAdmin());
			
			try (ResultSet rs = ps.executeQuery()) {
				rs.next(); // autoincremento
				chat.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

	
	public Optional<ChatVO> encontrarPorID(int id) {
		String sqlBusqueda = "SELECT id, correo_normal, correo_admin FROM sisinf_p3.chat WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sqlBusqueda)) {
			
			ps.setInt(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {
				if(!rs.next()) {
					return Optional.empty();
				} else {
					return Optional.of(busqueda(rs));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Optional.empty(); // return sin sentido, ya se realiza la comprobación en el if y hace el salto
		
	}

	// Funcion para mostrar en la parte del admin todos los chats activos.
	public List<ChatVO> mostrarTodos() {
		String sqlMostrarAll = "SELECT id, correo_normal, correo_admin FROM sisinf_p3.chat ORDER BY id";
		List<ChatVO> lista = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sqlMostrarAll);
			 ResultSet rs = ps.executeQuery()) {
			// Ejecuto query guardada en result set.
			
			while (rs.next()) {
				// Carga en un ArrayList con los datos generales pero pasado por la función-
				//- búsqueda, la cual transforma la fila en tipo ChatVO.
				lista.add(busqueda(rs));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public boolean update(ChatVO chat) {
		String sqlUpdate = "UPDATE sisinf_p3.chat SET correo_normal = ?, correo_admin = ? WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
			
			ps.setString(1, chat.getCorreoNormal());
			ps.setString(2, chat.getCorreoAdmin());
			ps.setInt(3, chat.getId());
			
			return ps.executeUpdate() > 0; // Devuelve verdadero o falso en caso de ejecutar el statement.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; // En caso de que no ejecute el return del ps.
	}

	@Override
	public boolean delete(int id) {
		String sqlDel = "DELETE FROM sisinf_p3.chat WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sqlDel)) {
			
			ps.setInt(1, id);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// ResultSet devuelve una fila, lo transformo en un objeto de tipo ChatVO para-
	// -mostrarlo posteriormente en la búsqueda.
	public ChatVO busqueda(ResultSet rs) throws SQLException {
		return new ChatVO(
				rs.getInt("id"),
				rs.getString("correo_normal"),
				rs.getString("correo_admin"));
	}
	

}
