package com.mycompany.evoting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class CandidateSide extends Person implements CandidateInterface {
    Scanner enter=new Scanner(System.in);
    private int VerifiedCandidate;
    private boolean alreadyRegistered=true;
    private String loginCNIC;
    private String party;
    
    public boolean SelectOption()
    {
        boolean registeration=false;
        System.out.println("Press 1 for Registeration");
        System.out.println("Press 2 for Login");
        int choose=enter.nextInt();
        if(choose==1)
        {
            registeration=true;
        }
        else if(choose==2)
        {
            registeration=false;
        }
        else
        {
            System.out.println("invalid data");
        }
        if(registeration==true)
        {
            return registeration;
        }
        else
        {
            return registeration;
        }
    }
    
    private boolean isThreeDigitInteger(String input) {
        try {
            int number = Integer.parseInt(input);
            return input.length() == 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isValidAlphabetic(String input) {
    return input.matches("^[a-zA-Z\\s]+$");
}
    
    public void InputCandidate()
    {
        System.out.println("enter candidate cnic");
        String candidateCNIC=enter.nextLine();
        candidateCNIC=enter.nextLine();
        if(candidateCNIC.length()==3)
        {
             
            // System.out.println(getCnic());
            if(this.isThreeDigitInteger(candidateCNIC))
            {
                
                setCnic(candidateCNIC);
            }
            else
            {
                System.out.println("enter 3 digits integer only");
                this.InputCandidate();
            }
        }
        else
        {
            System.out.println("please enter 3 digits cnic only");
            this.InputCandidate();
        }
       
       
        FileReader FR=null;
        BufferedReader BR=null;
        try{
            FR=new FileReader("Candidate File.txt");
            BR=new BufferedReader(FR);
            String line;
            while((line=BR.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length>=5)
                {
                    String canCNIC=parts[0].trim();
                    String canNAME=parts[1].trim();
                    String canPROVINCE=parts[2].trim();
                    String canCITY=parts[3].trim();
                    String canPARY=parts[4].trim();
                    if(canCNIC.equals(getCnic()))
                    {
                        System.out.println("please login to proceed further");
                        this.alreadyRegistered=false;
                        break;
                    }
                    else
                    {
                        this.alreadyRegistered=true;
                    }
                }
            }
        }catch(FileNotFoundException dd)
        {
            dd.printStackTrace();
        }
        catch(IndexOutOfBoundsException ss)
        {
            ss.printStackTrace();
        }
        catch(Exception xc){
            xc.printStackTrace();
        }
        finally
        {
            try
            {
                if(BR!=null)
                {
                    BR.close();
                }
                if(FR!=null)
                {
                    FR.close();
                }
            }catch(FileNotFoundException qq)
            {
                qq.printStackTrace();
            }catch(IOException ww)
            {
                ww.printStackTrace();
            }
        }
        if(this.alreadyRegistered==true){
        System.out.println("enter candidate name");
        String CANDIDATEname=enter.nextLine();
        if(this.isValidAlphabetic(CANDIDATEname))
        {
             setName(CANDIDATEname);
        }
        else
        {
            System.out.println("enter only ALPHABET(A-Z)");
            this.InputCandidate();
        }
       
        System.out.println("enter candidate province");
        String CANDIDATEprovince=enter.nextLine();
        if(this.isValidAlphabetic(CANDIDATEprovince))
        {
            setProvince(CANDIDATEprovince);
        }
        else
        {
           System.out.println("enter only ALPHABET(A-Z)");
            this.InputCandidate();
        }
        
        System.out.println("enter your city");
        String CANDIDATEcity=enter.nextLine();
        if(this.isValidAlphabetic(CANDIDATEcity))
        {
            setCity(CANDIDATEcity);
        }
        else
        {
            System.out.println("enter only ALPHABET(A-Z)");
            this.InputCandidate();
        }
        
        System.out.println("enter your party");
        String CANDIDATEparty=enter.nextLine();
        if(this.isValidAlphabetic(CANDIDATEparty))
        {
            this.party=CANDIDATEparty;
        }
        else
        {
             System.out.println("enter only ALPHABET(A-Z)");
            this.InputCandidate();
        }
        
        System.out.println("set your passsword");
        setPassword(enter.nextLine());
        }
        else
        {
            System.out.println("can't register again");
        }
    }
    
    public void StoreLoginInfo()
    {
        FileWriter FW=null;
        BufferedWriter BW=null;
        try
        {
            FW=new FileWriter("CandidateLogin.txt",true);
            BW=new BufferedWriter(FW);
            BW.write(getCnic());
            BW.write(",");
            BW.write(getPassword());
            BW.newLine();
        }catch(Exception hj)
        {
            hj.printStackTrace();
        }
        finally
        {
            try
            {
                if(BW!=null)
                {
                    BW.close();
                }
                if(FW!=null)
                {
                    FW.close();
                }
            }catch(FileNotFoundException ff)
            {
                ff.printStackTrace();
            }
            catch(IOException DD)
            {
                DD.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException gg)
            {
                gg.printStackTrace();
            }
            catch(Exception ff)
            {
                ff.printStackTrace();
            }
        }
    }
    
    public void TOTALvotes()
    {
        int total=0;
        FileReader FR=null;
        BufferedReader BR=null;
        FileReader FRR=null;
        BufferedReader BRR=null;
        try
        {
            FRR=new FileReader("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting\\Vote Stored.txt");
           BRR=new BufferedReader(FRR);
           String lane;
           FR=new FileReader("Candidate File.txt");
           BR=new BufferedReader(FR);
           String line;
           while((line=BR.readLine())!=null)
           {
               String[] parts=line.split(",");
               if(parts.length>=5)
               {
                   String FileCNIC=parts[0].trim();
                   String FileNAME=parts[1].trim();
                   String FilePROVNCE=parts[2].trim();
                   String Filecity=parts[3].trim();
                   String Fileparty=parts[4].trim();
                   if(FileCNIC.equals(loginCNIC))
                   {
                       //System.out.println("candidate file name "+FileNAME);
                      
                       while((lane=BRR.readLine())!=null)
                       {
                           String[] part=lane.split(",");
                           if(part.length>=5){
                           String votCNIC=part[0].trim();
                           String canNAME=part[1].trim();
                           String votPROV=part[2].trim();
                           String votCITY=part[3].trim();
                           String canPARTY=part[4].trim();
                           if(canNAME.equals(FileNAME))
                           {
                               //System.out.println("vote file name "+canNAME);
                               ++total;
                             //  break;
                           }
                           }
                       }
                   }
               }
           }
            System.out.println("you have got "+total+" votes");
        }catch(Exception gg)
        {
            gg.printStackTrace();
        }
        finally
        {
            try
            {
                if(BRR!=null)
                {
                    BRR.close();
                }
                if(FRR!=null)
                {
                    FRR.close();
                }
                if(BR!=null)
                {
                    BR.close();
                }
                if(FR!=null)
                {
                    FR.close();
                }
            }catch(FileNotFoundException gg)
            {
                gg.printStackTrace();
            }
            catch(IOException ss)
            {
                ss.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException ss)
            {
                ss.printStackTrace();
            }
            catch(Exception ff)
            {
                ff.printStackTrace();
            }
        }
    }
    
    public boolean LOGIN()
    {
        boolean ValidLogin=false;
        System.out.println("enter your cnic");
        String canLOGcnic=enter.nextLine();
        canLOGcnic=enter.nextLine();
        if(canLOGcnic.length()==3)
        {
            if(this.isThreeDigitInteger(canLOGcnic))
            {
                loginCNIC=canLOGcnic;
            }
            else
            {
                System.out.println("enter 3 digits integers only");
                this.LOGIN();
            }
            
        }
        else
        {
            System.out.println("please enter 3 digits cnic");
            this.LOGIN();
        }
       //loginCNIC=enter.nextLine();
       //loginCNIC=enter.nextLine();
        System.out.println("enter your password");
        String loginPASSWORD=enter.nextLine();
        FileReader FR=null;
        BufferedReader BR=null;
        try
        {
            FR=new FileReader("CandidateLogin.txt");
            BR=new BufferedReader(FR);
            String line;
            while((line=BR.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length==2){
                    String SeeCNIC=parts[0].trim();
                    String SeePass=parts[1].trim();
                    if(SeeCNIC.equals(loginCNIC)&& SeePass.equals(loginPASSWORD))
                    {
                        System.out.println("verified");
                        ValidLogin=true;
                        break;
                    }
                    else
                    {
                        ValidLogin=false;
                    }
                }
            }
        }catch(FileNotFoundException hh)
        {
            hh.printStackTrace();
        }
        catch(IOException aa)
        {
            aa.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException qq)
        {
            qq.printStackTrace();
        }
        catch(Exception ss)
        {
            ss.printStackTrace();
        }
        finally
        {
            try
            {
                if(BR!=null)
                {
                    BR.close();
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
        if(ValidLogin==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean VerifyCandidate()
    {
        FileReader FR=null;
    BufferedReader BR=null;
    FileWriter FW=null;
                BufferedWriter BW=null;
        try{
        FR=new FileReader("ElectionCommission.txt");
        BR=new BufferedReader(FR);
        String line;
        while((line=BR.readLine())!=null)
        {
            String[] parts=line.split(",");
            if(parts.length>=5){
            String CanCnic=parts[0].trim();
            String CanName=parts[1].trim();
            String CanProvnce=parts[2].trim();
            String CanCity=parts[3].trim();
            String CanParty=parts[4].trim();
            
            if(CanCnic.equals(getCnic())&&CanName.equals(getName())&&CanProvnce.equals(getProvince())&&CanCity.equals(getCity())&&CanParty.equals(this.party))
            {
                this.setVerifiedCandidate(1);
                FW=new FileWriter("Candidate File.txt",true);
                BW=new BufferedWriter(FW);
                BW.write(getCnic());
                BW.write(",");
                BW.write(getName());
                BW.write(",");
                BW.write(getProvince());
                BW.write(",");
                BW.write(getCity());
                BW.write(",");
                BW.write(this.party);
                BW.newLine();
                
                System.out.println("you are valid candidate and you are now registered");
                break;
            }
            else
            {
                this.setVerifiedCandidate(0);
        }
            }
        }
        }catch(Exception hj)
        {
            hj.printStackTrace();
        }
        finally
        {
            try
            {
                if(BR!=null)
                {
                    BR.close();
                }
                if(BW!=null)
                {
                    BW.close();
                }
                
                if(FW!=null)
                {
                    FW.close();
                }
                
                if(FR!=null)
                {
                    FR.close();
                }
                
            }catch(Exception tt)
            {
                tt.printStackTrace();
            }
        }
        if(this.getVerifiedCandidate()==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int getVerifiedCandidate() {
        return VerifiedCandidate;
    }
    
    public void setVerifiedCandidate(int VerifiedCandidate) {
        this.VerifiedCandidate = VerifiedCandidate;
    }

    /**
     * @return the alreadyRegistered
     */
    public boolean isAlreadyRegistered() {
        return alreadyRegistered;
    }

    /**
     * @param alreadyRegistered the alreadyRegistered to set
     */
    public void setAlreadyRegistered(boolean alreadyRegistered) {
        this.alreadyRegistered = alreadyRegistered;
    }
}
