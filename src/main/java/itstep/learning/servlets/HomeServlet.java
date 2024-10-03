package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;
import itstep.learning.services.db.DbService;
import itstep.learning.services.filegenerator.FileGeneratorService;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.kdf.KdfService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class HomeServlet extends HttpServlet {

    private final HashService hashService;
    private final KdfService kdfService;
    private final DbService dbService;
    private final FileGeneratorService fileGeneratorService;
    private final AuthDao authDao; //інжекцію класі (не інтерфейсів) реєструвати не треба.

    @Inject
    public HomeServlet(HashService hashService, KdfService kdfService, DbService dbService, FileGeneratorService fileGeneratorService, AuthDao authDao) {
        this.hashService = hashService;
        this.kdfService = kdfService;
        this.dbService = dbService;
        this.fileGeneratorService = fileGeneratorService;
        this.authDao = authDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ~ return View(); аналог

        String defaultName = fileGeneratorService.generateFileName();
        String customName = fileGeneratorService.generateFileName(54);

        req.setAttribute("defaultName", defaultName);
        req.setAttribute("customName", customName);


        boolean isSigned = false;
        Object signature = req.getAttribute("signature");

        if(signature instanceof Boolean) {
            isSigned = (Boolean) signature;
        }

        String dbMesage;
        if(isSigned){
            String dbMessage =
                    authDao.install() ? "Connection OK" : "Connection failed";



            req.setAttribute("hash",
                    hashService.hash("123") + " " + kdfService.dk("password","salt.4"));
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