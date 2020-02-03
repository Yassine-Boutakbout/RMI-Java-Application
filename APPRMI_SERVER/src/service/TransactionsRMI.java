package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface TransactionsRMI extends Remote{
	public void CreateAccount(int id, String name,Date date,int solde,Date datemod) throws RemoteException;
	public List SearchAccount(String id) throws RemoteException;
	public void TransferAccount(int amount, String src, String dest) throws RemoteException;
}