package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import baseLibrary.Utilities;
import enums.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBController {
    static Connection conn = null;
    private Connection dbConn = null;
    private static final Logger logger = LoggerFactory.getLogger(DBController.class);


    public DBController() {
//        try {
//            String jdbcConnection = "jdbc:mysql://" + System.getProperty("mySQLDB") + "/" + System.getProperty("DBname");
//            DBController.conn = DriverManager.getConnection(jdbcConnection, System.getProperty("DB_USER"), System.getProperty("DB_PASS"));
//            System.out.println("Connected to database OPEN. . . ");
//        } catch (SQLException sqlex) {
//            System.out.println("Database parameters have not been provided... cannot connect");
//        }
//        try {
//            System.out.println("Reading DB properties");
//            String propertiesFilePath = "./src/main/resources/db.properties";
//            File src = new File(propertiesFilePath);
//            FileInputStream fis;
//
//            fis = new FileInputStream(src);
//            Properties pro = new Properties();
//            pro.load(fis);
//
//            System.out.println("Opening Credentials DB connection");
//            String jdbcConnection = "jdbc:mysql://" + pro.getProperty("DB_URL") + "/" + pro.getProperty("DB_NAME");
//            dbConn = DriverManager.getConnection(jdbcConnection, pro.getProperty("DB_USERNAME"), pro.getProperty("DB_PASSWORD"));
//            logger.info("Connected to credentials database OPEN. . . ");
//
//        } catch(SQLException | IOException sqlex) {
//            logger.info("Database parameters have not been provided... cannot connect");
//            sqlex.printStackTrace();
//            logger.error(sqlex.getMessage(), sqlex);
//        }
    }
    public void createCriteriaDBConnection(){
        try {
            String jdbcConnection = "jdbc:mysql://" + System.getProperty("mySQLDB") + "/" + System.getProperty("DBname");
            DBController.conn = DriverManager.getConnection(jdbcConnection, System.getProperty("DB_USER"), System.getProperty("DB_PASS"));
            System.out.println("Connected to database OPEN. . . ");
        } catch (SQLException sqlex) {
            System.out.println("Database parameters have not been provided... cannot connect");
        }
    }
    public void closeCriteriaDBConnection(){
        if (conn != null) {
            try {
                DBController.conn.close();
                System.out.println("Connection to database CLOSED . . . ");
            } catch (SQLException sqlex) {
                System.out.println("Database parameters have not been provided... cannot close");
            }
        }
    }

    public void createCredentialsDBConnection(){
        try {
            System.out.println("Reading DB properties");
            String propertiesFilePath = "./src/main/resources/db.properties";
            File src = new File(propertiesFilePath);
            FileInputStream fis;

            fis = new FileInputStream(src);
            Properties pro = new Properties();
            pro.load(fis);

            System.out.println("Opening Credentials DB connection");
            String jdbcConnection = "jdbc:mysql://" + pro.getProperty("DB_URL") + "/" + pro.getProperty("DB_NAME");
            dbConn = DriverManager.getConnection(jdbcConnection, pro.getProperty("DB_USERNAME"), pro.getProperty("DB_PASSWORD"));
            logger.info("Connected to credentials database OPEN. . . ");

        } catch(SQLException | IOException sqlex) {
            logger.info("Database parameters have not been provided... cannot connect");
            sqlex.printStackTrace();
            logger.error(sqlex.getMessage(), sqlex);
        }
    }
    public void closeCredentialsDBConnection(){
        if(dbConn != null) {
            try {
                dbConn.close();
                logger.info("Connection to Credentials database CLOSED . . . ");
            } catch(SQLException sqlex) {
                logger.info("Database parameters have not been provided... cannot close");
            }
        }
    }

    public void CloseConnection() {
//        if (conn != null) {
//            try {
//                DBController.conn.close();
//                System.out.println("Connection to database CLOSED . . . ");
//            } catch (SQLException sqlex) {
//                System.out.println("Database parameters have not been provided... cannot close");
//            }
//        }
//        if(dbConn != null) {
//            try {
//                dbConn.close();
//                logger.info("Connection to Credentials database CLOSED . . . ");
//            } catch(SQLException sqlex) {
//                logger.info("Database parameters have not been provided... cannot close");
//            }
//        }
    }

    public boolean updateCriteria(String serviceCode, String buildVersion, Double score, Criteria criteria) {
        createCriteriaDBConnection();
        if (conn != null) {
            boolean result = false;
            String query = "insert into coe_score(criteria_id,build_version,service_code,score) values("
                    + criteria.getValue() + ",'" + buildVersion + "','" + serviceCode + "'," + score + ");";
            System.out.println(query);
            try {

                Statement stmt = DBController.conn.createStatement();
                int resultSet = stmt.executeUpdate(query);
                //System.out.println(resultSet);
                result = (resultSet == 1) ? true : false;

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeCriteriaDBConnection();
                System.out.println("Result is >> " + result);
                return result;
            }
        } else {
            System.out.println("Database parameters have not been provided... cannot update details");
            return false;
        }
    }
    public Map<String, Object> getUserDetails(int dbUserId, String username_email) {
        createCredentialsDBConnection();
        Map<String, Object> userRecord = null;

        if (dbConn != null) {
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
                closeCredentialsDBConnection();
            }
        } else {
            logger.info("Database parameters have not been provided... cannot get details");
        }
        return userRecord;
    }

    public void updateUserPassword(int dbUserId, String emailId, String password){
        createCredentialsDBConnection();
        if (dbConn != null) {
            String query;
            PreparedStatement stmt = null;
            query = "UPDATE tv_app_login_details SET password = ? WHERE username = ? AND id = ?;";
            try {
                stmt = dbConn.prepareStatement(query);
                stmt.setString(1, password);
                stmt.setString(2, emailId);
                stmt.setInt(3,dbUserId);
                logger.info("Query used for updating the password "+stmt.toString());
                int result=stmt.executeUpdate();
                if(result ==1)
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
                closeCredentialsDBConnection();
            }
        } else {
            logger.info("Database parameters have not been provided... cannot update details");
        }

    }
    public int insertNewCredentials(String userName, String emailId, String password, String userId) {
        createCredentialsDBConnection();
        logger.info("Inside insert credentials method in the NGApp/ TV database");
        if (dbConn != null) {
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
                logger.info(" Query Used for inserting the data "+stmt.toString());
                int resultSet = stmt.executeUpdate();
                logger.info("Result set is "+resultSet);
                if (resultSet == 1) {
                    query = "select id from tv_app_login_details where username = ?;";
                    stmt = dbConn.prepareStatement(query);
                    stmt.setString(1, userName);
                    ResultSet res = stmt.executeQuery();
                    res.next();
                    recId = res.getInt(1);
                    logger.info("Received DBID: " + recId + " Data has been successfully inserted.");
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
                closeCredentialsDBConnection();
            }
            return recId;
        } else {
            logger.info("Database parameters have not been provided... cannot update details");
            return -1;
        }
    }
    public void updateUserAppConsumerId(int dbUserId,String emailId,String appConsumerId){
        createCredentialsDBConnection();
        if (dbConn != null) {
            String query;
            PreparedStatement stmt = null;
            query = "UPDATE tv_app_login_details SET app_consumer_id = ? WHERE email_id = ? AND id = ?;";
            try {
                stmt = dbConn.prepareStatement(query);
                stmt.setString(1, appConsumerId);
                stmt.setString(2, emailId);
                stmt.setInt(3,dbUserId);
                logger.info("Query used for updating the AppConsumer ID "+stmt.toString());
                int result=stmt.executeUpdate();
                if(result ==1)
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
                closeCredentialsDBConnection();
            }
        } else {
            logger.info("Database parameters have not been provided... cannot update details");
        }

    }

}
