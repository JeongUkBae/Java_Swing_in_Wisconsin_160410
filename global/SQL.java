package global;

public interface SQL {
	/**
	 * executeUpdate
	 */
	public String insert();
	public String update();
	public String delete();
	/**
	 *  executeQuery
	 */
	public String selectAll();
	public String selectOneBy(String s);
	public String count();
	/**
	 * Query Style
	 */
	public String make(String s);
}
