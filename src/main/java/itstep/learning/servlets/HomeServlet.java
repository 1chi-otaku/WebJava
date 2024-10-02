package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.services.hash.HashService;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class HomeServlet extends HttpServlet {

    private  final HashService hashService;

    @Inject
    public HomeServlet(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ~ return View(); аналог

        boolean isSigned = false;
        Object signature = req.getAttribute("signature");

        if(signature instanceof Boolean) {
            isSigned = (Boolean) signature;
        }

        if(isSigned){
            req.setAttribute("hash",hashService.hash("123"));
            req.setAttribute("body", "home.jsp");
        }
        else{
            req.setAttribute("body", "insecure.jsp");
        }

        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}

/*
* Сервелети - спеціализовані класи для мережних
* задач, зокремма HTTPServlet - для веб-задач, є аналогом
* контролерів в ASP
* */