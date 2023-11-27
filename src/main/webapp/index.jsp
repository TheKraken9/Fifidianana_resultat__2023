<%@ page import="java.util.ArrayList" %>
<%@ page import="com.project.fifidianana.model.ResultatParCandidat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ArrayList<ResultatParCandidat> resultatParCandidats = (ArrayList<ResultatParCandidat>) request.getAttribute("res");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Resultat - CENI</title>
</head>
<body>
<a href="BureauDeVoteServlet">Liste des bureaux de votes</a>
<h1>Resultat</h1>
<table>
    <thead>
        <tr>
            <th>Bureau de vote</th>
            <th>No Candidat</th>
            <th>Nombre de voix</th>
            <th>Pourcentage</th>
            <th>Region</th>
            <th>District</th>
            <th>Commune</th>
            <th>Fokontany</th>
            <th>Centre de Vote</th>
            <th>Nom</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (ResultatParCandidat resultatParCandidat : resultatParCandidats) {
        %>
        <tr>
            <td><%= resultatParCandidat.getId_bureau_de_vote() %></td>
            <td><%= resultatParCandidat.getId_candidat() %></td>
            <td><%= resultatParCandidat.getVoix() %></td>
            <td><%= resultatParCandidat.getPourcentage() %></td>
            <td><%= resultatParCandidat.getRegion() %></td>
            <td><%= resultatParCandidat.getDistrict() %></td>
            <td><%= resultatParCandidat.getCommune() %></td>
            <td><%= resultatParCandidat.getFokontany() %></td>
            <td><%= resultatParCandidat.getCentre_de_vote() %></td>
            <td><%= resultatParCandidat.getNom() %></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
</body>
</html>