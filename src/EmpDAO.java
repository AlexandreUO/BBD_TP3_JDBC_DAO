import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class EmpDAO extends DAO<Emp>{
	public EmpDAO(Connection connexion) { 
		super(connexion); 
	}
	
	@Override 
	public Emp find(int id) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( "SELECT * from emp where empno = ? " );
		preparedStatement.setInt( 1, id ); 
		ResultSet results = preparedStatement.executeQuery(); 
		if(results.next()) {
			return new Emp(results.getLong("empno"),
					results.getString("ename"),
					results.getString("efirst"),
					results.getString("job"),
					find(results.getInt("mgr")),
					results.getDate("hiredate"),
					results.getInt("sal"),
					results.getInt("comm"),
					results.getInt("tel"),
					new DeptDAO(connexion).find(results.getInt("deptno")));
		} else {
			return null;
		}
	}


	@Override
	public boolean create(Emp object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Emp object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Emp object) {
		// TODO Auto-generated method stub
		return false;
	}
}
