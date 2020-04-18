import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeptDAO extends DAO<Dept>{
	
	public DeptDAO(Connection connexion) { 
		super(connexion); 
	}
	
	@Override 
	public Dept find(int id) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( "SELECT * from dept where deptno = ? " );
		preparedStatement.setInt( 1, id ); 
		ResultSet results = preparedStatement.executeQuery(); 
		if(results.next()) {
			return new Dept(results.getInt("deptno"),
					results.getString("dname"),
					results.getString("loc"));
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean create(Dept object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override 
	public boolean update(Dept object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override 
	public boolean delete(Dept object) {
		// TODO Auto-generated method stub
		return false;
	}
}
