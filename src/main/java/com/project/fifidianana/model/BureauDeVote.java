package com.project.fifidianana.model;

import com.project.fifidianana.connecting.Connecting;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.*;

public class BureauDeVote {
    private String id;
    private String region;
    private String district;
    private String commune;
    private String fokontany;
    private String centre_de_vote;
    private String nom;

    public BureauDeVote() {
    }

    public BureauDeVote(String id, String region, String district, String commune, String fokontany, String centre_de_vote, String nom) {
        this.id = id;
        this.region = region;
        this.district = district;
        this.commune = commune;
        this.fokontany = fokontany;
        this.centre_de_vote = centre_de_vote;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getFokontany() {
        return fokontany;
    }

    public void setFokontany(String fokontany) {
        this.fokontany = fokontany;
    }

    public String getCentre_de_vote() {
        return centre_de_vote;
    }

    public void setCentre_de_vote(String centre_de_vote) {
        this.centre_de_vote = centre_de_vote;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void importer_xls_bureau_de_vote_avec_librarie_jxl_et_inserer(Connection connection) throws Exception {
        if(connection == null) connection = Connecting.getConnection("postgres");
        try{
            String sql = "insert into bureau_de_vote(id, region, district, commune, fokontany, centre_de_vote, nom) values(?, ?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String chemin_du_fichier = "/home/thekraken9/IdeaProjects/fifidianana/src/main/java/com/project/fifidianana/S5fifidianana/listebv.xls";
            jxl.Workbook workbook = jxl.Workbook.getWorkbook(new java.io.File(chemin_du_fichier));
            jxl.Sheet sheet = workbook.getSheet(0);
            for(int i = 1; i < sheet.getRows(); i++){
                jxl.Cell[] row = sheet.getRow(i);
                preparedStatement.setString(1, row[6].getContents());
                preparedStatement.setString(2, row[1].getContents());
                preparedStatement.setString(3, row[2].getContents());
                preparedStatement.setString(4, row[3].getContents());
                preparedStatement.setString(5, row[4].getContents());
                preparedStatement.setString(6, row[5].getContents());
                preparedStatement.setString(7, row[7].getContents());
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Candidat> liste_des_candidats(Connection connection) throws Exception {
        if(connection == null) connection = Connecting.getConnection("postgres");
        ArrayList<Candidat> liste_des_candidats = new ArrayList<>();
        try{
            String sql = "select * from candidat";
            java.sql.PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = preparedStatement2.executeQuery();
            while(resultSet.next()){
                Candidat candidat = new Candidat();
                candidat.setId(resultSet.getInt("id"));
                candidat.setNom(resultSet.getString("nom"));
                candidat.setParti(resultSet.getString("parti"));
                liste_des_candidats.add(candidat);
            }
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
        }
        return liste_des_candidats;
    }

    /*public void recuperer_les_resultats_dans_le_txt(Connection connection) throws Exception {
        ArrayList<Candidat> liste_des_candidats = liste_des_candidats(connection);
        try{
            listes_de_tous_les_fichier_dans_le_dossier("/home/thekraken9/IdeaProjects/fifidianana/src/main/java/com/project/fifidianana/S5fifidianana/resultats", liste_des_candidats, connection);
        }catch (Exception e){
            throw e;
        }
    }*/

    public java.io.File[] listes_de_tous_les_fichier_dans_le_dossier() throws Exception {
        String chemin_dossier = "/home/thekraken9/IdeaProjects/fifidianana/src/main/java/com/project/fifidianana/S5fifidianana/bv2a";
        java.io.File dossier = new java.io.File(chemin_dossier);
        java.io.File[] listes_de_tous_les_fichier_dans_le_dossier = dossier.listFiles();
        for (java.io.File file : listes_de_tous_les_fichier_dans_le_dossier) {
            //System.out.println(file.getName());
        }
        return listes_de_tous_les_fichier_dans_le_dossier;
    }

    public void exploiter_chaque_fichier(java.io.File[] listes_de_tous_les_fichier_dans_le_dossier, ArrayList<Candidat> liste_des_candidats) throws Exception {
        Connection connection = Connecting.getConnection("postgres");
        connection.setAutoCommit(false);
        for (java.io.File file : listes_de_tous_les_fichier_dans_le_dossier) {
            if(file.getName().endsWith(".txt")){
                //System.out.println(file.getName());
                exploiter_un_fichier(file, liste_des_candidats, connection);
            }
        }
        connection.commit();
    }

    public void exploiter_un_fichier(java.io.File file, ArrayList<Candidat> liste_des_candidats, Connection connection) throws Exception {
        try{
            String ligne;
            for (Candidat candidate : liste_des_candidats) {
                java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.FileReader(file));
                String nom_candidate = candidate.getNom();
                String dernier_mot_du_nom = nom_candidate.substring(nom_candidate.lastIndexOf(" ")+1);
                if(dernier_mot_du_nom.contains("-")) dernier_mot_du_nom = dernier_mot_du_nom.substring(dernier_mot_du_nom.lastIndexOf("-")+1);
                String parti_candidate = candidate.getParti();
                List<String> tous_les_lignes = new ArrayList<>();
                while((ligne = bufferedReader.readLine()) != null){
                    tous_les_lignes.add(ligne);
                }
                //System.out.println(tous_les_lignes);
                String balise_gauche = dernier_mot_du_nom + ",";
                //System.out.println(balise_gauche);
                String balise_droite = "%";
                String texte_entre_les_balises = "";
                Matcher matcher = java.util.regex.Pattern.compile(Pattern.quote(balise_gauche) + "(.*?)" + Pattern.quote(balise_droite)).matcher(tous_les_lignes.toString());
                while (matcher.find()) {
                    texte_entre_les_balises = matcher.group(1);
                    //System.out.println(texte_entre_les_balises);
                }
                Pattern reste_string = Pattern.compile(Pattern.quote(parti_candidate) + "(.*)");
                Matcher matcher2 = reste_string.matcher(texte_entre_les_balises);
                while (matcher2.find()) {
                    texte_entre_les_balises = matcher2.group(1);
                    //System.out.println(texte_entre_les_balises);
                }
                String pourcentage = texte_entre_les_balises.substring(texte_entre_les_balises.lastIndexOf(" ")+1);
                String reste = texte_entre_les_balises.substring(0, texte_entre_les_balises.lastIndexOf(" "));
                //System.out.println(pourcentage + " " + reste);
                String id_bureau_de_vote_avec_pdf = file.getName().substring(0, file.getName().lastIndexOf("."));
                String id_bureau_de_vote = id_bureau_de_vote_avec_pdf.substring(0, id_bureau_de_vote_avec_pdf.lastIndexOf("."));
                inserer_resultat_par_candidat(id_bureau_de_vote, String.valueOf(candidate.getId()), Integer.parseInt(reste), Double.parseDouble(pourcentage), connection);
            }
        }catch (Exception e){
            throw e;
        }
    }

    public String enlever_tous_les_lettres(String texte_entre_les_balises) {
        String resultat = "";
        for(int i = 0; i < texte_entre_les_balises.length(); i++){
            if(Character.isDigit(texte_entre_les_balises.charAt(i))){
                resultat += texte_entre_les_balises.charAt(i);
            }
        }
        return resultat;
    }

    public void inserer_resultat_par_candidat(String id_bureau_de_vote, String id_candidat, int voix, double pourcentage, Connection connection) throws Exception {
        try{
            String sql = "insert into resultat_par_candidat(id_bureau_de_vote, id_candidat, voix, pourcentage) values(?, ?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_bureau_de_vote);
            preparedStatement.setString(2, id_candidat);
            preparedStatement.setInt(3, voix);
            preparedStatement.setDouble(4, pourcentage);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    public ArrayList<BureauDeVote> getAllBureauDeVote() throws Exception {
        ArrayList<BureauDeVote> list = new ArrayList<>();
        try {
            Connection conn = Connecting.getConnection("postgres");
            java.sql.PreparedStatement ps = conn.prepareStatement("select * from bureau_de_vote");
            java.sql.ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BureauDeVote bureauDeVote = new BureauDeVote();
                bureauDeVote.setId(rs.getString("id"));
                bureauDeVote.setRegion(rs.getString("region"));
                bureauDeVote.setDistrict(rs.getString("district"));
                bureauDeVote.setCommune(rs.getString("commune"));
                bureauDeVote.setFokontany(rs.getString("fokontany"));
                bureauDeVote.setCentre_de_vote(rs.getString("centre_de_vote"));
                bureauDeVote.setNom(rs.getString("nom"));
                list.add(bureauDeVote);
            }
            conn.close();
        } catch (Exception e){
            throw e;
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        //new BureauDeVote().importer_xls_bureau_de_vote_avec_librarie_jxl_et_inserer(null);
        //new BureauDeVote().importer_xls_bureau_de_vote_avec_librarie_jxl_et_inserer(null);
        ArrayList<Candidat> liste_des_candidats = new BureauDeVote().liste_des_candidats(null);
        new BureauDeVote().exploiter_chaque_fichier(new BureauDeVote().listes_de_tous_les_fichier_dans_le_dossier(), liste_des_candidats);
        //new BureauDeVote().listes_de_tous_les_fichier_dans_le_dossier();
    }
}
