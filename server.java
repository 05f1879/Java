import java.io.*;
import java.net.*;
import java.sql.*;
class server{
	private static Socket socket;
	public static void main(String[] args)
	{
		try{
			int port=25000;
			ServerSocket serversocket= new ServerSocket(port);
			System.out.print("Server Started and Listening to the port 25000");
			
			while(true)
			{
				// Read the message from the client
				socket =serversocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br= new BufferedReader(isr);
				String cname=br.readLine();
				System.out.print("Message Received from clint is "+cname);
				try
				
				{
				
					//load the jdbc odbc driver 
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
					
					Connection con=DriverManager.getConnection("jdbc:odbc:MyDB","","");
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select *from CountryDiary where CountryName ='"+cname+"'");
					ResultSetMetaData rsmd= rs.getMetaData();
					int numberOfColumns =rsmd.getColumnCount();
					if(rs.next()){
						String Value =rs.getString("CCode");
						OutputStream os=socket.getOutputStream();
						OutputStreamWriter osw =new OutputStreamWriter(os);
						BufferedWriter bw=new BufferedWriter(osw);
						bw.write(Value+"\n");
					    System.out.print("Message Sent to the Clint is "+ Value);
					    bw.flush();
					    }
					    else
					    {
					    OutputStream os=socket.getOutputStream();
						OutputStreamWriter osw =new OutputStreamWriter(os);
						BufferedWriter bw=new BufferedWriter(osw);
							bw.write("not Found \n");
					    System.out.print("Message Sent to the Clint ");
					    }
					    st.close();
					    con.close();
					    }catch(Exception ex)
					    {
					    	System.err.print("Exception");
					    	System.err.print(ex.getMessage());
					    }
					    
					    
		}
	}
	catch(Exception e){
		e.printStackTrace();}
		finally{
		}
		try{
			socket.close();}
			catch(Exception e){}
	
	}
	}

	
