<%@ page import="java.util.ArrayList" %>
<%@ page import="com.project.fifidianana.model.BureauDeVote" %><%--
  Created by IntelliJ IDEA.
  User: thekraken9
  Date: 2023-11-27
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ArrayList<BureauDeVote> bureaux = (ArrayList<BureauDeVote>) request.getAttribute("res");
%>
<html>
<head>
    <title>Bureau de vote</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Id</th>
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
        for (BureauDeVote bureauDeVote : bureaux) {
    %>
    <tr>
        <td><%= bureauDeVote.getId() %></td>
        <td><%= bureauDeVote.getRegion() %></td>
        <td><%= bureauDeVote.getDistrict() %></td>
        <td><%= bureauDeVote.getCommune() %></td>
        <td><%= bureauDeVote.getFokontany() %></td>
        <td><%= bureauDeVote.getCentre_de_vote() %></td>
        <td><%= bureauDeVote.getNom() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
