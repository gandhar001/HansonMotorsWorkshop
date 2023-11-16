package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations;


import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardEstimateResDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Service
public class PdfService {

  public String parseThymeleafTemplate(JobCardEstimateResDto jobCardEstimated) {

    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();
    context.setVariable("jobCardEntity", jobCardEstimated);
    return templateEngine.process("hansonMotorsEstimate", context);
  }

  public void generatePdfFromHtml(String html) throws IOException, DocumentException {
    String outputPath =
        "C:\\Users\\gandh\\Desktop\\Hanson Motors\\pdf files" + File.separator + "jobCard.pdf";
    OutputStream outputStream = new FileOutputStream(outputPath);

    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);

    outputStream.close();
  }


}
