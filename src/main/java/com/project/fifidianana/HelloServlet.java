package com.project.fifidianana;

import java.io.*;
import java.util.ArrayList;

import com.project.fifidianana.model.ResultatParCandidat;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<ResultatParCandidat> resultatParCandidats = new ArrayList<ResultatParCandidat>();
        ResultatParCandidat resultatParCandidat = new ResultatParCandidat();
        try {
            resultatParCandidats = resultatParCandidat.getAllResultat();
            System.out.println(resultatParCandidats.size());
            request.setAttribute("res", resultatParCandidats);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}