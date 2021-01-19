package h2_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployeeSample {

	public static void main(String[] args) {
		// データベース接続時の指定
		String JDBC_URL = "jdbc:h2:"; // 使用DB ：H2 DB
		boolean judge = true;
		while(judge) {
			System.out.println("データベースへの接続方法を選んでください");
			System.out.println("1：組み込みモード（非常駐モード）");
			System.out.println("2：サーバーモード（常駐モード）");
			System.out.println(">");
			String select = new java.util.Scanner(System.in).nextLine();

			switch(select) {
			case "1":
				JDBC_URL += "file:~/Documents/data/example";
				judge = false; break;

			case "2":
				JDBC_URL += "tcp://localhost/~/Documents/data/example";
				judge = false; break;
			}


		}
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, "sa", "")){
															// ↑ 接続先DB/ユーザー名/パスワード
			//SERECT文を準備
			String sql = "SELECT ID,NAME,AGE FROM EMPLOYEE";
			// SQLをDBに届けるPreparedStatementインスタンスを取得する
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SERECTを実行し、結果表(ResultSet)を取得
			// ResultSetインスタンスにSERECT文の結果が格納される
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			// 結果を取り出し対象レコードを1つ進める
			while (rs.next()) {
				// 取り出し対象レコードの各列の値を取得する
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");

				// 取得したデータを出力
				System.out.println("ID:" + id);
				System.out.println("NAME:" + name );
				System.out.println("AGE:" + age + "\n");

			}

		}catch (SQLException e){
			// 接続やSQL処理失敗時の処理
			e.printStackTrace();
		}
	}

}
