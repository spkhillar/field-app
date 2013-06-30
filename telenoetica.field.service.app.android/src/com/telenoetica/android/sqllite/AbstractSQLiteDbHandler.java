package com.telenoetica.android.sqllite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class AbstractSQLiteDbHandler extends SQLiteOpenHelper {


  protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractSQLiteDbHandler.class);

  private static final String DATABASE_NAME = "field_service_db";

  private static final int DB_APP_VERSION = 1;

  @SuppressWarnings("unused")
  private Context context;

  private static final String[] SPINNER_TABLE_NAMES =
      new String[] { "spare", "client", "fault", "site", "maintenance" };

  private static final List<String> FINAL_TABLE_LIST = new ArrayList<String>(Arrays.asList(SPINNER_TABLE_NAMES));

  public AbstractSQLiteDbHandler(final Context context, final String name, final CursorFactory factory, final int version) {
    super(context, name, null, version);
    this.context = context;
    FINAL_TABLE_LIST.add("configuration");
    FINAL_TABLE_LIST.add("credential");
    FINAL_TABLE_LIST.add("visit");
  }

  public AbstractSQLiteDbHandler(final Context context) {
    this(context, DATABASE_NAME, null, DB_APP_VERSION);
  }

  @Override
  public void onCreate(final SQLiteDatabase db) {
    LOGGER.debug("creating table started");
    try {
      String finalCreateSqlQuery;

      for (String spinnerTableName : SPINNER_TABLE_NAMES) {
        finalCreateSqlQuery = getSpinnerTableSqlScript(spinnerTableName);
        db.execSQL(finalCreateSqlQuery);
      }
      db.execSQL(getConfigurationTableSqlScript());
      db.execSQL(getCredentialTableSqlScript());
      db.execSQL(getVisitTableSqlScript());
    } catch (SQLException e) {
      LOGGER.error("SQL Exception while creating tables..",e);
    }
    LOGGER.debug("creating table ends");
  }

  @Override
  public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    LOGGER.debug("...onUpgrade...starts");
    String baseQuery = "DROP TABLE IF EXISTS ";
    String finalDropSqlQuery = null;
    try {
      for (String spinnerTableName : SPINNER_TABLE_NAMES) {
        finalDropSqlQuery = baseQuery + spinnerTableName;
        db.execSQL(finalDropSqlQuery);
      }
      finalDropSqlQuery = baseQuery + "configuration";
      db.execSQL(finalDropSqlQuery);
      finalDropSqlQuery = baseQuery + "credential";
      db.execSQL(finalDropSqlQuery);
      finalDropSqlQuery = baseQuery + "visit";
      db.execSQL(finalDropSqlQuery);
    } catch (SQLException e) {
      LOGGER.error("SQL Exception while droping tables tables..",e);
    }
    onCreate(db);
    LOGGER.debug("...onUpgrade...ends");
  }

  private String getSpinnerTableSqlScript(final String tableName) {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + tableName + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name" + " TEXT)";
    return sqlCreateQuery;
  }

  private String getCredentialTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "credential" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "username" + " TEXT,"
            + "password" + " TEXT)";
    return sqlCreateQuery;
  }

  private String getConfigurationTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "configuration" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name" + " TEXT,"
            + "value" + " TEXT)";
    return sqlCreateQuery;
  }

  private String getVisitTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "visit" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "json" + " TEXT,"
            + "status" + " TEXT,tries INTEGER)";
    return sqlCreateQuery;
  }

  public String getSparesTable(){
    return FINAL_TABLE_LIST.get(0);
  }

  public String getClientsTable(){
    return FINAL_TABLE_LIST.get(1);
  }

  public String getFaultsTable(){
    return FINAL_TABLE_LIST.get(2);
  }

  public String getSitesTable(){
    return FINAL_TABLE_LIST.get(3);
  }

  public String getMaintenanceTable(){
    return FINAL_TABLE_LIST.get(4);
  }

  public String getConfigurationsTable(){
    return FINAL_TABLE_LIST.get(5);
  }

  public String getCredentialsTable(){
    return FINAL_TABLE_LIST.get(6);
  }

  public String getVistisTable(){
    return FINAL_TABLE_LIST.get(7);
  }
}
