package vttp2022.paf.assessment.eshop.controllers;

import java.net.http.HttpHeaders;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.context.support.RequestHandledEvent;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@Controller
@RequestMapping (path = "/index")
public class OrderController {

	private static final int Optional = 0;
	private static final Order Order = null;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private CustomerRepository cusRepo;
	@Autowired
	private OrderRepository orderRepo;

	public ResponseEntity<String> saveOrder(@RequestBody MultiValueMap<String,String> form) {
		String name = form.getFirst("line_item");
		String item = form.getFirst("item");
        String quantity = form.getFirst("quantity");
		

		List<LineItem> lineItems = new LinkedList<>(String : String);
		lineItems.add(new LineItem(item, quantity));
	
		Optional<Customer> valid = cusRepo.findCustomerByName(name);
		if (valid != null) {

			Optional<Order> order = orderRepo.saveOrder(name, lineItems);
			Order orderObject = order.orElse(Order);
			String orderId = orderObject.getOrderId();

			if (order != null) {
				String URL = "http://paf.chuklee.com/dispatch/" + orderId;
				RestTemplate template = new RestTemplate();
				ResponseEntity<String> resp = null;

				resp = template.exchange(
						URL,
						HttpMethod.POST,
						String.class,
						1);
						
        if(resp.getStatusCode() == HttpStatus.OK) {
            logger.info("Response Successful");
        } else {
            logger.info("Response Failed");
        }
        Detector d = Detector.createJson(resp.getBody());
        return d;
    }
				return (ResponseEntity<String>) ResponseEntity.ok();
			} else {
				JsonObjectBuilder job = Json.createObjectBuilder();
				String errorMsg = "Fail to place order.";
				job.add("error", errorMsg);
				
				return new ResponseEntity<>(job.toString(), HttpStatus.NOT_FOUND);
			}
			return (ResponseEntity<String>) ResponseEntity.ok();
			warehouseService.dispatch(null)
		} else {
			JsonObjectBuilder job = Json.createObjectBuilder();
			String errorMsg = "Customer " + name + " not foung";
			job.add("error", errorMsg);
			
			return new ResponseEntity<>(job.toString(), HttpStatus.NOT_FOUND);
		}

		
	}

}
