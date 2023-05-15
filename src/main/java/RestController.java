@RestController
@SpringBootApplication
public class RestController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(RestController.class, args);
    }
}
