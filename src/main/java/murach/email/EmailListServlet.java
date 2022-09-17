package murach.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import murach.business.User;
import murach.data.UserDB;


public class EmailListServlet extends HttpServlet  {

    protected void  doPost (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "/index.html";
        
        //get current action 
        String action = request.getParameter("action");
        if( action == null){
            action = "join "; //default action 
        }

        //perform action and set URL to appropriate

        if(action.equals("join")){
            url = "/index.html";
        }
        else if(action.equals("add")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            User user = new User(firstName,lastName,email);
            UserDB.insert(user);

            request.setAttribute("user",user);
            url = "/thank.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    @Override
    protected void doGet ( HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        doPost(request,response);
    }
}