package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ronald Tran
 * @version February 25, 2022
 */
@WebServlet(name = "ShoppingListServlet", urlPatterns = {"/ShoppingListServlet"})
public class ShoppingListServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        
        // Username validate
        if (userName != null) // Username has a string value already
        {   
            // Holds the action's value
            String action = request.getParameter("action");

            // If user clicks "logout"
            if (action != null && action.equals("logout"))
            {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            
            // If the user does not click logout then it stays on the shoppinglist page
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }
        else // If username is empty then it stays on the register page
        {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        // Registering
        if (action.equals("register"))
        {
            String userName = request.getParameter("username");
            
            if (userName != null)
            {
                session.setAttribute("username", userName);
                response.sendRedirect("ShoppingList");
                return;
            }
            else
            {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        }
        else if (action.equals("add")) // Adding item
        {
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("shoppinglist");
            
            if (itemList == null)
            {
                itemList = new ArrayList<>();
            }
            
            if (!request.getParameter("additem").isEmpty())
            {
                String item = request.getParameter("additem");
                itemList.add(item);
                session.setAttribute("shoppinglist", itemList);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }
        else if (action.equals("delete")) // Deleting item
        {   
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("shoppinglist");
            String delete = request.getParameter("items");
            
            if (delete != null)
            {
                itemList.remove(delete);
            }
            session.setAttribute("shoppinglist", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }
    }

}