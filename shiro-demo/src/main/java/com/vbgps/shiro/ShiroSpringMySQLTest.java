package com.vbgps.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.alibaba.druid.pool.DruidDataSource;

public class ShiroSpringMySQLTest {

	public static void main2(String[] args) {
		/*实例化工厂*/
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_mysql.ini");
		/*获取实例*/
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		/*设置参数*/
		SecurityUtils.setSecurityManager(securityManager);
		/*获取登录实例*/
		Subject subject = SecurityUtils.getSubject();
		/*实例化token,传入登录的用户和密码*/
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "666");
		subject.isAuthenticated();

		try {
			/*进行登录*/
			subject.login(token);
			System.out.println("登录成功！！");
			if (subject.hasRole("system")) {
				System.out.println("拥有该角色");
			} else {
				System.out.println("没有该角色");
			}

			if (subject.isPermitted("update")) {
				System.out.println("拥有该权限");
			} else {
				System.out.println("没有该权限");
			}
			/*退出登录*/
			subject.logout();
		} catch (Exception e) {
			System.out.println("登录错误！！");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory();
		org.apache.shiro.mgt.SecurityManager instance = factory.getInstance();
		JdbcRealm realm = new JdbcRealm();
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("mysql://localhost:3306/shiro");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		realm.setDataSource(dataSource);
		realm.setPermissionsLookupEnabled(true);
		SecurityUtils.setSecurityManager(instance);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "666");
		subject.isAuthenticated();

		try {
			subject.login(token);
			System.out.println("登录成功！！");
			if (subject.hasRole("system")) {
				System.out.println("拥有该角色");
			} else {
				System.out.println("没有该角色");
			}

			if (subject.isPermitted("update")) {
				System.out.println("拥有该权限");
			} else {
				System.out.println("没有该权限");
			}
			/*退出登录*/
			subject.logout();
		} catch (Exception e) {
			System.out.println("登录错误！！");
			e.printStackTrace();
		}
	}

}
