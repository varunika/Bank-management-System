package project1;
import java.sql.*;
public class connect 
{
    public static Connection c()
    {   Connection co=null;
    try
    {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    co=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","varunika");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
    return co;
    }
}
