package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

/*
 * Employeeテーブルを担当するDAO
 */

public class EmployeeDAO {

	// データベース接続に使用する情報
//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Documents/data/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	private String JDBC_URL = "jdbc:h2:"; // データベース接続時の指定する場合

	public EmployeeDAO(String judge) { // データベース接続時の指定する場合
		switch(judge) {
		case "1":
			JDBC_URL += "file:~/Documents/data/example";
			break;

		case "2":
			JDBC_URL += "tcp://localhost/~/Documents/data/example";

		}
	}

	public List<Employee> findAll(){
		List<Employee> empList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

			// SELECT文を準備
			String sql = "SELECT ID, NAME, AGE FROM EMPLOYEE";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表を格納されたレコード内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				// レコード値の取得
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");

				// 取得した値をEmployeeインスタンスに格納
				Employee employee = new Employee(id, name, age);

				// ArrayListインスタンスにEmployeeインスタンスを追加
				empList.add(employee);
			}
		} catch (SQLException e) {
			// 接続、SQL処理失敗時の処理
			e.printStackTrace();
			return null;
		}

		return empList;
	}
}
