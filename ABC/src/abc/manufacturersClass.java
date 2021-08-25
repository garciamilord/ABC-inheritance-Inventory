/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

/**
 *
 * @author garci
 */
public class manufacturersClass  {
    private String companyName;
    private addressClass address;
    
       public manufacturersClass (String companyName, addressClass address){
           this.companyName = companyName;
           this.address= address;
       }
       public manufacturersClass(){
           
       }
       public void setCompanyName(String companyName){
           this.companyName=companyName;
       }
       public String getCompanyName(){
        return companyName;   
       }
       public void setAddress(addressClass address){
           this.address = address;
       }
       public addressClass getAddress(){
           return address;
           
       }
    
}
