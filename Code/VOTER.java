/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evoting;

/**
 *
 * @author thinkpad
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class VOTER extends Person implements VoterInterface{
    Scanner enter=new Scanner(System.in);
    private boolean ValidCandidate;
    private String nameCandidate;
    private int validPassword;
    private boolean CastedVote;
    private int alreadyRegistered;
    private int VerifiedVoter;
    public void VoterSide(){
   boolean loop=true;
   while(loop){
            System.out.println("press 1 for voter registeration");
            System.out.println("press 2 for voter login");
            System.out.println("Press 3 to exit");
            char pick=enter.next().charAt(0);
            switch(pick)
            {
                case '1':
                {
                    this.InputVoter();
                    if(this.getAlreadyRegistered()==1)
                    {
                    boolean b = this.Verify();
                    if (b == true) {
                        System.out.println("Successfully Verified.");
                        this.SetPassword();
                        boolean dc=this.DisplayCandidate();
                        if(dc==true){
                        CasteVote();
                        break;
                        }
                        else
                        {
                            System.out.println("no candidate matched so u can't cast vote");
                        } 
                    }else {
                        System.out.println("Not Verified.");
                    }
                    }
                    else
                    {
                        System.out.println("you are already registered please press 2 to login and proceed further");
                    }
                    break;
                }
                
                case '2':
                {
                   Scanner sc=new Scanner(System.in);
                    System.out.print("Enter CNIC: ");
                    String VOTERLOGIN=sc.next();
                    if(VOTERLOGIN.length()==3)
                    {
                        if(this.isThreeDigitInteger(VOTERLOGIN))
                        {
                            setCnic(VOTERLOGIN);
                        }
                        else
                        {
                            System.out.println("Enter 3 digits integer only");
                            break;
                        }
                     
                      
                    }
                    else
                    {
                        System.out.println("please enter 3 digits cnic only");
                        break;
                    }
                    
                   
                    System.out.print("Enter Password: ");
                    String pass=sc.nextLine();
                    pass=sc.nextLine();
                    setPassword(pass);
                    boolean passverify=this.verifyLogin();
                    if(passverify==true)
                    {
                     boolean Post=this.alreadyCasted();
                        if(Post==true)
                        {
                            this.fetchCITY();
                            System.out.println(getCity());
                            boolean dc=this.DisplayCandidate();
                            if(dc==true){
                            this.CasteVote();
                            }
                            else
                            {
                                System.out.println("no candidate matched so you can't cast vote");
                            }
                        } 
                        else
                        {
                            System.out.println("you have casted already");
                        }                     
                    }
                    else
                    {
                        System.out.println("invalid login info");
                    }
                    break;
                }
                case '3':
                {
                    loop=false;
                    break;
                }
            }
}
    }
    
    
    
    private boolean isValidAlphabetic(String input) {
    return input.matches("^[a-zA-Z\\s]+$");
}
    
    
    public boolean  DisplayCandidate() {
        boolean FoundCandidates=false;
        BufferedReader reader=null;
        FileReader FR=null;
        try{
            FR=new FileReader("Candidate File.txt");
            reader=new BufferedReader(FR);
            String line;
                while((line=reader.readLine())!=null){
               String[] CanInfo=line.split(",");
                 if(CanInfo.length>=5){
                String cancnc=CanInfo[0].trim();
                    String canname=CanInfo[1].trim();
                    String canprovnce=CanInfo[2].trim();
                    String cancity=CanInfo[3].trim();
                    String canParty=CanInfo[4].trim();
                    if(cancity.equals(getCity())){
                        System.out.println("you can cast vote to following candidates");
                        FoundCandidates=true;
                        System.out.println("Name is: "+canname);
                        System.out.println("city is: "+cancity);
                        System.out.println("Party is: "+canParty);
                       // break;
                    }
                    }
            }
                if(!FoundCandidates)
                {
                    System.out.println("no matched candidate found");
                }
        }catch(ArrayIndexOutOfBoundsException uu)
        {
            uu.printStackTrace();
        }
        catch (Exception e){e.printStackTrace();}
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
            }catch(FileNotFoundException gg)
            {
                gg.printStackTrace();
            }
            catch(IOException gg)
            {
                gg.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException v){
                v.printStackTrace();
            }
            catch(Exception yy)
            {
                yy.printStackTrace();
            }
        }
        if(FoundCandidates==true)
        {
            return FoundCandidates;
        }
        else
        {
            return FoundCandidates;
        }
    }
    
    public void StoreVote()
    {
        BufferedWriter bw=null;
        FileWriter fw=null;
        BufferedReader BR=null;
        FileReader FR=null;
        try
        {
            FR=new FileReader("Candidate File.txt");
            BR=new BufferedReader(FR);
            
            String line;
            while((line=BR.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length>=5){
                String CanCNIC=parts[0].trim();
                String CanNAME=parts[1].trim();
                String CanPROVNCE=parts[2].trim();
                String CanCity=parts[3].trim();
                String CanParty=parts[4].trim();
                if(CanCity.equals(getCity())){
                if(CanNAME.equals(this.nameCandidate))
                {
                    fw=new FileWriter("Vote Stored.txt",true);
                bw=new BufferedWriter(fw);
                bw.write(getCnic());
                bw.write(",");
                bw.write(this.nameCandidate);
                bw.write(",");
                bw.write(getProvince());
                bw.write(",");
                bw.write(getCity());
                bw.write(",");
                bw.write(CanParty);
                bw.newLine();
                    break;
                }
                }
                }
            }
                System.out.println("your vote has been saved successfully");
        }catch(FileNotFoundException  yy)
        {
            yy.printStackTrace();
        }
        catch(IOException hh)
        {
            hh.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException jj)
        {
            jj.printStackTrace();
        }
        catch(Exception bb)
        {
            bb.printStackTrace();
        }
        finally
        {
            try{
                if(BR!=null)
                {
                    BR.close();
                }
                if(FR!=null)
                {
                    FR.close();
                }
                if(bw!=null)
                {
                    bw.close();
                }
                if(fw!=null)
                {
                    fw.close();
                }
            }catch(FileNotFoundException yy)
                {
                yy.printStackTrace();
                }
            catch(IOException HH)
            {
                HH.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException hh)
            {
                hh.printStackTrace();
            }
            catch(Exception nn)
            {
                nn.printStackTrace();
            }
        }
    }
    
    public void CasteVote()
    {
        Scanner enter=new Scanner(System.in);
                System.out.println("enter name of candidate");
                this.nameCandidate=enter.nextLine();
                boolean cvc=this.CastedValidCandidate();
                if(cvc==true)
                {
                    this.StoreVote();
                }
                else
                {
                    System.out.println("invalid candidate can't store your vote");
                }
    }  
    
    public boolean CastedValidCandidate()
    {
        FileReader FR=null;
        BufferedReader BR=null;
        try
        {
            FR=new FileReader("Candidate File.txt");
            BR=new BufferedReader(FR);
            String line;
            while((line=BR.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length>=5){
                String CanCNIC=parts[0].trim();
                String CanNAME=parts[1].trim();
                String CanPROVNCE=parts[2].trim();
                String CanCity=parts[3].trim();
                String CanParty=parts[4].trim();
                if(CanCity.equals(getCity())){
                if(CanNAME.equals(this.nameCandidate))
                {
                    this.ValidCandidate=true;
                    break;
                }
                else
                {
                    this.ValidCandidate=false;
                }
                }
                }
            }
        }catch(FileNotFoundException gh){
            gh.printStackTrace();
        }
        catch(IOException bb)
        {
            bb.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException ff)
        {
            ff.printStackTrace();
        }
        catch(Exception gg)
        {
            gg.printStackTrace();
        }
        finally{
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
            }catch(Exception jj){
                jj.printStackTrace();
            }
        }
        if(ValidCandidate==true)
        {
            return ValidCandidate;
        }
        else
        {
            return ValidCandidate;
        }
    }
    
     public boolean alreadyCasted()
    {//used CastedVote
        BufferedReader br=null;
        FileReader fr=null;
        BufferedReader Br=null;
        FileReader Fr=null;
        try{
        fr=new FileReader("Vote Stored.txt");
        br=new BufferedReader(fr);
        String line;
        this.CastedVote=true;
        while((line=br.readLine())!=null)
        {
            String[] parts=line.split(",");
            if(parts.length>=5){
            String votcnc=parts[0].trim();
            String votcan=parts[1].trim();
            String votprovince=parts[2].trim();
            String votCity=parts[3].trim();
            String canPARTY=parts[4].trim();
            if(votcnc.equals(getCnic()))
            {
                System.out.println("vote already casted to: "+votcan);
                System.out.println("candidate party is: "+canPARTY);
                this.CastedVote=false; 
                break;
            }
            }
        }
        }catch(FileNotFoundException ee)
        {
            ee.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException ff)
        {
            ff.printStackTrace();
        }
        catch(IOException jj)
        {
            jj.printStackTrace();
        }
        catch(Exception jj)
        {
            jj.printStackTrace();
        }
        finally
        {
            try{
                if(br!=null)
                {
                    br.close();
                }
                if(fr!=null)
                {
                    fr.close();
                }
            }catch(Exception yy)
                {
                yy.printStackTrace();
                }
        }
        return this.CastedVote;
    }
     
      public void fetchCITY()
    {
        FileReader FR=null;
        BufferedReader BR=null;
        try
        {
            FR=new FileReader("Registeration.txt");
            BR=new BufferedReader(FR);
            String line;
            while((line=BR.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length>=4){
                String voterCNIC=parts[0].trim();
               //     System.out.println(voterCNIC);
                String voterNAME=parts[1].trim();
                String voterPROVINCE=parts[2].trim();
                String voterCITY=parts[3].trim();
                System.out.println("read");
                if(voterCNIC.equals(getCnic()))
                {
                   // System.out.println(voterCNIC);
                    setCity(voterCITY);
                    setProvince(voterPROVINCE);
                }
                else
                {
                    System.out.println("cnic not found in registeration file");
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
    }
      
    public boolean verifyLogin(){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting\\Password.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length>=2){
                String pass=parts[0].trim();
                String votcnic=parts[1].trim();
               
                if(pass.equals(getPassword())&&votcnic.equals(getCnic()))
                        {
                            this.validPassword=1;
                            break;
                        }
                else
                {
                     this.validPassword=0;
                }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e){e.printStackTrace();}
        catch(IOException nn)
        {
            nn.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException gg)
        {
            gg.printStackTrace();
        }
        catch(Exception aa)
        {
            aa.printStackTrace();
        }
        if(this.validPassword==1)
        {
            System.out.println("verified");
            return true;
        }
        else 
        {
            System.out.println("invalid info");
            return false;
        }
    }
    
    public void InputVoter() { //used alreadyRegistered
        Scanner sc=new Scanner(System.in);
                try {
                    System.out.print("Enter CNIC: ");
                    String VOTERCNIC=sc.next();
                    if(VOTERCNIC.length()==3)
                    {
                        if(this.isThreeDigitInteger(VOTERCNIC)){
                        setCnic(VOTERCNIC);}
                        else
                        {
                            System.out.println("Enter only 3 digits integer");
                            this.InputVoter();
                        }
                    }
                    else
                    {
                        System.out.println("please enter 3 digits cnic only");
                        this.InputVoter();
                    }
                         
                     
                    
                   
         BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting\\Registeration.txt"));
         String line;
         while((line=reader.readLine())!=null)
         {
         String[] parts=line.split(",");
         String cnc=parts[0].trim();
         if(cnc.equals(getCnic()))
         {
             this.setAlreadyRegistered(0);
             break;
         }
         else
         {
             this.setAlreadyRegistered(1);
         }
         }
         if(this.getAlreadyRegistered()==1)
         {
             BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting\\Registeration.txt", true));
              System.out.print("Enter Name: ");
              String VOTERname=sc.nextLine();
              VOTERname=sc.nextLine();
              if(this.isValidAlphabetic(VOTERname))
              {
                  setName(VOTERname);
              }
              else
              {
                  System.out.println("enter only ALPHABETS(A-Z)");
                  this.InputVoter();
              }
              
             
        System.out.print("Enter Province: ");
        String VOTERprovince=sc.nextLine();
        if(this.isValidAlphabetic(VOTERprovince))
        {
            setProvince(VOTERprovince);
        }
        else
        {
            System.out.println("enter only ALPHABETS(A-Z)");
                  this.InputVoter();
        }
               
            
           
             
             System.out.println("Enter City");
             String VOTERcity=enter.nextLine();
             VOTERcity=enter.nextLine();
             if(this.isValidAlphabetic(VOTERcity))
             {
              setCity(VOTERcity);   
             }
             else
             {
                 System.out.println("enter only ALPHABETS(A-Z)");
                  this.InputVoter();
             }
             //cit=sc.nextLine();
             
        writer.close();
        reader.close();
         }
         else 
         {
             System.out.println("can't register again");
         }
                }catch(Exception ee){
                    ee.printStackTrace();
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
    
    public boolean Verify() {
        //used VerifiedVoter
        FileWriter FW=null;
        BufferedWriter BW=null;
        FileReader fw=null;
        BufferedReader reader=null;
        try{
            fw=new FileReader("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting\\NADRA.txt");
            reader=new BufferedReader(fw);
            String line;
            while((line= reader.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length>=4){
                    String fileCnic=parts[0].trim();
                    String fileName=parts[1].trim();
                    String fileProvince=parts[2].trim();
                    String fileCity=parts[3].trim();
                   // System.out.println("read");
                    //System.out.println(getName());
                    if(fileCnic.equals(getCnic()) && fileName.equals(getName()) && fileProvince.equals(getProvince()) && fileCity.equals(getCity())){
                        this.VerifiedVoter=0;
                        FW=new FileWriter("Registeration.txt",true);
                        BW=new BufferedWriter(FW);
                        BW.write(getCnic());
                        BW.write(",");
                        BW.write(getName());
                        BW.write(",");
                        BW.write(getProvince());
                        BW.write(",");
                        BW.write(getCity());
                        BW.newLine();
                        break;
                    }
                    else{
                        this.VerifiedVoter=1;
                    }
                }
            }
            reader.close();
        }catch (Exception e){}
        finally
        {
            try
            {
                if(BW!=null)
                {
                    BW.close();
                }
                if(reader!=null)
                {
                    reader.close();
                }
                if(FW!=null)
                {
                    FW.close();
                }
                if(fw!=null)
                {
                    fw.close();
                }
            }catch(Exception gh)
            {
                gh.printStackTrace();
            }
        }
        if(this.VerifiedVoter==0){
            return true;
        }
        else{
            return false;
        }
    }
    public void SetPassword() {
         Scanner sc = new Scanner(System.in);
        System.out.print("Set Password: ");
        setPassword(sc.nextLine());
    try(BufferedWriter writer=new BufferedWriter(new FileWriter("Password.txt",true))){
            writer.write(getPassword());
            writer.write(",");
            writer.write(getCnic());
            writer.newLine(); // Add a new line for each password
            System.out.println("Password Has Been Saved Successfully...");
          writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    catch(IOException ff)
    {
        ff.printStackTrace();
    }
    catch(ArrayIndexOutOfBoundsException ss)
    {
        ss.printStackTrace();
    }
    catch(Exception gg)
    {
        gg.printStackTrace();
    }
    }

    /**
     * @return the alreadyRegistered
     */
    public int getAlreadyRegistered() {
        return alreadyRegistered;
    }

    
    public void setAlreadyRegistered(int alreadyRegistered) {
        this.alreadyRegistered = alreadyRegistered;
    }
}

    
    

