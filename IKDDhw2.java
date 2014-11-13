
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IKDDhw2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = null;
        String q = args[0];
    
        String url = "jdbc:postgresql://iServDB.cloudopenlab.org.tw:5432/mento0513_db_1154";
        String user = "mento0513_user_1154";
        String password = "GfYqIFtc";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            query = "SELECT * FROM \"twitter\" WHERE q = '"+q+"' order by user_id ASC";
            rs = st.executeQuery(query);
            //String leftAlignFormat = "| %-15s |%n";
            System.out.println("+---------------+--------------------+--------------------+");
            System.out.println("|text           |user_name           |user_id             |");
            System.out.println("+---------------+--------------------+--------------------+");
            while( rs.next() ) {
            	String str = rs.getString("text");
            	String s = new String(str.getBytes("UTF-8"),"UTF-8");
            	char[] Chars = s.toCharArray();
            	for(int i=0 ; i<s.length() ; i++)
            	{
            		if( Chars[i] == '\n' )
            			Chars[i] = ' ';
            	}
            	s = String.valueOf(Chars);
            	
            	int len = 0;
            	while(len < s.length())
            	{
            		if(len + 15 >= s.length())
            		{
            			System.out.print("|"+s.substring(len));
            			for(int i=0 ; i < 15 - (s.length() - len) ; i++)
            				System.out.print(' ');
            			System.out.print("|");
            		}
            		else
            		{
            			System.out.print("|"+s.substring(len, len+15)+"|");
            		}
            		
            		if(len == 0)
            		{
            			String user_name = new String((rs.getString("user_name")).getBytes("UTF-8"),"UTF-8");
            			String user_id = new String((rs.getString("user_id")).getBytes("UTF-8"),"UTF-8");
            			System.out.print(user_name);
            			for(int i=0 ; i < 20 - user_name.length() ; i++)
            				System.out.print(' ');
            			System.out.print("|");            			
            			System.out.print(user_id);
            			for(int i=0 ; i < 20 - user_id.length() ; i++)
            				System.out.print(' ');
            			System.out.print("|");             			
            		}
            		else
            		{
            			for(int i=0 ; i < 20 ; i++)
            				System.out.print(' ');
            			System.out.print("|");            			

            			for(int i=0 ; i < 20 ; i++)
            				System.out.print(' ');
            			System.out.print("|");       
            		}
            		
            		System.out.println("");
            		len += 15;
            	}
            	System.out.println("+---------------+--------------------+--------------------+");
            	
            }

        } catch (SQLException ex) {
        	System.out.println(ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
            	System.out.println(ex);
            }
        }		
		
	}

}
