package utilties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcconection {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

//		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://10.162.40.19:3306/gtcrm";
//		Class.forName(myDriver);
		Connection con = DriverManager.getConnection(myUrl, "admin", "windwater");
		Statement s = con.createStatement();

		ResultSet rs = s.executeQuery("select * from sims where status = 'available' and sim_type = 'trio'");

//		for (int i = 0; i < 10; i++) {
//			rs.next();
//		}

//		while (rs.next())
//			System.out.println(rs.getString("iccid"));

		rs.next();

		{
			System.out.println(rs.getString("iccid"));

		}

	}

}