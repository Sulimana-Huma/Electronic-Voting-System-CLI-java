/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.evoting;

import java.util.Scanner;
public class Evoting {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean loop=true;
        VOTER V=new VOTER();
        Admin A=new Admin();
        Display display = new Display();
        CandidateSide C=new CandidateSide();
        while(loop) {
            System.out.println("Press 1 if you are VOTER");
            System.out.println("Press 2 if you are Candidate");
            System.out.println("Press 3 if you are ADMIN");
            System.out.println("Press 4 to Display Reports");
            System.out.println("Press 5 to Exit.");
            System.out.println();
            System.out.print("Enter Key: ");
            char c=sc.next().charAt(0);
            switch (c) {
                case '1': {
                    V.VoterSide();
                    break;
                }
                case '2':{
                    boolean so=C.SelectOption();
                    if(so==true){
                    C.InputCandidate();
                    boolean vc=C.VerifyCandidate();
                    if(vc==true)
                    {
                        System.out.println("valid candidate");
                        C.StoreLoginInfo();
                    }
                    else
                    {
                        System.out.println("invalid data");
                    }
                    }
                    else
                    {
                        boolean log=C.LOGIN();
                        if(log==true)
                        {
                            C.TOTALvotes();
                        }
                        else
                        {
                            System.out.println("invalid login INFO");
                        }
                    }
                    break;
                }
                
                case '3':
                {
                    A.SelectOption();
                    break;
                }
                case '4':
                {
                    display.SelectOption();
                    break;
                }
                case '5':{
                    loop=false;
                    break;
                }
                default:{
                    System.out.println("Wrong Input!!!");
                }
            }
        }
    }
}
