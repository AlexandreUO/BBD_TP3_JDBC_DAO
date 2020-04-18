import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T> {
	protected Connection connexion = null;
    
    public DAO(Connection connexion) {
        this.connexion = connexion;
    }
    
    public abstract T find(int id) throws SQLException;
    
    public abstract boolean create(T object);
    
    public abstract boolean update(T object);
    
    public abstract boolean delete(T object);
}
