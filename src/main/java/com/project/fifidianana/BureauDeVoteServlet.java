package com.project.fifidianana;

import com.project.fifidianana.model.BureauDeVote;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BureauDeVoteServlet", value = "/BureauDeVoteServlet")
public class BureauDeVoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<BureauDeVote> bureauDeVotes = new ArrayList<BureauDeVote>();
        BureauDeVote bureauDeVote = new BureauDeVote();
        try {
            bureauDeVotes = bureauDeVote.getAllBureauDeVote();
            System.out.println(bureauDeVotes.size());
            request.setAttribute("res", bureauDeVotes);
            request.getRequestDispatcher("index2.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}