package testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DbManager {

	private static Connection con = null; // sql
	private static Connection conn = null; // mysql

	// SQL Server
	public static void setDbConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(TestConfig.mysqldriver);
			con = DriverManager.getConnection(TestConfig.mysqUrl, TestConfig.mysqluserName, TestConfig.mysqlpassword);

			if (!con.isClosed())
				System.out.println("Successfully connected to SQL server");

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());

			// monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
			// TestConfig.subject+" - (Script failed with Error, Datamart database used for
			// reports, connection not established)", TestConfig.messageBody,
			// TestConfig.attachmentPath, TestConfig.attachmentName);
		}

	}

	// Reset Credit Card Connection
	public static void resetCreditCardConnection() throws SQLException, ClassNotFoundException {
		try {
			String sql = "UPDATE gtcrm.accounts SET max_subscribers = '15' WHERE \n"
					+ "(id in (select account_id from pm_status where pm_id in (select id from pm_cg where card_mask_4digit in (5614,3431,5606,7759,7767))))";

			Statement s = con.createStatement();
			int rs = s.executeUpdate(sql);

			if (!con.isClosed())
				System.out.println("Successfully Reset Credit Card Connection");

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());

		}

	}
	
	// Reset Credit Card Connection
	public static void resetPaypalConnection() throws SQLException, ClassNotFoundException {
		try {
			String sql = "UPDATE gtcrm.pm_status_current SET pm_id = '6800' where pm_id in (68009,68010)";

			Statement s = con.createStatement();
			int rs = s.executeUpdate(sql);

			if (!con.isClosed())
				System.out.println("Successfully Reset Paypal Connection");

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());

		}

	}

	public static String getSimNumberFromDB() throws SQLException {

		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(
				"select iccid from sims where status = 'available' and sim_type = 'trio' and dealer_id IS NULL limit 500,200");

		rs.next();
		return rs.getString("iccid");

	}

	public static ArrayList<String> simsList() throws SQLException {

		Statement s = con.createStatement();
		ResultSet rs = s
				.executeQuery("SELECT iccid FROM sims s\n" + "LEFT JOIN orders_items oi ON s.iccid = oi.item_barcode \n"
						+ "WHERE s.status = 'available' AND s.is_kosher = 0 AND oi.id IS NULL \n"
						+ "ORDER BY s.creation_date DESC LIMIT 100;");

		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		ArrayList<String> simsFreeList = new ArrayList<String>();
		while (rs.next()) {

			int i = 1;
			while (i <= colCount) {
				simsFreeList.add(rs.getString(i++));
			}
		}

		return simsFreeList;

	}

}
