package com.winners.isetch.fmeapi.Controller;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.winners.isetch.fmeapi.Entity.Account;
import com.winners.isetch.fmeapi.Service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	 static final String crossOriginUrl="http://localhost:4200";

	@Autowired
	private AccountService accountService;

	@RequestMapping(method=RequestMethod.GET)
    @CrossOrigin(origins = crossOriginUrl)
	public String index(ModelMap modelMap) {
		modelMap.put("account", new Account());
		return"account/index";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
    @CrossOrigin(origins = crossOriginUrl)
	public String login(@ModelAttribute("account") Account account,ModelMap modelMap) {
		if(accountService.findByUsernameAndPassword(account.getUserName(), account.getPassword())!=null) {
		
			return"account/Welcome";
			
		}
		else {
			modelMap.put("error", "Account's Invalid");
			return"account/Index";

		}
	}
}
