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
		//�擾���ʂ��i�[����String�ϐ�
		String money = "";
		
		//��O����
		try (
	         //�f�[�^�x�[�X�ڑ�
			 Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
		     //ResultSet����String�ϐ��փZ�b�g
			 if(rs.next()) {money =  rs.getString(1);}

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//�Ăяo�����֕Ԃ�
		return money;
	}

	//imcomes�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate imcomed_at, String memo, int imcome) throws SQLException {
		
		//SQL���̓J�������Ƀv���[�X�z���_�[��������`�ŏ���
		final String INSERT_QUERY = "INSERT INTO imcomes (imcomed_at, memo, imcome) "
				                  + "VALUES (?, ?, ?)";
		//��O����
		try (
            //�f�[�^�x�[�X�ڑ�
            Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {
			
			//���̓t�H�[������̃p�����[�^���v���[�X�z���_�Ƀo�C���h���A�f�[�^�x�[�X�֑}��
			ps.setObject(1, imcomed_at);
			ps.setString(2, memo);
			ps.setInt(3, imcome);
			ps.executeUpdate();
			System.out.println(ps);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//pays�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		
		//SQL���̓J�������Ƀv���[�X�z���_�[��������`�ŏ���
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) "
				                  + "VALUES (?, ?, ?, ?)";
		//��O����
		try (
	            //�f�[�^�x�[�X�ڑ�
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {

			    //���̓t�H�[������̃p�����[�^���v���[�X�z���_�Ƀo�C���h���A�f�[�^�x�[�X�֑}��
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
	
	//imcomes�e�[�u�����O�f�[�^�폜
	public static void deleteImcomesRecord() throws SQLException {
		
		//SQL���̓J�������Ƀv���[�X�z���_�[��������`�ŏ���
		final String INSERT_QUERY = "DELETE FROM imcomes "
								  + "ORDER BY id DESC "
								  + "LIMIT 1;";
		//��O����
		try (
				//�f�[�^�x�[�X�ڑ�
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {
			
			����.executeUpdate();
			System.out.println(����);
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//pays�e�[�u�����O�f�[�^�폜
	public static void deletePaysRecord() throws SQLException {
		
		//SQL���̓J�������Ƀv���[�X�z���_�[��������`�ŏ���
		final String INSERT_QUERY = "DELETE FROM pays "
				                  + "ORDER BY id DESC "
				                  + "LIMIT 1;";
		//��O����
		try (
	            //�f�[�^�x�[�X�ڑ�
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {

				����.executeUpdate();
				System.out.println(����);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Main��ʂœ��͂��ꂽ���z���擾
	public String selectPayPrice() throws SQLException {
		final String SUM_QUERY = "SELECT money "
						 	   + "FROM pays "
							   + "ORDER BY id DESC "
							   + "LIMIT 1;";
					
		//�擾���ʂ��i�[����String�ϐ�
		String newPrice = "";
		
		//��O����
		try (
	            //�f�[�^�x�[�X�ڑ�
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			    //ResultSet����String�ϐ��փZ�b�g
			    if(rs.next()) {newPrice = rs.getString("money");}
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//�Ăяo�����֕Ԃ�
		return newPrice;
	}
	
	//AddImcome��ʂœ��͂��ꂽ���z���擾
	public String selectIncomePrice() throws SQLException {
		final String SUM_QUERY = "SELECT imcome "
							   + "FROM imcomes "
							   + "ORDER BY id DESC "
							   + "LIMIT 1;";
		
		//�擾���ʂ��i�[����String�ϐ�
		String newPrice = "";
		
		//��O����
		try (
	         //�f�[�^�x�[�X�ڑ�
			 Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
		    //ResultSet����String�ϐ��փZ�b�g
			 if(rs.next()) {newPrice = rs.getString("imcome");}

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//�Ăяo�����֕Ԃ�
		return newPrice;
	}
	
	//�h���������h������ʂ�TableView�ɕ\��������f�[�^�̎擾
	public ObservableList<TableViewProperty> selectTableViewIncomes() throws SQLException {
		final String SUM_QUERY = "SELECT imcomed_at, memo, imcome "
							   + "FROM imcomes "
							   + "ORDER BY imcomed_at;";
		
        //�v���p�e�B�N���X�̃C���X�^���X���i�[����list
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
		//��O����
		try (
	            //�f�[�^�x�[�X�ڑ�
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
            //ResultSet����v���p�e�B�N���X�̃t�B�[���h�փZ�b�g
			while(rs.next()){
				
				//�v���p�e�B�N���X�̃C���X�^���X����
				TableViewProperty tbl = new TableViewProperty();
				
                //�t�B�[���h�ɃZ�b�g
				tbl.setImcomed_at(rs.getString("imcomed_at"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setImcome(rs.getString("imcome"));
				//�C���X�^���X���i�[
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//�Ăяo�����֕Ԃ�
		return list;
	}
	
	//�h�x�o�h������ʂ�TableView�ɕ\��������f�[�^�̎擾
	public ObservableList<TableViewProperty> selectTableViewPays() throws SQLException {
		final String SUM_QUERY = "SELECT paid_at, category, memo, money "
							   + "FROM pays "
							   + "JOIN categorys "
							   + "ON pays.category_id = categorys.id "
							   + "ORDER BY paid_at;";
		
        //�v���p�e�B�N���X�̃C���X�^���X���i�[����list
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
        //��O����
        try (
            //�f�[�^�x�[�X�ڑ�
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

            //ResultSet����v���p�e�B�N���X�̃t�B�[���h�փZ�b�g
			while(rs.next()){
				
				//�v���p�e�B�N���X�̃C���X�^���X����
				TableViewProperty tbl = new TableViewProperty();
                //�t�B�[���h�ɃZ�b�g
				tbl.setPaid_at(rs.getString("paid_at"));
				tbl.setCategory(rs.getString("category"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setMoney(rs.getString("money"));
				//�C���X�^���X���i�[
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//�Ăяo�����֕Ԃ�
		return list;
	}
	
	/**
	 * searchTableView���\�b�h
	 * ������̈������󂯎��A�ǂ̌�������������
	 * �Ή�����SOL�ƃo�C���h
	 * �N�G�����s���A���ʂ�ObservableList�^list�ŕԂ�
	 */
	public ObservableList<TableViewProperty> searchTableView(String search) throws SQLException {
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		String SERACH_QUERY = null;
		
		//���ꂼ��I�����ꂽ�������[�h�ɉ����āASQL���s
		switch(search) {
		
		case "�����Ƃɂ�����g������":
			SERACH_QUERY = "SELECT paid_at, "
						 + "SUM(money) "
						 + "FROM pays "
						 + "GROUP BY paid_at;";
			
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
				e.printStackTrace();
			}
			break;
			
		case "��񓖂��蕽�ϊz":
			SERACH_QUERY = "SELECT AVG(money) "
						 + "FROM pays;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("AVG(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;

		case "3000�~�ȏ�̎x�o":
			SERACH_QUERY = "SELECT paid_at, category, memo, money "
						 + "FROM pays "
						 + "JOIN categorys "
						 + "ON pays.category_id = categorys.id "
						 + "WHERE money > 3000;";
			
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
				e.printStackTrace();
			}
			break;
		
		case "�J�e�S���[���Ƃ̍��v�z":
			SERACH_QUERY = "SELECT category, "
						 + "SUM(money) "
						 + "FROM pays "
						 + "JOIN categorys "
						 + "ON pays.category_id = categorys.id "
						 + "GROUP BY category_id;";
			
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
				e.printStackTrace();
			}
			break;

		case "�����̍��v�z":
			SERACH_QUERY = "SELECT SUM(money) "
						 + "FROM pays "
						 + "WHERE paid_at > "
						 + "DATE_SUB(NOW(), INTERVAL 1 MONTH) "
						 + "ORDER BY paid_at ASC;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "�����v�z":
			SERACH_QUERY = "SELECT SUM(money) "
					     + "FROM pays;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		return list;
	}
}
			