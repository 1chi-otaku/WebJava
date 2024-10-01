package itstep.learning.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().println("<h1>Home</h1>");
        // ~ return View(); аналог
        req.setAttribute("body", "home.jsp"); //Аналог ~ViewData, може передавати дані від контролера до вью
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}

/*
* Сервелети - спеціализовані класи для мережних
* задач, зокремма HTTPServlet - для веб-задач, є аналогом
* контролерів в ASP
* */