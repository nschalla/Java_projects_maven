package webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int accountnum=Integer.parseInt( request.getParameter("accnum"));
		int Transferamount=Integer.parseInt( request.getParameter("amnt"));
		int enter=Integer.parseInt( request.getParameter("enter"));
		int lasttranssaction=Integer.parseInt(request.getParameter("lasttrans"));
		if(enter==true)
		{
		String  ks="insert into transactions2(AccNum,updbal) values(accountnum,Transferamount)"
			try {
				 String ns = states(ks);
				 request.setAttribute("result",ns);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
		}
		else if(lasttranssaction==true) {
			String  ks="select * from transactions2 order by id desc";
			String ns;
			try {
				ns = states(ks);
				 request.setAttribute("result",ns);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

		
	  request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
	}
	public static String states(String ks) throws SQLException{
		Connection myconn=null;
		Statement myst=null;
		ResultSet myse=null;
		String s=null;
		
		try {
		 
	     myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","student","student"); 
		 myst=myconn.createStatement();
			myse=myst.executeQuery(ks);
			
				 String female = myse.getString("blnc");
				 String male=myse.getString("amt");
				 String total=myse.getString("balance");
				 
               if(ks=="insert into transactions2(AccNum,updbal) values(accountnum,Transferamount)") {
            	   while(myse.next()) {
            	   String balance = myse.getString("blnc");
  				 String amount=myse.getString("amt");
  				 String final_balanace=myse.getString("balance");
            	   s= "PREVIOUS BALANCE IS"+ balance +"/n" +"Transfer amount"+ amount +"/n"+" Your balances:"+final_balanace;
            	   } }
               else {
            	   while(myse.next()) {
                	   String accnumber= myse.getString("accno");
      				 String type=myse.getString("type");
      				 String dump=myse.getString("amt");
      				 String final_balanace=myse.getString("balance");
            	   
            	   String ps="YOUR LAST FIVE TRANSACTION ARE";
            	   s=ps+ accnumber + type  + dump + final_balanace;
            	   }}
				    
			}
		
			
		     }
	      
		catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}
		
	

}
