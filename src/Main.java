import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		try {
            Class.forName( "oracle.jdbc.OracleDriver" );
            // Class.forName("oracle.jdbc.OracleDriver") ;
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
		
		String url = "jdbc:oracle:thin:sys/0000@localhost:1521:xe";
        String user = "sys as sysdba";
        String pass = "0000";
        Connection connexion = null;
        
        try {
            connexion = DriverManager.getConnection( url, user, pass );

            /* Requests to bdd will be here */
            System.out.println("Bdd Connected");
            //displayDepartment(connexion);
            /*Scanner sc = new Scanner(System.in);
            System.out.println("Input empno");
            int empno = sc.nextInt();
            System.out.println("Input newDeptno");
            int newDeptno = sc.nextInt();
            moveDepartment(connexion, empno, newDeptno);*/
            //displayTable(connexion, "emp");
            
            /*DAO<Dept> departmentDao = new DeptDAO(connexion);
            Dept dept20 = departmentDao.find(20);
            System.out.println(dept20); */// Don't forget to add toString() method in Dept.java to be able to pass it to System.out.println.
            
            /*DAO<Emp> empDAO = new EmpDAO(connexion);
            Emp emp7369 = empDAO.find(7369);
            System.out.println(emp7369);*/
            
            DAOFactory daoFactory = new DAOFactory(connexion);
            DAO<Dept> daoDept = daoFactory.getDeptDAO();
            Dept dept20 = daoDept.find(20);
            System.out.println(dept20);
            DAO<Emp> daoEmp = daoFactory.getEmpDAO();
            Emp emp7369 = daoEmp.find(7369);
            System.out.println(emp7369);
            
            connexion.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connexion != null )
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                    ignore.printStackTrace();
                }
        }
	}
	
	public static void displayDepartment(Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        ResultSet resultat = statement.executeQuery( "SELECT deptno, dname, loc FROM dept" );
        
        while ( resultat.next() ) {
            int deptno = resultat.getInt( "deptno");
            String dname = resultat.getString( "dname" );
            String loc = resultat.getString( "loc" );
            
            System.out.println("Department " + deptno + " is for " 
                    + dname + " and located in " + loc);
        }
        resultat.close();
    }
	
	public static void moveDepartment(Connection connexion, int empno, int newDeptno) throws SQLException {
		String query = "update emp set deptno = ? where empno = ? ";
		PreparedStatement updateDeptNo = connexion.prepareStatement(query);
		updateDeptNo.setInt(1, newDeptno);
		updateDeptNo.setInt(2, empno);
	    int resultat = updateDeptNo.executeUpdate();
	    System.out.println(resultat + " modified rows");
	}
	
	public static void displayTable(Connection connexion, String tblName) throws SQLException {
		String query =  "select * from " + tblName;
		PreparedStatement statement = connexion.prepareStatement(query);
		ResultSet resultat = statement.executeQuery();
		ResultSetMetaData resultatMD = resultat.getMetaData();
		int nbCol = resultatMD.getColumnCount();
		for(int i = 1; i <= nbCol; i++) {
			System.out.print(resultatMD.getColumnName(i) + " | ");
		}
		System.out.println();
		while(resultat.next()) {
			for(int i = 1; i <= nbCol; i++) {
				System.out.print(resultat.getString(i) + " | ");
			}
			System.out.println();
		}
	}
}
