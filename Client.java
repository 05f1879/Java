import java.io.*;
import java.net.*;
import java.util.*;
class Client{
	private static Socket socket;
	public static void main(String[] args)
	{
		try{
			
			String host="localhost";
		    int port=25000;
		    InetAddress address=InetAddress.getByName(host);
		    socket=new Socket(address,port);
		    
		    
		    OutputStream os= socket.getOutputStream();
			OutputStreamWriter osw =new OutputStreamWriter(os);
			BufferedWriter bw=new BufferedWriter(osw);
			
			
			Scanner keyboard =new Scanner(System.in);
			System.out.print("enter the Country Name:  ");
			String cname=keyboard.nextLine();
			String messagesend=cname+"\n";
			bw.write(messagesend);
			bw.flush();
			
			
			
			InputStream is = socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br= new BufferedReader(isr);
				String message =br.readLine();
				System.out.print("Country Code of "+cname+"is" +message);
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
					}
					finally{
						//close the Socket
						try{
							socket.close();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
			
		
}
}