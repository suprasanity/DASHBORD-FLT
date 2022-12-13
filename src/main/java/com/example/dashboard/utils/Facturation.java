package com.example.dashboard.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Facturation {
    private  String Societe;
    private  String payeur;
    private  String montant;
    private  String date;
    private  String libelle;
    private  String factureName;

    public String fileOutputPath="/home/pi/Deploiment/facture/";

    public Facturation(String societe, String payeur, String montant, String date, String libelle) throws ParserConfigurationException, IOException {
        Societe = societe;
        this.payeur = payeur;
        this.montant = montant;
        this.date = date;
        this.libelle = libelle;

    }


    public Facturation(String factureName) throws IOException {
        HtmlConverter.convertToPdf(new File(fileOutputPath+"template.html"),new File(fileOutputPath+factureName+".pdf"));
    }
}
