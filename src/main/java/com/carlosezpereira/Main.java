package com.carlosezpereira;
import org.springframework.boot.SpringApplication;
import  org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication //
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setAge(request.age());
        customer.setEmail(request.email());
        customer.setName(request.name());
        customerRepository.save(customer);
    }

    @DeleteMapping("/del/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("/up/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}
    //    @GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response = new GreetResponse(
//                "Hello!",
//                List.of("Java","Javascript","Python"),
//                new Person("Carlos",24,30_0000));
//        return response;
//    }
//
//    record Person(String name, int age, double savings){
//
//    }

//    record GreetResponse(
//            String greet,
//            List<String> favoriteProgrammingLanguages,
//            Person name
//    ){
//
//    }

//    class GreetResponse{
//        private final String greet;
//        public GreetResponse(String greet){
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString(){
//            return "GreetResponse{\"greet\"=\"" + greet + "\"}";
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
