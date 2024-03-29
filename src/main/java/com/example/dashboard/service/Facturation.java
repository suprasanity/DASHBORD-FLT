package com.example.dashboard.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class Facturation {
    @Autowired
    TemplateEngine templateEngine;
    @Value("${path.facture}")
    public String fileOutputPath;

    public final Logger logger = LoggerFactory.getLogger(Facturation.class);

    @Autowired
    public Facturation(TemplateEngine templateEngine)  {
    this.templateEngine=templateEngine;
    }


    public void build (String contracteur,String libelle, int prix,long num) throws IOException {

        Context ctx = new Context();
        ctx.setVariable("Item", libelle);
        ctx.setVariable("contracteur", contracteur);
        ctx.setVariable("Price", prix);

        String s=templateEngine.process("template",ctx);
        File f = new File(fileOutputPath +contracteur+num+".html");
        PrintWriter writer = new PrintWriter(fileOutputPath +contracteur+num+".html", "UTF-8");
        writer.print(s);
        writer.close();
        HtmlConverter.convertToPdf(f,new File(fileOutputPath +contracteur+num+".pdf"));
        try{
            Files.deleteIfExists(Paths.get(f.getAbsolutePath()));
            logger.info("File deleted successfully");
        }catch (IOException e){
            logger.error(e.getMessage());
            logger.error("Failed to delete the file");
        }
    }
}
