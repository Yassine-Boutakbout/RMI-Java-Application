package service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RemoteTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			LocateRegistry.createRegistry(3000);
			TransactionsImpl ob=new TransactionsImpl();
			System.out.println(ob.toString());
			Naming.rebind("//Localhost:3000/Remote", ob);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}