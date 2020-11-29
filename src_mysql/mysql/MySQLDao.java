package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import controller.TableViewProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLDao {

	private static final String url = "jdbc:mariadb://localhost:3306/pocketmoney?";
	private static final String user = "root";
	private static final String password = "root";
	
	//���݂̂��������c���Ɖ�
	public String selectMoney() throws SQLException {
		final String SUM_QUERY = "SELECT"
							   + "(SELECT SUM(imcome) AS imcomes_sum FROM imcomes)" //����
							   + " - "												//-(�}�C�i�X)
							   + "(SELECT SUM(money) AS pays_sum FROM pays);";		//�x�o
		String money = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {money =  rs.getString(1);} //�c���̒l���󂯎��

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return money;
	}

	//imcomes�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate imcomed_at, String memo, int imcome) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO imcomes (imcomed_at, memo, imcome) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager
				.getConnection(url, user, password);
				
				PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {
			
			����.setObject(1, imcomed_at);
			����.setString(2, memo);
			����.setInt(3, imcome);
			����.executeUpdate();
			System.out.println(����);
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//pays�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager
				.getConnection(url, user, password);

			PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {

				����.setObject(1, paid_at);
				����.setString(2, memo);
				����.setInt(3, money);
				����.setInt(4, category_id);
				����.executeUpdate();
				System.out.println(����);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	//Main��ʂœ��͂��ꂽ���z���擾
	public String selectPayPrice() throws SQLException {
		final String SUM_QUERY = "SELECT money FROM pays ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("money");} //�l���󂯎��
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
	
	//AddImcome��ʂœ��͂��ꂽ���z���擾
	public String selectIncomePrice() throws SQLException {
		final String SUM_QUERY = "SELECT imcome FROM imcomes ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("imcome");} //�l���󂯎��

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
	
	//�h���������h������ʂ�TableView�ɕ\��������f�[�^�̎擾
	public ObservableList<TableViewProperty> selectTableViewIncomes() throws SQLException {
		final String SUM_QUERY = "SELECT imcomed_at, memo, imcome FROM imcomes ORDER BY imcomed_at;";
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

			while(rs.next()){
				TableViewProperty tbl = new TableViewProperty();
				tbl.setImcomed_at(rs.getString("imcomed_at"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setImcome(rs.getString("imcome"));
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	//�h�x�o�h������ʂ�TableView�ɕ\��������f�[�^�̎擾
	public ObservableList<TableViewProperty> selectTableViewPays() throws SQLException {
		final String SUM_QUERY = "SELECT paid_at, category, memo, money FROM pays JOIN categorys ON pays.category_id = categorys.id ORDER BY paid_at;";
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

			while(rs.next()){
				TableViewProperty tbl = new TableViewProperty();
				tbl.setPaid_at(rs.getString("paid_at"));
				tbl.setCategory(rs.getString("category"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setMoney(rs.getString("money"));
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	//�������ʂ̎擾
	public ObservableList<TableViewProperty> searchTableView(String search) throws SQLException {
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		String SERACH_QUERY = null;
		//���ꂼ��I�����ꂽ�������[�h�ɉ����āASQL���s
		switch(search) {
		
		case "�����Ƃɂ�����g������":
			SERACH_QUERY = "SELECT paid_at, SUM(money) FROM pays GROUP BY paid_at;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setPaid_at(rs.getString("paid_at"));
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case "��񓖂��蕽�ϊz":
			SERACH_QUERY = "SELECT AVG(money) FROM pays;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("AVG(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;

		case "3000�~�ȏ�̎x�o":
			SERACH_QUERY = "SELECT paid_at, category, memo, money FROM pays JOIN categorys ON pays.category_id = categorys.id WHERE money > 3000;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setPaid_at(rs.getString("paid_at"));
					tbl.setCategory(rs.getString("category"));
					tbl.setMemo(rs.getString("memo"));
					tbl.setMoney(rs.getString("money"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case "�J�e�S���[���Ƃ̍��v�z":
			SERACH_QUERY = "\r\n"
					+ "SELECT category, SUM(money) FROM pays JOIN categorys ON pays.category_id = categorys.id GROUP BY category_id;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setCategory(rs.getString("category"));
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case "�����̍��v�z":
			SERACH_QUERY = "SELECT SUM(money) FROM pays WHERE paid_at > DATE_SUB(NOW(), INTERVAL 1 MONTH) ORDER BY paid_at ASC;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;
			
		case "�����v�z":
			SERACH_QUERY = "SELECT SUM(money) FROM pays;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		return list;
	}
}
			