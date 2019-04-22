package DBUtil;

import java.util.UUID;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mysql.jdbc.Connection;

import Model.User;

public class DBOperation {

	public static String insertIntoUser(String nickname,String phoneNum,String password) throws SQLException {
		Connection connection = (Connection) DBUtils.getConnection();
		QueryRunner queryRunner=new QueryRunner();//链接数据库

		String state = "failure";
		UUID uuid = UUID.randomUUID();
		String id = uuid+"";
		String sql="insert into t_user(id,nickname,phoneNum,password) values (?,?,?,?)";
		Object[] params= {id,nickname,phoneNum,password};
		int result=queryRunner.update(connection,sql,params);
		if(result<=0) {
			System.out.println("注册失败");
		}else {
			state = "success";
		}
		DBUtils.close(null, null, connection);
		return state;
	}
	
	public static User getUser(String account,String password) throws SQLException {
		Connection connection = (Connection) DBUtils.getConnection();
		QueryRunner queryRunner=new QueryRunner();//链接数据库
		
		String sql = "select * from t_user where ( phoneNum = ? or nickname = ? ) and password = ?";
		ResultSetHandler<User> result=new BeanHandler<User>(User.class);
		User user = queryRunner.query(connection,sql,result,account,account,password);

		DBUtils.close(null, null, connection);
		
		return user;
	}

}
