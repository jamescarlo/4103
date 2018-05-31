
package test;

import griffon.core.artifact.GriffonService;
import griffon.core.resources.ResourceHandler;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;


import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.MapIterator;
import java.util.ArrayList;
import java.awt.List;


@javax.inject.Singleton
@ArtifactProviderFor(GriffonService.class)
public class DBQuery extends AbstractGriffonService {

	private String host     = "162.217.144.39";
	private String username = "ivantest_user";
	private String password = "Jfhslfj35ssd";
	private String database = "ivantest_db";

	private Connection conn;

    public boolean connect() {
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            System.err.println(e);
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, username, password);

    	}catch(SQLException e) {
    		System.err.println(e);
    	}
        return conn == null ? false : true;
    }


    public void save(MultiMap mapquery) {
        if(connect()) {
            String query = querybuilder(mapquery, "insert");
            try { 
                Statement st = conn.createStatement();
                st.executeUpdate(query);
            }catch(SQLException e) {
                System.err.println(e);
            }
            close();
        }
    }


    public Map get(MultiMap mapquery) {
        Map<Integer, Map> map = new HashMap();
        if(connect()) {
            String query = querybuilder(mapquery, "select");
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData md = rs.getMetaData();
                while(rs.next()) {
                    Map data = new LinkedHashMap();
                    for(int a = 1; a <= md.getColumnCount(); a++) {
                        data.put(md.getColumnName(a), rs.getString(a));
                    }
                    map.put(rs.getRow()-1, data);
                }
                rs.close();
            }catch(SQLException e) {
                System.err.println(e);
            }
            close();
        }
        return map;
    }


    @Nonnull
    public String querybuilder(MultiMap mapquery, String action) {
        String query = "";
        MapIterator iterator = mapquery.mapIterator();
        switch(action) {
            case "select":
                query = "SELECT * FROM "+ ((ArrayList) mapquery.get("table")).get(0) +" ";
                if(mapquery.size() > 1) {
                    query += "WHERE ";
                }
                while(iterator.hasNext()) {
                    String[] key = (iterator.next()+"").split(":"); 
                    if(key[0].equals("condition")) {
                        ArrayList list = (ArrayList) iterator.getValue();
                        for(int i=0; i < list.size(); i++) {
                            if(key.length == 2) {
                                query += " "+ key[1] +" ";
                            }   
                            String[] value = (list.get(i)+"").split(" ");  
                            try {
                                int num = Integer.parseInt(value[2]);
                                query += value[0] + value[1] + num;
                            }catch(Exception e) {
                                query += value[0] + value[1] +"'"+ value[2] +"'";
                            }
                        }
                    }
                }
                break;
            
            case "insert":
                query = "INSERT INTO "+((ArrayList) mapquery.get("table")).get(0) +" ";
                String columns = "";
                String values  = "";
                while(iterator.hasNext()) {
                    String key = iterator.next()+"";
                    if(key.equals("set")) {
                        ArrayList list = (ArrayList) iterator.getValue();
                        for(int i=0; i < list.size(); i++) {
                            if(columns != "" && values != "") {
                                columns += ", ";
                                values += ", ";
                            }
                            String[] value = (list.get(i)+"").split(" ");  
                            columns += value[0];
                            try {
                                int num = Integer.parseInt(value[2]);
                                values += num;
                            }catch(Exception e) {
                                values += "'"+ value[2] +"'";
                            }
                        }
                    }
                }
                query += "("+ columns +") VALUES ("+ values +")";
                break;
             
            default:

                break;    
        }
        return query;
    }


    public MultiValueMap map(){
        return new MultiValueMap();
    }


    public void query(@Nonnull String query) {
        if(connect()) {
            try { 
                Statement st = conn.createStatement();
                st.executeUpdate(query);
            }catch(SQLException e) {
                System.err.println(e);
            }
            close();
        }
    }
    

    public void close() {
        try{
            conn.close();
        }catch(SQLException e) {
            System.err.println(e);
        }
    }
}