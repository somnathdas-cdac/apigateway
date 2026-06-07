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
    import org.springframework.web.reactive.function.client.WebClient;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.HttpStatus;
    import reactor.core.publisher.Mono;

    import java.util.Map;

    // Correct Custom Class Imports
    import com.microservice.ecart.apigateway.repository.UserAppRepository;
    import com.microservice.ecart.apigateway.util.JwtUtil; // Adjust this package declaration if your JwtUtil is located elsewhere

    

    @Controller
    public class ViewController {

        private final UserAppRepository userAppRepository;
        private final JwtUtil jwtUtil;

        public ViewController(UserAppRepository userAppRepository, JwtUtil jwtUtil) {
            this.userAppRepository = userAppRepository;
            this.jwtUtil = jwtUtil;
        }

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
    }

        
        
        
        
        
        
        
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


   