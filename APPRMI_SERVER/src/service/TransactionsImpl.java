package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TransactionsImpl extends UnicastRemoteObject implements TransactionsRMI  {

	protected TransactionsImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void CreateAccount(int id, String name,Date date,int solde,Date datemod) throws RemoteException {
		// TODO Auto-generated method stub
		try {
				File xmlFile = new File("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml");
				if(xmlFile.exists())
				{
					SAXBuilder builder = new SAXBuilder();
					Document document = (Document) builder.build(xmlFile);
					
					Element rootNode = document.getRootElement();
					List list = rootNode.getChildren("Compte");
					
					
					Element Compte = new Element("Compte");
					Compte.setAttribute(new Attribute("id",String.valueOf(list.size()+1)));
					Compte.addContent(new Element("Client").setText(name));
					Compte.addContent(new Element("Date").setText(date.toString()));
					Compte.addContent(new Element("Solde").setText(String.valueOf(solde)));
					Compte.addContent(new Element("DateDerniereModif").setText(datemod.toString()));
		
					document.getRootElement().addContent(Compte);
		
					// new XMLOutputter().output(doc, System.out);
					XMLOutputter xmlOutput = new XMLOutputter();
		
					// display nice nice
					xmlOutput.setFormat(Format.getPrettyFormat());
					xmlOutput.output(document, new FileWriter("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml"));
		
					System.out.println("File Saved!");
				}
				else
				{
					Element banque = new Element("banque");
					Document doc = new Document(banque);
					//doc.setRootElement(banque);
		
					Element Compte = new Element("Compte");
					Compte.setAttribute(new Attribute("id", "1"));
					Compte.addContent(new Element("Client").setText(name));
					Compte.addContent(new Element("Date").setText(date.toString()));
					Compte.addContent(new Element("Solde").setText(String.valueOf(solde)));
					Compte.addContent(new Element("DateDerniereModif").setText(datemod.toString()));
		
					doc.getRootElement().addContent(Compte);
		
					// new XMLOutputter().output(doc, System.out);
					XMLOutputter xmlOutput = new XMLOutputter();
		
					// display nice nice
					xmlOutput.setFormat(Format.getPrettyFormat());
					xmlOutput.output(doc, new FileWriter("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml"));
		
					System.out.println("File Saved!");
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

	@Override
	public List<Object> SearchAccount(String idc) throws RemoteException {
		// TODO Auto-generated method stub
		
		List<Object> Result = new ArrayList<>();
		//TransactionsImpl Client=null;
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml");
		//SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US"));
		try
		{
			
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("Compte");

			for (int i = 0; i < list.size(); i++)
			{	
				Element node = (Element) list.get(i);
				//System.out.println("Client name: "+node.getChildText("Client"));
				if(node.getChildText("Client").contentEquals(idc))
				{
					int ident=Integer.parseInt(node.getAttribute("id").getValue().toString());
					String nam=node.getChildText("Client").toString();
					Date d=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("Date").toString());
					int sld=Integer.parseInt(node.getChildText("Solde").toString());
					Date ddm=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("DateDerniereModif").toString());
					//System.out.println("id: hello"+ident+" \n"+nam+" \n"+d+"\n "+sld+"\n"+ddm);
					//Client=new TransactionsImpl(ident, nam, d, sld, ddm);
					Result.add(ident);
					Result.add(nam);
					Result.add(d);
					Result.add(sld);
					Result.add(ddm);
					return Result;
				}
				else
				{
					System.out.println("Client not found! at id="+i);
					//Result=null;
				}

				//System.out.println("First Name : " + node.getChildText("firstname"));
			}
		} catch (Exception e) {
			System.out.println("exception "+e.getMessage());
		}
		return Result;	
	}

	@Override
	public void TransferAccount(int amount,String src, String dest) throws RemoteException {
		// TODO Auto-generated method stub
		
		File xmlFile = new File("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml");
		
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document) builder.build(xmlFile);
			if(SearchAccount(dest)!=null)
			{				
				Element rootNode = document.getRootElement();
				List list = rootNode.getChildren("Compte");
				
				Element nodesrc = null;
				Element nodedest= null;
				for (int i = 0; i < list.size(); i++)
				{

					Element node = (Element) list.get(i);
					if(node.getChildText("Client").contentEquals(dest))
					{
						nodedest=node;
						int dident=Integer.parseInt(node.getAttribute("id").getValue().toString());
						String dnam=node.getChildText("Client").toString();
						Date dd=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("Date").toString());
						int dsld=Integer.parseInt(node.getChildText("Solde").toString());
						Date dddm=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("DateDerniereModif").toString());
						System.out.println("id: hello from destination "+dident+" \n"+dnam+" \n"+dd+"\n "+dsld+"\n"+dddm);
					}
					else if(node.getChildText("Client").contentEquals(src))
					{
						nodesrc=node;
						int sident=Integer.parseInt(node.getAttribute("id").getValue().toString());
						String snam=node.getChildText("Client").toString();
						Date sd=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("Date").toString());
						int ssld=Integer.parseInt(node.getChildText("Solde").toString());
						Date sddm=new SimpleDateFormat("E MMM dd H:m:s z yyyy",new Locale("en", "US")).parse(node.getChildText("DateDerniereModif").toString());
						System.out.println("id: hello from source "+sident+" \n"+snam+" \n"+sd+"\n "+ssld+"\n"+sddm);
					}
				}
				if(Integer.parseInt(nodesrc.getChildText("Solde").toString())>=amount)
				{
					int ssolde=Integer.parseInt(nodesrc.getChildText("Solde").toString())-amount;
					nodesrc.getChild("Solde").setText(String.valueOf(ssolde));
					
					int dsolde = Integer.parseInt(nodedest.getChildText("Solde").toString())+amount;
					nodedest.getChild("Solde").setText(String.valueOf(dsolde));
				}
				else
				{
					System.out.println("solde insuffisant pour effectuer l'operation");
				}
				
				
				XMLOutputter xmlOutput = new XMLOutputter();
	
				// display nice nice
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(document, new FileWriter("C:\\Users\\LEGEND\\eclipse-workspace\\APPRMI_SERVER\\banque.xml"));
	
				System.out.println("File Saved!");
			}
			else
			{
				System.out.println("ELSE !!! NO CLIENT");
			}
		} catch (Exception e) {
			System.out.println("exception "+e.getMessage());
		}
		
	}
}
