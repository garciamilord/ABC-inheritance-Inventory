/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author garci
 */
public class productClass {
    private String productName;
    private int quantity;
    private double price;
    private manufacturersClass manufacturer;
    private SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
    private Date productCreated;

    public productClass(String ProductName, int quantity, double price, Date productCreated, manufacturersClass manufacturer){
        
     this.productName = ProductName;
     this.quantity = quantity;
     this.price= price;
     this.manufacturer = manufacturer;
     this.productCreated= productCreated;
}
    public productClass(){
        
    }
    public String getProduct (){
        
        return productName;   
    }
   
    public int getQuantity(){
        
        return quantity;
    }
       public void setQuantity(int quantity)
   {
       this.quantity = quantity;
   }
public void setPrice(double price)
   {
       this.price = price;
   }
   public void upDateQuantity(int quantity_upDate)
   {
       quantity += quantity_upDate;
   }
   public void upDatePrice(double price_upDate)
   {
       this.price = price_upDate;
   }


    public double getPrice(){
        
        return price;
    }
    public manufacturersClass getManufacturers(){
        return manufacturer;
    }
     public Date getProductCreated()
   {
       return productCreated;
   }
  
   // to set the date of the product manufactured
   public void setProductCreated(Date productCreated)
   {
       this.productCreated = productCreated;
   }
   public String getProductInfomation()
   {
       String result = "";
       result += String.format("%-30s", productName);
       String dateForm = date.format(productCreated);
       result += String.format("\t %s", dateForm);
       result += String.format("%10d", quantity);
       result += String.format("\t%15.2f", price);
       result += String.format("\t%15s",
                           manufacturer.getCompanyName());
       result += String.format("\t%20s",
                  manufacturer.getAddress().getState());
       return result;
   }  

    public SimpleDateFormat date(){
        return date;
        
    }
}
