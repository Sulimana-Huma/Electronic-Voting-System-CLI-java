/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evoting;

/**
 *
 * @author thinkpad
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Admin extends Person {
    // private static final String FILE_NAME = "NADRA.txt";
    private static final String ADMIN_FILE_NAME = "admin.txt";
    
     Scanner scanner = new Scanner(System.in);

        

            

      public void SelectOption(){
          System.out.print("Enter Admin Name: ");
          setName(scanner.nextLine());
        System.out.print("Enter Admin Password: ");
          setPassword(scanner.nextLine());

        if (verifyAdmin(getName(), getPassword())) {
            System.out.println("Admin Verified Successfully!");
            System.out.print("Enter the file name (NADRA.txt or ElectionCommission.txt): ");
            String fileName = scanner.next();
            
                List<String> entries = loadEntriesFromFile(fileName);
                boolean loop=true;
        while (loop==(true)) {
           
            System.out.println("1. Add Information");
            System.out.println("2. Display Information");
            System.out.println("3. Update Information");
            System.out.println("4. Delete Information");
            System.out.println("5. Save INFO");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:{
                    System.out.println(fileName);
                    if(fileName=="NADRA.txt"){
                        NADRAaddInformation(entries);
                    break;
                    }
                    else
                    {
                        ECaddInformation(entries);
                        break;
                    }
                }
                case 2:
                    displayInformation(entries);
                    break;
                case 3:
                    updateInformation(entries);
                    break;
                case 4:
                    deleteInformation(entries);
                    break;
                case 5:
                    saveEntriesToFile(entries, fileName);
                    System.out.println("Exiting program. Goodbye!");
                    //System.exit(0);
                loop=false;
                break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    
        } 
else {
            System.out.println("Admin Verification Failed. Invalid name or password.");
        }
      }
    

    private static boolean verifyAdmin(String name, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminInfo = line.split(",");
                if (adminInfo.length == 2 && adminInfo[0].equals(name) && adminInfo[1].equals(password)) {
                    return true; // Admin found and verified
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from admin file: " + e.getMessage());
        }
        return false; // Admin not found or error occurred
    }

    private static List<String> loadEntriesFromFile(String fileName) {
        List<String> entries = new ArrayList<>();
        BufferedReader br=null;
        FileReader fr=null;

        try 
        {   fr=new FileReader(fileName);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } 
        return entries;
    }

    private static void saveEntriesToFile(List<String> entries, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < entries.size(); i++) {
                String entry = entries.get(i); 
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void NADRAaddInformation(List<String> entries) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter CNIC: ");
        String cnic=scanner.next();
         BufferedReader reader=null;
        FileReader FR=null;
        try
        {
             FR=new FileReader("NADRA.txt");
            reader=new BufferedReader(FR);
            String line;
            while((line=reader.readLine())!=null)
            {
                String[] voter=line.split(",");
                if(voter.length>=4)
                {
                    String votcnic=voter[0].trim();
                    String votname=voter[1].trim();
                    String votprovince=voter[2].trim();
                    String votcity=voter[3].trim();
                    if(votcnic.equals(cnic))
                    {
                        System.out.println("already registered");
                        break;
                                
                    }
                    else
                    {
                        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Province: ");
        String province = scanner.next();
                        System.out.println("Enter city");
                        String city=scanner.next();
        // Adding information to the list
        entries.add(cnic + "," + name + "," + province+","+city);

        System.out.println("Information added successfully!");
        break;
                    }
                }
            }
        }catch(Exception ff)
        {
            ff.printStackTrace();
        }
        
        finally
        {
            try
            {
                if(reader!=null)
                {
                    reader.close();
                }
                if(FR!=null)
                {
                    FR.close();
                }
            }catch(Exception gg)
            {
                gg.printStackTrace();
            }
        }
        
    }

    
    
    private static void ECaddInformation(List<String> entries) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter CNIC: ");
        String cnic=scanner.next();
         BufferedReader reader=null;
        FileReader FR=null;
        try
        {
             FR=new FileReader("ElectionCommission.txt");
            reader=new BufferedReader(FR);
            String line;
            while((line=reader.readLine())!=null)
            {
                String[] candidate=line.split(",");
                if(candidate.length>=4)
                {
                    String cancnic=candidate[0].trim();
                    String canname=candidate[1].trim();
                    String canprovince=candidate[2].trim();
                    String cancity=candidate[3].trim();
                    if(cancnic.equals(cnic))
                    {
                        System.out.println("already registered");
                        break;
                                
                    }
                    else
                    {
                        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Province: ");
        String province = scanner.next();
                        System.out.println("Enter city");
                        String city=scanner.next();
                        System.out.println("Enter party");
                        String party=scanner.next();
        // Adding information to the list
        entries.add(cnic + "," + name + "," + province+","+city+","+party);

        System.out.println("Information added successfully!");
        break;
                    }
                }
            }
        }catch(Exception ff)
        {
            ff.printStackTrace();
        }
        
        finally
        {
            try
            {
                if(reader!=null)
                {
                    reader.close();
                }
                if(FR!=null)
                {
                    FR.close();
                }
            }catch(Exception gg)
            {
                gg.printStackTrace();
            }
        }
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    private static void displayInformation(List<String> entries) {
        System.out.println("CNIC\t  \tName\t  \tProvince");
        System.out.println("---------------------------------");

        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            String[] parts = entry.split(",");
            System.out.println(parts[0] + "\t\t" + parts[1] + "\t\t" + parts[2]);
        }
    }
    private static void updateInformation(List<String> entries) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the CNIC to update: ");
        String cnicToUpdate = scanner.next();

        boolean found = false;

        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            String[] parts = entry.split(",");

            if (parts[0].equals(cnicToUpdate)) {
                System.out.print("Enter new Name: ");
                String newName = scanner.next();
                System.out.print("Enter new Province: ");
                String newProvince = scanner.next();

                // Update information in the list
                entries.set(i, cnicToUpdate + "," + newName + "," + newProvince);

                System.out.println("Information updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("CNIC not found. No information updated.");
        }
    }
    private static void deleteInformation(List<String> entries) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the CNIC to delete: ");
        String cnicToDelete = scanner.next();

        boolean found = false;

        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            String[] parts = entry.split(",");

            if (parts[0].equals(cnicToDelete)) {
                // Remove information from the list
                entries.remove(i);

                System.out.println("Information deleted successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("CNIC not found. No information deleted.");
        }
    }
}
