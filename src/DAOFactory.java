import java.sql.Connection;

public class DAOFactory {
	Connection connexion;
	
	public DAOFactory(Connection connexion) {
		this.connexion = connexion;
	}
	
	public DAO getDeptDAO() {
		return new DeptDAO(connexion);
	}
	
	public DAO getEmpDAO() {
		return new EmpDAO(connexion);
	}
	
	public DAO getdpendentsDAO() {
		return null;
	}
	
	public DAO getBonusDAO() {
		return null;
	}
}
