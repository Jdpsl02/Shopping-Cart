package com.eshoppingzone.ewallet.resource;

import java.util.List; 
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshoppingzone.ewallet.models.Ewallet;
import com.eshoppingzone.ewallet.models.Statement;
import com.eshoppingzone.ewallet.repositories.EwalletRepository;
import com.eshoppingzone.ewallet.repositories.StatementRepository;
import com.eshoppingzone.ewallet.service.EwalletService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/ewallet")
@CrossOrigin(origins="*" ,maxAge = 3600)
public class EwalletResource {
	
	@Autowired
	EwalletService ewalletService;
	
	@Autowired
	StatementRepository stateRepository;
	
	@GetMapping("/all")
	public List<Ewallet> getWallets(){
		return ewalletService.getWallets();
	}
	
//	@PostMapping("/add")
//	public Ewallet addWallet(@RequestBody Ewallet addwallet) {
//		return ewalletService.addWallet(addwallet);
//	}
//	
	@PostMapping("/addForProfile/{profileId}")
	public Ewallet addWalletForProfile(@PathVariable("profileId") int profileId) {
		return ewalletService.addWalletForProfile(profileId);
	}
	
	@PostMapping("/addmoney/{amount}")
	public String onlinePayment( @PathVariable int amount ) throws RazorpayException {
		
		int amt= amount;
		System.out.println(amt);
		
		 RazorpayClient client =  new RazorpayClient("rzp_test_BL4rFuV9nKRoMc","g91auwnxjWhSoguFayUZScuo" );
		 JSONObject options = new JSONObject();
		 options.put("amount", amt*100);
		 options.put("currency", "INR");
		 options.put("receipt", "txn_123456");
		 Order order = client.Orders.create(options);
		 System.out.println(order);
		 return order.toString();
	}
	
	@PostMapping("/addMoneyWallet/{amount}/{profileId}")
	public void  addWalet(@PathVariable int amount,@PathVariable int profileId) {
		 ewalletService.addMoney(profileId, amount);
	}
	@PostMapping("/transaction/{amount}/{profileId}")
	public void doTransaction(@PathVariable int profileId, @PathVariable("amount") double amount) {
		ewalletService.doTransaction(profileId, amount);
	}
	
	@GetMapping("/getById/{profileId}")
	public Ewallet getWalletById(@PathVariable("profileId") int profileId){
		return ewalletService.getWalletById(profileId);
	}
	
	@GetMapping("/statement/byid/{statementId}")
	public List<Statement> getStatementById(@PathVariable("statementId") int statementId){
		return ewalletService.getStatementById(statementId);
	}
	
	@GetMapping("/statement/all")
	public List<Statement> getAllStatements(){
		return ewalletService.getAllStatements();
	}
	
	@DeleteMapping("/delete/{ewalletId}")
	public String deleteWalletByEwalletId(@PathVariable("ewalletId") int ewalletId) {
		return ewalletService.deleteWalletById(ewalletId);
	}
	@DeleteMapping("/deleteS/{profileId}")
	public String deleteWalletById(@PathVariable("profileId") int profileId) {
		stateRepository.deleteById(profileId);
		return "done";
	}
}