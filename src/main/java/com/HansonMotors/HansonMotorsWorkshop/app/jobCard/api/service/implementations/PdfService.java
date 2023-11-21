package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations;


import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardEstimateResDto;
import com.lowagie.text.DocumentException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Service
@AllArgsConstructor
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

  public void generatePdfFromHtml(String html, long jobCardId, String jobCardEstimatesPath)
      throws IOException, DocumentException {
    String outputPath = jobCardEstimatesPath + jobCardId + ".pdf";
    OutputStream outputStream = new FileOutputStream(outputPath);

    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);

    outputStream.close();
  }


}
