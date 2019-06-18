package Generics;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.Types;
import java.sql.Types.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetConverter {

    public static JsonArray convert(ResultSet rs) throws SQLException {
        JsonArray json = new JsonArray();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        while (rs.next()){
            int numCol = resultSetMetaData.getColumnCount();
            JsonObject obj = new JsonObject();
            for (int i=0; i<numCol;i++){
                String column_name = resultSetMetaData.getColumnName(i);
                switch (resultSetMetaData.getColumnType(i)){
                    case Types.BIGINT: obj.addProperty(column_name, rs.getInt(column_name));break;
                    case Types.BOOLEAN: obj.addProperty(column_name, rs.getBoolean(column_name));break;
                    case Types.BLOB: obj.addProperty(column_name, rs.getInt(column_name));break;
                    case Types.INTEGER: obj.addProperty(column_name, rs.getInt(column_name));break;
                    case Types.DOUBLE: obj.addProperty(column_name, rs.getDouble(column_name));break;
                    case Types.FLOAT: obj.addProperty(column_name, rs.getFloat(column_name));break;
                    case Types.VARCHAR: obj.addProperty(column_name, rs.getNString(column_name));
                    case Types.NVARCHAR: obj.addProperty(column_name, rs.getNString(column_name));
                    case Types.TINYINT: obj.addProperty(column_name, rs.getInt(column_name));
                    case Types.SMALLINT: obj.addProperty(column_name, rs.getInt(column_name));
                    case Types.DATE: obj.addProperty(column_name, rs.getDate(column_name)+"");
                    case Types.TIMESTAMP: obj.addProperty(column_name, ""+rs.getTimestamp(column_name));
                }
            }
            json.add(obj);
        }
        return json;
    }
}
