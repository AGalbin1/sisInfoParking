package sisInfo_pr3Parking.connBD;

import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;

public class ConexionBD {
	public static DataSource getDataSource() {
		PGSimpleDataSource ds = new PGSimpleDataSource();
		// Conexión a la BBDD de postgre - Localhost indica la zona de docker donde se publica o carga el contenedor.
		// 55432 -> Es el puerto en el que está habilitado (modificar para cada pc de manera individual)
		ds.setURL("jdbc:postgresql://localhost:55432/sisinf_grupo_23");
		
		// Usuario y Password por defecto, modificar en cada caso
		ds.setUser("postgres");
		ds.setPassword("postgres");
		return ds;
	}

}
