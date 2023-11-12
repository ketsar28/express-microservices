package com.express.employeeservice.service.impl;

import com.express.employeeservice.entity.Employee;
import com.express.employeeservice.model.response.AddressResponse;
import com.express.employeeservice.model.response.EmployeeResponse;
import com.express.employeeservice.openfeignclients.AddressClient;
import com.express.employeeservice.repository.EmployeeRepository;
import com.express.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;
    private final RestTemplate restTemplate;
//    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;
    private final AddressClient addressClient;

//    @Value("${addressservice.base.url}")
//    private String BASE_URL;


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeResponse> employeeResponse = new ArrayList<>();
//        BeanUtils.copyProperties(employees, employeeResponse);

        List<EmployeeResponse> employeeResponses = Arrays.asList(modelMapper.map(employees, EmployeeResponse[].class));
        employeeResponses.forEach(employeeResponse -> {
            for (AddressResponse addressResponse : addressClient.getAllAddress().getBody()) {
                if(addressResponse.getId().equals(employeeResponse.getId())) {
                    employeeResponse.setAddressResponse(addressResponse);
                }
            }
        });
        return employeeResponses;
    }


    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get(); // db call => 10 seconds (ex)
        // casting to employee response object
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        // addressResponse - set data by making a rest api call
        String uri = "/addresses/" + employeeId;

//        AddressResponse addressResponse = toRestCallAddressByRestTemplateAndLoadBalancerClient(employeeId);
//        AddressResponse addressResponse = toRestCallAddressByRestTemplateAndLoadBalancedAnnotation(employeeId);
        AddressResponse addressResponse = addressClient.getAddressByEmployeeId(employeeId).getBody();

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

    private AddressResponse toRestCallAddressByRestTemplateAndLoadBalancedAnnotation(Integer employeeId) {
        return restTemplate.getForObject("http://address-service/address-app/api/addresses/{employeeId}", AddressResponse.class, employeeId);
    }

    private AddressResponse toRestCallAddressByRestTemplateAndLoadBalancerClient(Integer employeeId) {

//        // get me the detail for the ip and a port number for address service
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("address-service");
//        String URI = "";
//
//        for (ServiceInstance serviceInstance : serviceInstances) {
//            URI = serviceInstance.getUri().toString();
//            System.out.println("URL : " + URI);
//        }

        // apply load balancer with LoadBalancerClient (interface - springframework.cloud)
        ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
        String URI = serviceInstance.getUri().toString();
        String contextRoot = serviceInstance.getMetadata().get("configPath");
        System.out.println("URI (load balancer)  : "  + URI + contextRoot);

        return restTemplate.getForObject(URI + contextRoot + "/addresses/{employeeId}", AddressResponse.class, employeeId);
    }

    private AddressResponse toRestCallAddressByWebClient(Integer employeeId) {
        AddressResponse addressResponse = webClient.get()
                .uri("/addresses/" + employeeId)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
        return addressResponse;
    }


}
