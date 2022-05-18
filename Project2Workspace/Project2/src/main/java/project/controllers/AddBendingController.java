package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import project.service.AccountService;

@RestController
public class AddBendingController {
	
	AccountService serv;
	
	
	@Autowired
	public AddBendingController(AccountService serv) {
		super();
		this.serv = serv;
	}



	@PostMapping(value="/addwaterbending")
	public void addWaterBending() {
		
		String bendingStyle ="Water Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addearthbending")
	public void addEarthBending() {
		
		String bendingStyle ="Earth Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addairbending")
	public void addAirBending() {
		
		String bendingStyle ="Air Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	@PostMapping(value="/addfirebending")
	public void addFireBending() {
		
		String bendingStyle ="Fire Bending";
		
		serv.updateBending(bendingStyle);
		
	}
	
	
}
