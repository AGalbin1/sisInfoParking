package sisInfo_pr3Parking.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalTime;

import javax.sql.DataSource;

public class MensajeJdbc implements MensajeDAO{
	
private final DataSource dataSource;
	
	public MensajeJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void crear(MensajeVO mensaje) {
		String sqlCrear = "INSERT INTO sisinf_p3.mensaje (contenido, fecha, hora, id_chat, correo_usuario) VALUES (?, ?, ?, ?, ?) RETURNING id";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlCrear)) {
			
			// Declaración de los valores ? dentro de la consulta sql
			ps.setString(1, mensaje.getContenido());
			ps.setObject(2, mensaje.getFecha());
			ps.setObject(3, mensaje.getHora());
			ps.setInt(4, mensaje.getIdChat());
			ps.setString(5, mensaje.getCorreoUsuario());
			
			try (ResultSet rs = ps.executeQuery()) {
				// Itera dentro de el id de la tabla
				rs.next();
				// setea el campo id de mensaje con el id autoincrementado
				mensaje.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Optional<MensajeVO> encontrarPorID(int id) {
		String sqlBusqueda = "SELECT id, contenido, fecha, hora, id_chat, correo_usuario FROM sisinf_p3.mensaje WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlBusqueda)) {
			
			ps.setInt(1, id);
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

	
	public List<MensajeVO> mostrarTodos() {
		String sqlListado = "SELECT id, contenido, fecha, hora, id_chat, correo_usuario FROM sisinf_p3.mensaje ORDER BY id";
		List<MensajeVO> listaMensajes = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sqlListado);
			 ResultSet rs = ps.executeQuery()) {
			 
			while (rs.next()) {
				listaMensajes.add(busqueda(rs));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listaMensajes;
	}

	
	public boolean update(MensajeVO mensaje) {
		String sqlActualizar = "INSERT INTO sisinf_p3.mensaje (contenido, fecha, hora, id_chat, correo_usuario) VALUES (?, ?, ?, ?, ?) RETURNING id";
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlActualizar)) {
			
			// Declaración de los valores ? dentro de la consulta sql
			ps.setString(1, mensaje.getContenido());
			ps.setObject(2, mensaje.getFecha());
			ps.setObject(3, mensaje.getHora());
			ps.setInt(4, mensaje.getIdChat());
			ps.setString(5, mensaje.getCorreoUsuario());
			ps.setInt(6, mensaje.getId());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	public MensajeVO busqueda(ResultSet rs) throws SQLException {
		return new MensajeVO(
				rs.getInt("id"),
				rs.getString("contenido"),
				rs.getObject("fecha", LocalDate.class),
				rs.getObject("hora", LocalTime.class),
				rs.getInt("id_chat"),
				rs.getString("correo_usuario"));
	}
	

}
