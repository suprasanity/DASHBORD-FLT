package com.example.dashboard.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
import java.io.File;
import java.io.IOException;

@Component
public class Facturation {
    @Autowired
    TemplateEngine templateEngine;
    private  String Societe;
    private  String payeur;
    private  String montant;
    private  String date;
    private  String libelle;
    private  String factureName;

    public String fileOutputPath="/home/pi/Deploiment/facture/";

    @Autowired
    public Facturation(TemplateEngine templateEngine)  {
    this.templateEngine=templateEngine;
    }


    public void build (String libelle,String contracteur , int prix) throws IOException {

        Context ctx = new Context();
        ctx.setVariable("libelle", libelle);
        ctx.setVariable("contracteur", contracteur);
        ctx.setVariable("prix", prix);

        templateEngine.process("template",ctx);
        HtmlConverter.convertToPdf(new File(fileOutputPath+"template.html"),new File(fileOutputPath+factureName+".pdf"));
    }
}
