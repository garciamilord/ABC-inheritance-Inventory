/*Garcia Milord ID 6168616
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author garci
 */
public class ABC {

    /**
     * @param args the command line arguments
     */
    static SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args) throws ParseException {
        String state ,companyName, productName, dateManu;
        int quantity, subMenu =0;
        double price;
        manufacturersClass factory = new manufacturersClass();
        productClass item = new productClass();
        addressClass add = null;
        databaseClass productDB = new databaseClass();
        databaseClass deletedProdDB = new databaseClass();
        Date present = new Date();
        boolean isDone = false;

        while (!isDone) {

            int selectedMenu = IO.getInt(
                    "Welcome to Product Inventory\n\t1. Create Products\n"
                    + "\t2. Update Product (Quantity/Price)\n\t3. Delete Product\n"
                    + "\t4. To locate single product\n\t5. Inventory Report\n"
                    + "\t6. List of deleted Products\n\t7. Quit");

            switch (selectedMenu) {
                case 1:
                    productName = IO.getWord("Enter product name: ");
                    quantity = IO.getInt("Enter the quantity of product: ");
                    price = IO.getDouble("Enter the unit price of product(in $): ");
                    companyName = IO.getWord(
                            "Enter the name of the manufacturing product: ");
                    state = IO.getWord("Enter state address of company: ");

                    dateManu = IO.getWord(
                            "Enter date of manufacturing product in the form of(MM/dd/yyyy): ");
                    add = new addressClass(state);
                    factory = new manufacturersClass(companyName, add);
                    present = currentDate.parse(dateManu);

                    item = new productClass(productName, quantity, price, present, factory);

                    productDB.add(item);
                    break;

                case 2:

                    productName = IO
                            .getWord("Enter product name to update: ");

                    subMenu = IO.getInt(
                            "Select option to update: \n1. To update quantity\n2. To update price\n");
                    productDB.search(productName);
                   
                    if (!productDB.inList()) {
                       
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    } 
                    else {
                        
                        switch (subMenu) {
                           
                            case 1:
                                
                                int menuOpt = IO.getInt(
                                        "Select option to update: \n1. To add quantity\n2. To deduct quantity\n");
                                int addRDeductQuant = 0;
                               
                                if (menuOpt < 1 || menuOpt > 2) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please chose the correct choice");
                                } else {
                                   
                                    switch (menuOpt) {
                                        
                                        case 1:
                                            addRDeductQuant = IO.getInt(
                                                    "Enter the amount of quantity to add: ");
                                            
                                            item = productDB.getProduct();
                                            
                                            item.upDateQuantity(addRDeductQuant);
                                            break;
                                        case 2:
                                            addRDeductQuant = IO.getInt(
                                                    "Enter the amount of quantity to deduct: ");
                                            item = productDB.getProduct();
                                            
                                            item.upDateQuantity(addRDeductQuant * -1);
                                            break;
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Quantity of the product is uupdated successfully.");
                                }
                                break;
                            case 2:
                                double priceToUpdate = IO.getDouble(
                                        "Enter the unit price of product to update: ");

                                if (priceToUpdate < 0) {
                                    JOptionPane.showMessageDialog(null,
                                            "Price of the product is not updated.");
                                } else {
                                   
                                    item = productDB.getProduct();
                                    
                                    item.upDatePrice(priceToUpdate);
                                    
                                    JOptionPane.showMessageDialog(null,
                                            "Price of the product is updated successfully.");
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,
                                        "Unable to process the option selected from the update list.");
                        }
                    }
                    break;

                
                case 3:

                    
                    productName = IO
                            .getWord("Enter product name to update: ");

                    
                    productDB.search(productName);

                    
                    if (productDB.inList()) {
                        
                        int index = productDB.getIndex();

                        
                        deletedProdDB.add(productDB.getProduct());

                        
                        productDB.delete(index);

                        
                        JOptionPane.showMessageDialog(null, "The \"" + productName
                                + "\" product is deleted successfully.");
                    } 
                    
                    else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }
                    break;

                
                case 4:

                    
                    productName = IO
                            .getWord("Enter name of the product to display about: ");

                    
                    productDB.search(productName);

                    
                    if (productDB.inList()) {
                        
                        displaySingleProduct(productDB.getProduct(),
                                JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }
                    break;

                case 5:
                    
                    if (productDB.getList() != null) {
                        
                        displayInventory(productDB,
                                JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog(null,
                                "There are no products present in the inventory.");
                    }
                    break;

                
                case 6:
                   
                    
                    if (deletedProdDB.getList() != null) {
                        displayInventory(deletedProdDB,
                                JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog(null,
                                "There are no products present in the inventory.");
                    }
                    break;

                case 7:
                    isDone = true;
                    break;

               
                    
                default:
                    JOptionPane.showMessageDialog(null,
                            "Unable to process the option selected from the menu list.");
            }
        }
    }

    
    public static String getFormatedProductInfo(productClass info) {
        String result = String.format("%30s", info.getProduct());
        result += String.format("%30s", currentDate.format(info.getProductCreated()));
        result += String.format("%30s", info.getManufacturers().getCompanyName());
        return result;
    }

    
    public static void displayDeletedInventory(databaseClass productDB,
            int Type_Message) {
        String inventResult = "";
        ArrayList<productClass> prodList = productDB.getList();
        inventResult += String.format("%30s %30s %30s", "Product",
                "Purchase Date", "Manufacturer");
        for (int i = 0; i < productDB.size(); i++) {
            inventResult += getFormatedProductInfo(prodList.get(i)) + "\n";
        }
        JTextArea text = new JTextArea(inventResult, 10, 50);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, "Deleted Inventory Details",
                Type_Message);
    }

    
    public static void displayInventory(databaseClass productDB, int Type_Message) {
        String inventResult = "";
        ArrayList<productClass> prodList = productDB.getList();
        inventResult += String.format("%-30s \t%s %10s %15s %20s %15s\n",
                "Product", "Purchase Date", "Quantity", "Price($)",
                "Manufacturer", "State");
        for (int i = 0; i < productDB.size(); i++) {
            inventResult += prodList.get(i).getProductInfomation() + "\n";
        }
        JTextArea text = new JTextArea(inventResult, 10, 60);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, "Inventory Details",
                Type_Message);
    }

    
    public static void displaySingleProduct(productClass product, int Type_Message) {
        String productInfo = "Product Name: " + product.getProduct() + "\n";
        productInfo += String.format("Product's Unit Price: $%.2f",
                product.getPrice()) + "\n";
        productInfo += "Quantity of product: " + product.getQuantity() + "\n";
        JTextArea text = new JTextArea(productInfo, 10, 30);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane,
                product.getProduct() + " Details", Type_Message);
    }
}

// TODO code application logic here

