package dev.customer.data;

import java.util.HashSet;
import java.util.Set;

import dev.customer.entities.Bank;
import dev.customer.entities.Corporation;

/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
public class InitialData {
	public static Set<Corporation> GenerateData() {

		Set<Corporation> banks = new HashSet<>();
		Bank b1 = new Bank("123453333","445566789"); 
		banks.add(b1); 

		Bank b2 = new Bank("129857833","445344389"); 
		banks.add(b2); 
		return banks;
	}
}
