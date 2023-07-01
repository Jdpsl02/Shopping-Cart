package com.eshoppingzone.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.eshoppingzone.ewallet.models.Ewallet;
import com.eshoppingzone.ewallet.models.Statement;
import com.eshoppingzone.ewallet.resource.EwalletResource;
import com.eshoppingzone.ewallet.service.EwalletService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes= {EwalletResourceTests.class})
public class EwalletResourceTests {
	  @Mock
	    private EwalletService ewalletService;

	    @InjectMocks
	    private EwalletResource ewalletResource;

	    @Test
	    public void testGetWallets() {
	        List<Ewallet> wallets = Arrays.asList(new Ewallet(), new Ewallet());
	        Mockito.when(ewalletService.getWallets()).thenReturn(wallets);

	        List<Ewallet> response = ewalletResource.getWallets();

	        assertEquals(wallets.size(), response.size());
	    }
	    
	    
	    @Test
	    public void testAddWalletForProfile() {
	        int profileId = 1;
	        Ewallet wallet = new Ewallet();
	        Mockito.when(ewalletService.addWalletForProfile(profileId)).thenReturn(wallet);

	        Ewallet response = ewalletResource.addWalletForProfile(profileId);

	        assertEquals(wallet, response);
	    }
	    
	
	    @Test
	    public void testAddWallet() {
	        int amount = 100;
	        int profileId = 1;

	        ewalletResource.addWalet(amount, profileId);

	        Mockito.verify(ewalletService, Mockito.times(1)).addMoney(profileId, amount);
	    }
	    
	    
	    
	    
	    @Test
	    public void testDoTransaction() {
	        int profileId = 1;
	        double amount = 50.0;

	        ewalletResource.doTransaction(profileId, amount);

	        Mockito.verify(ewalletService, Mockito.times(1)).doTransaction(profileId, amount);
	    }
	    
	    
	    
	    @Test
	    public void testGetWalletById() {
	        int profileId = 1;
	        Ewallet wallet = new Ewallet();
	        Mockito.when(ewalletService.getWalletById(profileId)).thenReturn(wallet);

	        Ewallet response = ewalletResource.getWalletById(profileId);

	        assertEquals(wallet, response);
	    }
	    
	    
	    
	    
	    @Test
	    public void testGetAllStatements() {
	        List<Statement> statements = Arrays.asList(new Statement(), new Statement());
	        Mockito.when(ewalletService.getAllStatements()).thenReturn(statements);

	        List<Statement> response = ewalletResource.getAllStatements();

	        assertEquals(statements.size(), response.size());
	    }
	    
	    
	    @Test
	    public void testDeleteWalletByEwalletId() {
	        int ewalletId = 1;
	        Mockito.when(ewalletService.deleteWalletById(ewalletId)).thenReturn("Wallet deleted");

	        String response = ewalletResource.deleteWalletByEwalletId(ewalletId);

	        assertEquals("Wallet deleted", response);
	    }
	    
	    
}
