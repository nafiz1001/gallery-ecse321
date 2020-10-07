package ca.mcgill.ecse321.gallery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GalleryApplication {

  public static void main(String[] args) {
    SpringApplication.run(GalleryApplication.class, args);
  }

  @RequestMapping("/")
  public String greeting(){
    return "Hello world!";
  }

}