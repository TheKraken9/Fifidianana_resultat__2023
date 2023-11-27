package com.project.fifidianana.model;

import com.project.fifidianana.connecting.Connecting;

import java.sql.Connection;
import java.util.ArrayList;

public class ResultatParCandidat {
    private int id;
    private String id_bureau_de_vote;
    private String id_candidat;
    private int voix;
    private double pourcentage;
    private String region;
    private String district;
    private String commune;
    private String fokontany;
    private String centre_de_vote;
    private String nom;

    public ResultatParCandidat() {
    }

    public ResultatParCandidat(int id, String id_bureau_de_vote, String id_candidat, int voix, double pourcentage) {
        this.id = id;
        this.id_bureau_de_vote = id_bureau_de_vote;
        this.id_candidat = id_candidat;
        this.voix = voix;
        this.pourcentage = pourcentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_bureau_de_vote() {
        return id_bureau_de_vote;
    }

    public void setId_bureau_de_vote(String id_bureau_de_vote) {
        this.id_bureau_de_vote = id_bureau_de_vote;
    }

    public String getId_candidat() {
        return id_candidat;
    }

    public void setId_candidat(String id_candidat) {
        this.id_candidat = id_candidat;
    }

    public int getVoix() {
        return voix;
    }

    public void setVoix(int voix) {
        this.voix = voix;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
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

    public ArrayList<ResultatParCandidat> getAllResultat() throws Exception{
        ArrayList<ResultatParCandidat> list = new ArrayList<>();
        try {
            Connection conn = Connecting.getConnection("postgres");
            java.sql.PreparedStatement ps = conn.prepareStatement("select resultat_par_candidat.id_bureau_de_vote as id_bureau_de_vote, resultat_par_candidat.id_candidat, voix, pourcentage, region, district, commune, fokontany, centre_de_vote, nom from resultat_par_candidat join bureau_de_vote on bureau_de_vote.id = resultat_par_candidat.id_bureau_de_vote");
            java.sql.ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ResultatParCandidat resultatParCandidat  = new ResultatParCandidat();
                resultatParCandidat.setId_bureau_de_vote(rs.getString("id_bureau_de_vote"));
                resultatParCandidat.setId_candidat(rs.getString("id_candidat"));
                resultatParCandidat.setVoix(rs.getInt("voix"));
                resultatParCandidat.setPourcentage(rs.getDouble("pourcentage"));
                resultatParCandidat.setRegion(rs.getString("region"));
                resultatParCandidat.setDistrict(rs.getString("district"));
                resultatParCandidat.setCommune(rs.getString("commune"));
                resultatParCandidat.setFokontany(rs.getString("fokontany"));
                resultatParCandidat.setCentre_de_vote(rs.getString("centre_de_vote"));
                resultatParCandidat.setNom(rs.getString("nom"));
                list.add(resultatParCandidat);
            }
            conn.close();
        } catch (Exception e){
            throw e;
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        ResultatParCandidat resultatParCandidat = new ResultatParCandidat();
        ArrayList<ResultatParCandidat> resultatParCandidats = resultatParCandidat.getAllResultat();
        for (ResultatParCandidat resultatParCandidat1 : resultatParCandidats) {
            System.out.println(resultatParCandidat1.getId());
            System.out.println(resultatParCandidat1.getId_bureau_de_vote());
            System.out.println(resultatParCandidat1.getId_candidat());
            System.out.println(resultatParCandidat1.getVoix());
            System.out.println(resultatParCandidat1.getPourcentage());
        }
    }
}
