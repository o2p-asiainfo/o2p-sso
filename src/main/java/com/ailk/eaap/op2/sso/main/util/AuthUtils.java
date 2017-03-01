package com.ailk.eaap.op2.sso.main.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AuthUtils {

	private Connection conn = null;
	private java.sql.CallableStatement cs = null;

	public AuthUtils() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@192.168.111.99:1521:drms2", "XRBSM",
//					"XRBSM");
			
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
			"com.ibm.ejs.ns.jndi.CNInitialContextFactory");
			 Context  ctx = new InitialContext(env);
			DataSource ds = (DataSource) ctx.lookup("jndi/xrbsm");
			conn = ds.getConnection();
			
			//jdbc:oracle:thin:@10.1.6.15:1521:ctgrb
			//jdbc:oracle:thin:@10.1.8.30:1521:npsoa
			//jdbc:oracle:thin:@192.168.111.98:1521:orcl98
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addrole(int roleid) {
		try {
			CallableStatement c = conn.prepareCall("{call AUTHWHENADDROLE(?)}");
			c.setInt(1, roleid);
			c.executeUpdate();
			c.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addfunction(int functionid) {
		try {
			CallableStatement c = conn
					.prepareCall("{call AUTHWHENADDFUNCTION(?)}");
			c.setInt(1, functionid);
			c.executeUpdate();
			c.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void delrole(int roleid) {
		try {
			CallableStatement c = conn.prepareCall("{call AUTHWHENDELROLE(?)}");
			c.setInt(1, roleid);
			c.executeUpdate();
			c.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void delfunction(int functionid) {
		try {
			CallableStatement c = conn
					.prepareCall("{call AUTHWHENDELFUNCTION(?)}");
			c.setInt(1, functionid);
			c.executeUpdate();
			c.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		AuthUtils au = new AuthUtils();
		au.addrole(151);
	}
}
