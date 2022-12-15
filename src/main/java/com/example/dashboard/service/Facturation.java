package com.example.dashboard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class Facturation {
    @Autowired
    TemplateEngine templateEngine;
    private  String factureName;

    public String fileOutputPath="/home/pi/Deploiement/facture/";

    @Autowired
    public Facturation(TemplateEngine templateEngine)  {
    this.templateEngine=templateEngine;
    }


    public void build (String contracteur,String libelle, int prix) throws IOException {

        Context ctx = new Context();
        ctx.setVariable("Libelle", libelle);
        ctx.setVariable("contracteur", contracteur);
        ctx.setVariable("Prix", prix);

        String s=templateEngine.process("template",ctx);
        File f = new File(fileOutputPath+contracteur+".html");
        PrintWriter writer = new PrintWriter(fileOutputPath+contracteur+".html", "UTF-8");
        writer.print(s);
        writer.close();
        HtmlConverter.convertToPdf(f,new File(fileOutputPath+contracteur+".pdf"));
        f.delete();
    }
}
