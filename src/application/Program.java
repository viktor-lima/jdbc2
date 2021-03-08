package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			conn = DB.getConnection();
			 
			st = conn.createStatement();
			
			rs = st.executeQuery("select seller.id, seller.Name, department.Name from seller\r\n"
					+ "  left join department \r\n"
					+ "  on seller.DepartmentId = department.id");
			
			while(rs.next()) {
				System.out.println(rs.getInt("seller.id") + "| " + rs.getString("seller.Name") + " , " + rs.getString("department.Name"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
 