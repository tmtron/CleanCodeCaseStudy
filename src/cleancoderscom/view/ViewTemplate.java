package cleancoderscom.view;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewTemplate {
  private String template;

  public static ViewTemplate create(String templateResource) throws IOException {
    URL url = ClassLoader.getSystemResource(templateResource);
    // make it work on Windows: https://stackoverflow.com/a/32722617/6287240
    String cleanedPath = url.getPath().replaceFirst("^/(.:/)", "$1");
    Path path = Paths.get(cleanedPath);
    byte[] bytes = Files.readAllBytes(path);
    return new ViewTemplate(new String(bytes));
  }

  public ViewTemplate(String template) {

    this.template = template;
  }

  public void replace(String tagName, String content) {
    template = template.replace("${" + tagName + "}", content);
  }

  public String getContent() {
    return template;
  }
}
