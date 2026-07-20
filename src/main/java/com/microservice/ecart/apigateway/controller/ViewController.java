package com.microservice.ecart.apigateway.controller;
/**********
 * Below is Good Code------------
 * 
 * 
 * The Below is more older code and good code........
 * 
 */

/*



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

//=================================================

import com.microservice.ecart.apigateway.repository.UserAppRepository;

@Controller
//@RestController

public class ViewController {

    private final WebClient.Builder webClientBuilder;
    
  //  private final JwtUtil jwtUtil; //by s Das 28-05-26
 //   private final PasswordEncoder passwordEncoder; //by s Das 28-05-26

    public ViewController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    
   
  */  
    
    
    
    
    /*
    
    @PostMapping("/api/auth/login")
    @ResponseBody
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Map<String, String> request) { 
        String email = request.get("email"); // Extracts email string from request payload
        String password = request.get("password"); 
     
        return UserAppRepository.findByEmail(email) // Looks up user using the email index
            .flatMap(user -> { 
                if (password != null && password.equals(user.getPassword())) { 
                    // Tokenizes the email address into the JWT payload
                    String token = jwtUtil.generateToken(user.getEmail()); 
     
                    return Mono.just(ResponseEntity.ok(Map.of( 
                        "token", token, 
                        "email", user.getEmail() 
                    ))); 
                } 
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
                    .body(Map.of("error", "Invalid credentials"))); 
            }) 
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
                .body(Map.of("error", "User not found"))); 
    }
    */
    
    /*
    
    

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        // Fetch data from Product Service registered in Eureka
        Flux<Map> productFlux = webClientBuilder.build()
                .get()
                .uri("http://PRODUCT-SERVICE/api/products") // Adjust URL path to your Product API
                .retrieve()
                .bodyToFlux(Map.class);
                 //.collectList()      
                // .block(); 

        // Multi-advantage: Streams data reactively to Thymeleaf context
        model.addAttribute("products", new ReactiveDataDriverContextVariable(productFlux, 1));
        
        return "index"; // Looks for templates/index.html
    }
    
    
    // Run ok code for the controller on / before 28-05-2026...........
    
  
    */
    
    
    


    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.reactive.function.client.WebClient;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.HttpStatus;
    import reactor.core.publisher.Mono;

    import java.util.Map;

import com.microservice.ecart.apigateway.model.UserApp;
// Correct Custom Class Imports
    import com.microservice.ecart.apigateway.repository.UserAppRepository;
    import com.microservice.ecart.apigateway.util.JwtUtil; // Adjust this package declaration if your JwtUtil is located elsewhere

    

   // @Controller
    @RestController
    public class ViewController {

        private final UserAppRepository userAppRepository;
        private final JwtUtil jwtUtil;
        private final WebClient.Builder webClientBuilder; // webClient builder is acted by S Das 0n 07-26
        
        
        

        public ViewController(UserAppRepository userAppRepository, JwtUtil jwtUtil,WebClient.Builder webClientBuilder) {
            this.userAppRepository = userAppRepository;
            this.jwtUtil = jwtUtil;
            this.webClientBuilder = webClientBuilder;
        }
        
        //below constructor is ok..........
        
        /*
        public ViewController(UserAppRepository userAppRepository, JwtUtil jwtUtil) {
            this.userAppRepository = userAppRepository;
            this.jwtUtil = jwtUtil;
        }
        
        */
        

        // Programmatic endpoint processing JSON requests continues to work seamlessly
        @PostMapping("/api/auth/login")
        @ResponseBody
        public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Map<String, String> request) { 
            String email = request.get("email"); 
            String password = request.get("password"); 
         
            return userAppRepository.findByEmail(email) 
                .flatMap(user -> { 
                    if (password != null && password.equals(user.getPassword())) { 
                        String token = jwtUtil.generateToken(user.getEmail()); 
         
                        return Mono.just(ResponseEntity.ok(Map.of( 
                            "token", token, 
                            "email", user.getEmail() 
                        ))); 
                    } 
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
                        .body(Map.of("error", "Invalid credentials"))); 
                }) 
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
                    .body(Map.of("error", "User not found"))); 
        }
    
/*******
 * 
 * 
 * 
 * Below is For New  User register
 * 
 * 
 * @param request
 * @return
 */
        
        
        
        
    @PostMapping("/api/auth/register")
    @ResponseBody
    public Mono<ResponseEntity<Map<String, String>>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String address = request.get("address");
        String mobileNo = request.get("mobile_no");

        if (username == null || username.isBlank() || 
            email == null || email.isBlank() || 
            password == null || password.isBlank()) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Username, email, and password are required fields")));
        }

        return userAppRepository.findByEmail(email)
                .flatMap(existingUser -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "This email address is already registered"))))
                .switchIfEmpty(Mono.defer(() -> {
                    // Initialize entity matching your repository constraints
                    UserApp newUser = new UserApp();
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setPassword(password); // Remember to encode this in production environments!
                    newUser.setAddress(address);
                    newUser.setMobile_no(mobileNo);
                    
                    return userAppRepository.save(newUser)
                            .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED)
                                    .body(Map.of("message", "User registered successfully")));
                }));
    }
 
       
    /*
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Resolves to register.html
    }
    */
    
    
    
    
        
    }//class end.......     
        
        
        /*
        @GetMapping({"/", "/index"})
        public String index() {
            return "forward:/index.html"; 
        }
		*/
        
        /*
        @GetMapping("/api/auth/login-page")
        public String loginPage() {
            return "forward:/login.html"; 
        }
        */


   