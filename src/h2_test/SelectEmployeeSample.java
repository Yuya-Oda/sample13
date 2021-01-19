package h2_test;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

/*
 * DAOを利用して、全従業員情報を検索するクラス
 */

public class SelectEmployeeSample {

	public static void main(String[] args) {
		// データベース接続時の指定する場合
		String judge = null;

		while(true) {
			System.out.println("データベースへの接続方法を選んでください");
			System.out.println("1：組み込みモード（非常駐モード）");
			System.out.println("2：サーバーモード（常駐モード）");
			System.out.println(">");

			judge = new java.util.Scanner(System.in).nextLine();
			if(judge.equals("1") || judge.equals("2")) {
				break;
			}
		}

		// Employeeテーブルの全レコードを取得
		EmployeeDAO empDAO = new EmployeeDAO(judge);
		List<Employee> empList = empDAO.findAll();

		// 取得したレコードの内容を出力
		for (Employee emp : empList) {
			System.out.println("ID:" + emp.getId());
			System.out.println("名前:" + emp.getName());
			System.out.println("年齢:" + emp.getAge() + "\n");
		}

	}

}
