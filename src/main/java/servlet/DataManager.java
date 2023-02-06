package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebServlet(name = "datamanager",
        urlPatterns = {"/dataManager"})
public class DataManager extends HttpServlet {

    Logger log = Logger.getLogger(String.valueOf(DataManager.class));

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        switch (request.getParameter("type")) {
            case "ticket":
                // Retrieve all the info from ticket and insert it
                log.info("Is ticket");
                break;
            case "user":
                //Insert user
                log.info("Is user");
                break;
            case "support":
                //Insert support
                log.info("Is support");
                break;
        }

        response.sendRedirect("/myform.html");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("There is a get request..");
    }
}
