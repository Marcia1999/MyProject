package com.mars.proj.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mars.proj.entity.Item;
import com.mars.proj.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	int co=0;
	@Autowired
	private ProductService productService;
	@RequestMapping(method=RequestMethod.GET)
	public String index(ModelMap modelMap,HttpSession session)
	{
		modelMap.put("total",total(session));
		return "cart/index";
	}
	
	@RequestMapping(value="buy/{id}",method=RequestMethod.GET)
	public String buy(@PathVariable("id") int id,HttpSession session)
	{
		if(session.getAttribute("cart")==null)
		{
			List<Item> cart =new ArrayList<Item>();
			cart.add(new Item(productService.findById(id),1));
			session.setAttribute("cart",cart);
			co=1;
		}
		else
		{
			List<Item> cart=(List<Item>)session.getAttribute("cart");
			int val=isExists(id,cart);
			if(val==-1)
			{
				cart.add(new Item(productService.findById(id),1));
			}
			else
			{
				int quantity=cart.get(val).getQuantity()+1;
				cart.get(val).setQuantity(quantity);
			}
			co=1;
		}
		
		return "redirect:../../cart";
	}
	
     private int isExists(int id,List<Item> cart)
     {
    	 for(int i=0;i<cart.size();i++)
    	 {
    		 if(cart.get(i).getProd().getId()==id)
    		 {
    			 return i;
    		 }
    	 }
    	 return -1;
     }
     @RequestMapping(value="remove/{id}",method=RequestMethod.GET)
 	public String remove(@PathVariable("id") int id, HttpSession session)
 	{
    	 List<Item> cart= (List<Item>)session.getAttribute("cart");
			int val=isExists(id,cart);
			cart.remove(val);
			session.setAttribute("cart",cart);
			return "redirect:../../cart";
 	}
     
     @RequestMapping(value="inc/{id}",method=RequestMethod.GET)
 	public String incVal(@PathVariable("id") int id,HttpSession session)
 	{
 			List<Item> cart=(List<Item>)session.getAttribute("cart");
 			int val=isExists(id,cart);
 			
 				int quantity=cart.get(val).getQuantity()+1;
 				cart.get(val).setQuantity(quantity);
	
 		return "redirect:../../cart";
 	}
     
     @RequestMapping(value="dec/{id}",method=RequestMethod.GET)
  	public String decVal(@PathVariable("id") int id,HttpSession session)
  	{
  			List<Item> cart=(List<Item>)session.getAttribute("cart");
  			int val=isExists(id,cart);
  			
  				int quantity=cart.get(val).getQuantity()-1;
  				if(quantity<0)
  				{
  					quantity=0;
  					cart.get(val).setQuantity(quantity);
  				}
  				else
  				{
  				cart.get(val).setQuantity(quantity);
  				}
 	
  		return "redirect:../../cart";
  	}
     
     @RequestMapping(value="seeCart",method=RequestMethod.GET)
     public String viewCart(ModelMap modelMap,HttpSession session)
 	{
    	
    	 if (co==0) {
    		 return "cart/plain";
    	 }
    	 
 		modelMap.put("total",total(session));
 		return "cart/index";
     }
     public String total(HttpSession session)
     {
    	 DecimalFormat df=new DecimalFormat("#.00");
    	 List<Item> cart= (List<Item>)session.getAttribute("cart");
    	 double val=0;
    	 for(Item item:cart)
    	 {
    		 val+=(item.getQuantity()*item.getProd().getPrice())+(item.getQuantity()*item.getProd().getPrice()*item.getProd().getGst()*0.01);
    	 }
    	 if(val==0)
    	 {
    		 return "0";
    	 }
    	 return df.format(val);
     }
}

