package controller;

import baseLibrary.Utilities;
import enums.Criteria;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBController {
	private static final Logger logger = LoggerFactory.getLogger(DBController.class);
	// private Connection conn = null;
	private BasicDataSource coeDbSource = null;
	// private Connection dbConn = null;
	private BasicDataSource credentialsDbSource = null;

	public DBController() {
		logger.info("Opening COE DB connection");
		/*try {
			String jdbcConnection = "jdbc:mysql://" + System.getProperty("mySQLDB") + "/" + System.getProperty("DBname");
			conn = DriverManager.getConnection(jdbcConnection, System.getProperty("DB_USER"), System.getProperty("DB_PASS"));
			logger.info("Connected to COE Scores database OPEN. . . ");
		} catch(SQLException sqlex) {
			logger.info("Database parameters have not been provided... cannot connect");
			logger.error(sqlex.getMessage(), sqlex);
		}*/

		if (!Utilities.checkIfStringIsEmpty(System.getProperty("mySQLDB")) && !Utilities.checkIfStringIsEmpty(System.getProperty("DBname"))) {
			String jdbcConnection = "jdbc:mysql://" + System.getProperty("mySQLDB") + "/" + System.getProperty("DBname");
			coeDbSource = new BasicDataSource();
			coeDbSource.setUrl(jdbcConnection);
			coeDbSource.setUsername(System.getProperty("DB_USER"));
			coeDbSource.setPassword(System.getProperty("DB_PASS"));
			coeDbSource.setMinIdle(5);
			coeDbSource.setMaxIdle(10);
			coeDbSource.setMaxTotal(25);
			logger.info("Connection to COE database OPEN. . . ");
		} else {
			logger.info("COE Database parameters have not been provided... cannot connect");
		}

		logger.info("Opening Credentials DB connection");
		try {
			String propertiesFilePath = "./src/main/resources/db.properties";
			File src = new File(propertiesFilePath);
			FileInputStream fis;

			fis = new FileInputStream(src);
			Properties pro = new Properties();
			pro.load(fis);

			/*System.out.println("Opening Credentials DB connection");
			String jdbcConnection = "jdbc:mysql://" + pro.getProperty("DB_URL") + "/" + pro.getProperty("DB_NAME");
			dbConn = DriverManager.getConnection(jdbcConnection, pro.getProperty("DB_USERNAME"), pro.getProperty("DB_PASSWORD"));*/

			if (!Utilities.checkIfStringIsEmpty(pro.getProperty("DB_URL")) && !Utilities.checkIfStringIsEmpty(pro.getProperty("DB_NAME"))) {
				String jdbcConnection = "jdbc:mysql://" + pro.getProperty("DB_URL") + "/" + pro.getProperty("DB_NAME");
				credentialsDbSource = new BasicDataSource();
				credentialsDbSource.setUrl(jdbcConnection);
				credentialsDbSource.setUsername(pro.getProperty("DB_USERNAME"));
				credentialsDbSource.setPassword(pro.getProperty("DB_PASSWORD"));
				credentialsDbSource.setMinIdle(5);
				credentialsDbSource.setMaxIdle(10);
				credentialsDbSource.setMaxTotal(25);
				logger.info("Connection to credentials database OPEN. . . ");
			}

		} catch (IOException sqlex) {
			logger.info("Credentials Database parameters have not been provided... cannot connect");
			sqlex.printStackTrace();
			logger.error(sqlex.getMessage(), sqlex);
		}
	}

	public void closeConnection() {
		/*if (conn != null) {
			try {
				conn.close();
				logger.info("Connection to COE database CLOSED . . . ");
			} catch (SQLException sqlex) {
				logger.info("Database parameters have not been provided... cannot close");
			}
		}
		if (dbConn != null) {
			try {
				dbConn.close();
				logger.info("Connection to Credentials database CLOSED . . . ");
			} catch (SQLException sqlex) {
				logger.info("Database parameters have not been provided... cannot close");
			}
		}*/

	}

	public boolean updateCriteria(String serviceCode, String buildVersion, Double score, Criteria criteria) {
		if (coeDbSource != null) {
			try {
				Connection conn = coeDbSource.getConnection();
				boolean result = false;
				Statement stmt = null;
				String query = "insert into coe_score(criteria_id,build_version,service_code,score) values("
						+ criteria.getValue() + ",'" + buildVersion + "','" + serviceCode + "'," + score + ");";
				logger.info("Constructed Query >> \n {}", query);
				try {
					stmt = conn.createStatement();
					int resultSet = stmt.executeUpdate(query);
					result = (resultSet == 1);
				} catch (SQLException e) {
					logger.error("SQL State: {}\n {}", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					conn.close();
				}
				logger.info("Result is >> {}", result);
				return result;
			} catch (SQLException sqlex) {
				logger.info("Problem with establishing COE connection to database... please check");
				sqlex.printStackTrace();
				logger.error(sqlex.getMessage(), sqlex);
				return false;
			}
		} else {
			logger.info("Database parameters have not been provided... cannot update details");
			return false;
		}
	}


	public int insertNewCredentials(String userName, String emailId, String password, String userId) {
		if (credentialsDbSource != null) {
			try {
				Connection dbConn = credentialsDbSource.getConnection();
				int recId = -1;
				String query;
				PreparedStatement stmt = null;

				if (Utilities.checkIfStringIsEmpty(userName) || Utilities.checkIfStringIsEmpty(emailId) || Utilities.checkIfStringIsEmpty(password)) {
					throw new IllegalArgumentException("userName, emailId and password are mandatory, please check arguments");
				}
				if (!Utilities.checkIfStringIsEmpty(userId)) {
					query = "insert into tv_app_login_details(username, email_id, password, user_id) values(?, ?, ?, ?);";
				} else {
					query = "insert into tv_app_login_details(username, email_id, password) values(?, ?, ?);";
				}
				try {
					stmt = dbConn.prepareStatement(query);
					stmt.setString(1, userName);
					stmt.setString(2, emailId);
					stmt.setString(3, password);
					if (!Utilities.checkIfStringIsEmpty(userId)) {
						stmt.setString(4, userId);
					}
					int resultSet = stmt.executeUpdate();
					if (resultSet == 1) {
						query = "select id from tv_app_login_details where username = ?;";
						stmt = dbConn.prepareStatement(query);
						stmt.setString(1, userName);
						ResultSet res = stmt.executeQuery();
						res.next();
						recId = res.getInt(1);
						logger.debug("Received ID: " + recId);
						res.close();
					} else {
						throw new SQLException("SQL statement affected " + resultSet + " rows; expected was 1");
					}
				} catch (SQLException e) {
					logger.error("SQL State: {}\n {}", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					dbConn.close();
				}
				return recId;
			} catch (SQLException sqlex) {
				logger.info("Problem with establishing credentials connection to database... please check");
				sqlex.printStackTrace();
				logger.error(sqlex.getMessage(), sqlex);
				return -1;
			}
		} else {
			logger.info("Database parameters have not been provided... cannot update details");
			return -1;
		}
	}

	public Map<String, Object> getUserDetails(int dbUserId, String username_email) {
		Map<String, Object> userRecord = null;

		if (credentialsDbSource != null) {
			try {
				Connection dbConn = credentialsDbSource.getConnection();
				String query;
				PreparedStatement stmt = null;

				if (dbUserId != 0) {
					query = "select * from tv_app_login_details where id = ?;";
				} else if (!Utilities.checkIfStringIsEmpty(username_email)) {
					query = "select * from tv_app_login_details where username = ? or email_id = ?;";
				} else {
					query = "select * from tv_app_login_details where NOT ignore_by_default AND app_consumer_id IS NOT NULL ORDER BY RAND() LIMIT 1;";
				}
				try {
					stmt = dbConn.prepareStatement(query);
					if (dbUserId != 0) {
						stmt.setInt(1, dbUserId);
					} else if (!Utilities.checkIfStringIsEmpty(username_email)) {
						stmt.setString(1, username_email);
						stmt.setString(2, username_email);
					}
					ResultSet resultSet = stmt.executeQuery();
					resultSet.next();
					userRecord = new HashMap<>();
					userRecord.put("dbUserId", resultSet.getInt(1));
					userRecord.put("username", resultSet.getString(2));
					userRecord.put("emailId", resultSet.getString(3));
					userRecord.put("password", resultSet.getString(4));
					userRecord.put("userId", resultSet.getString(5));
					resultSet.close();
				} catch (SQLException e) {
					logger.error("SQL State: {}\n {}", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					dbConn.close();
				}
			} catch (SQLException sqlex) {
				logger.info("Problem with establishing credentials connection to database... please check");
				sqlex.printStackTrace();
				logger.error(sqlex.getMessage(), sqlex);
			}
		} else {
			logger.info("Database parameters have not been provided... cannot get details");
		}
		return userRecord;
	}

	public void updateUserPassword(int dbUserId, String emailId, String password) {
		if (credentialsDbSource != null) {
			try {
				Connection dbConn = credentialsDbSource.getConnection();
				String query;
				PreparedStatement stmt = null;
				query = "UPDATE tv_app_login_details SET password = ? WHERE email_id = ? AND id = ?;";
				try {
					stmt = dbConn.prepareStatement(query);
					stmt.setString(1, password);
					stmt.setString(2, emailId);
					stmt.setInt(3, dbUserId);
					int result = stmt.executeUpdate();
					if (result == 1)
						logger.info("Data has been updated in the data base");
					else
						logger.info("Data update failed");
				} catch (SQLException e) {
					logger.error("SQL State: {}\n {}", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					dbConn.close();
				}
			} catch (SQLException sqlex) {
				logger.info("Problem with establishing credentials connection to database... please check");
				sqlex.printStackTrace();
				logger.error(sqlex.getMessage(), sqlex);
			}
		} else {
			logger.info("Database parameters have not been provided... cannot update details");
		}

	}

	public void updateUserAppConsumerId(int dbUserId, String emailId, String appConsumerId) {
		if (credentialsDbSource != null) {
			try {
				Connection dbConn = credentialsDbSource.getConnection();
				String query;
				PreparedStatement stmt = null;
				query = "UPDATE tv_app_login_details SET app_consumer_id = ? WHERE email_id = ? AND id = ?;";
				try {
					stmt = dbConn.prepareStatement(query);
					stmt.setString(1, appConsumerId);
					stmt.setString(2, emailId);
					stmt.setInt(3, dbUserId);
					int result = stmt.executeUpdate();
					if (result == 1)
						logger.info("Data has been updated in the data base");
					else
						logger.info("Data update failed");
				} catch (SQLException e) {
					logger.error("SQL State: {}\n {}", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					dbConn.close();
				}
			} catch (SQLException sqlex) {
				logger.info("Problem with establishing credentials connection to database... please check");
				sqlex.printStackTrace();
				logger.error(sqlex.getMessage(), sqlex);
			}
		} else {
			logger.info("Database parameters have not been provided... cannot update details");
		}

	}

}
