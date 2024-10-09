package itstep.learning.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;

@Singleton
public class AuthServlet extends HttpServlet {
    private final AuthDao authDao;

    @Inject
    public AuthServlet(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
            The 'Basic' HTTP Authentication Scheme
            https://datatracker.ietf.org/doc/html/rfc7617
        */

        //Вилучаємо Authorization Header
        //Переверяємо, що схема Basic
        //Виділяємо дані авнтентифікації (credentials)
        //Декодуємо їх за Base64
        //Розділяємо за першим символом ':'
        //Запитуємо автентифікацію в DAO
        RestResponse restResponse = new RestResponse();

        try{
            //Вилучаємо Authorization Header
            String authHeader = req.getHeader("Authorization");
            if(authHeader == null){
                throw new ParseException("Authorization header is null",401);
            }
            //Переверяємо, що схема Basic
            String authScheme = "Basic "; //trailing space - the part of the standard
            if(!authHeader.startsWith("Basic ")){
                throw new ParseException("Invalid Authorization Scheme. Required " + authScheme,400);
            }
            //Виділяємо дані авнтентифікації (credentials)
            String credentials = authHeader.substring(authHeader.indexOf(authScheme)+authScheme.length());

            //Декодуємо їх за Base64
            String decodedCredentials;
            try{
                decodedCredentials = new String(
                        Base64.getUrlDecoder().decode(credentials.getBytes(StandardCharsets.UTF_8)),
                        StandardCharsets.UTF_8
                );
            }
            catch(IllegalArgumentException ignored){
                throw new ParseException("Invalid Credential format ",400);
            }

            //Розділяємо за першим символом ':'
            String[] parts = decodedCredentials.split(":",2);
            if(parts.length != 2){
                throw new ParseException("Invalid Credential Composition ",400);
            }

            restResponse.setStatus("success");
            restResponse.setCode(200);
            restResponse.setData(decodedCredentials);


        }
        catch(ParseException e){
            restResponse.setStatus("error");
            restResponse.setCode(e.getErrorOffset());
            restResponse.setData(e.getMessage());


        }



        Gson gson = new GsonBuilder().serializeNulls().create();
        resp.setContentType("application/json");
        resp.getWriter().print(gson.toJson (restResponse) );



    }

    class RestResponse{
        private int code;
        private String status;
        private Object data;

        public RestResponse(){

        }

        public RestResponse(int code, String status, Object data) {
            this.code = code;
            this.status = status;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getData() {
            return code;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
