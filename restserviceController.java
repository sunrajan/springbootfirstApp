package sprintBootdemoproject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class restserviceController {
	@RequestMapping("hello")

    public String sayHello(){

        return ("Hello, SpringBoot on welcome test project own code");

    }

}
