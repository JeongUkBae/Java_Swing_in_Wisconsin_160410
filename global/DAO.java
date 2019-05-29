package global;

import java.util.ArrayList;
import java.util.List;

public abstract class DAO {
	/**
	 * executeUpdate
	 */
	public int insert(Object o){return 0;}
	public int update(Object o){return 0;}
	public int delete(Object o){return 0;}
	/**
	 *  executeQuery
	 */
	public List selectAll(){return new ArrayList();}
	public Object selectOneBy(String s){return null;}
	public int count(){return 0;}
}
