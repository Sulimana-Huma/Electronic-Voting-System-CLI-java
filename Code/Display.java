/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evoting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*public class Display{
        private static final String VOTE_STORED_FILE = "Vote Stored.txt";
    public void SelectOption()
    {
        boolean loop=true;
        while(true){
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 to see Winner");
        System.out.println("Press 2 to see votes of candidates");
        System.out.println("Press 3 to display vote by voter cnic ");
        System.out.println("Press 4 to see province wise votes");
        System.out.println("Press 5 to positions of candidates");
            System.out.println("Press 6 to exit");
        int ch=sc.nextInt();
        switch(ch)
        {
            case '1':
            {
                 displayWinner();
                 break;
            }
            case '2':
            {
                System.out.println("enter name of candidate");
                String name=sc.nextLine();
                displayVotesOfCandidate(name);
                break;
            }
            case '3':
            {
                displayVote("678");
                break;
            }
            case '4':
            {
                sortProvince();
                break;
            }
            case '5':
            {
                 sortCandidates();
                 break;
            }
            case '6':
            {
                loop=false;
                break;
            }
            default:
            {
                System.out.println("invalid data");
                break;
            }
            
        }
    }
    }
    public void displayWinner() {
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            String winner = null;
            int maxVotes = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { 
                    String candidateName = parts[1];
                    int candidateVotes = countVotes(candidateName);
                    if (candidateVotes > maxVotes) {
                        maxVotes = candidateVotes;
                        winner = candidateName;
                    }
                }
            }
            if (winner != null) {
                System.out.println("Winner: " + winner);
            } else {
                System.out.println("No votes found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    private int countVotes(String candidateName) {
        int voteCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(candidateName)) {
                    voteCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return voteCount;
    }
    public void displayVote(String voterCNIC) {
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(voterCNIC)) {
                    System.out.println("Vote Entry for CNIC " + voterCNIC + ": " + line);
                    return; // Exit after finding the first entry
                }
            }
            System.out.println("No vote found for CNIC " + voterCNIC);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public void displayVotesOfCandidate(String candidateName) {
        int voteCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(candidateName)) {
                    voteCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("Votes for candidate " + candidateName + ": " + voteCount);
    }
        public void sortProvince() {
        List<String> provinces = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { 
                    String province = parts[2];
                    int index = provinces.indexOf(province);
                    if (index == -1) {
                        provinces.add(province);
                        votes.add(1);
                    } else {
                        votes.set(index, votes.get(index) + 1);
                    }
                }
            }
            if (!provinces.isEmpty()) {
                for (int i = 0; i < provinces.size() - 1; i++) {
                    for (int j = i + 1; j < provinces.size(); j++) {
                        if (votes.get(j) > votes.get(i)) {
                            // Swap candidates and votes
                            Collections.swap(provinces, i, j);
                            Collections.swap(votes, i, j);
                        }
                    }
                }
                System.out.println("Candidates sorted by votes:");

                for (int i = 0; i < provinces.size(); i++) {
                    System.out.println(provinces.get(i) + ": " + votes.get(i) + " votes");
                }
            } else {
                System.out.println("No votes found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public void sortCandidates() {
        List<String> candidates = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { 
                    String candidateName = parts[1];
                    int index = candidates.indexOf(candidateName);

                    if (index == -1) {
                        candidates.add(candidateName);
                        votes.add(1);
                    } else {
                        votes.set(index, votes.get(index) + 1);
                    }
                }
            }
            if (!candidates.isEmpty()) {
                for (int i = 0; i < candidates.size() - 1; i++) {
                    for (int j = i + 1; j < candidates.size(); j++) {
                        if (votes.get(j) > votes.get(i)) {
                            Collections.swap(candidates, i, j);
                            Collections.swap(votes, i, j);
                        }
                    }
                }
                System.out.println("Candidates sorted by votes:");

                for (int i = 0; i < candidates.size(); i++) {
                    System.out.println(candidates.get(i) + ": " + votes.get(i) + " votes");
                }
            } else {
                System.out.println("No votes found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}*/



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Display{
        private static final String VOTE_STORED_FILE = "Vote Stored.txt";

    public void SelectOption()
    {
        boolean loop=true;
        while(true){
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 to see Winner");
        System.out.println("Press 2 to see votes of candidates");
        System.out.println("Press 3 to display vote by voter cnic ");
        System.out.println("Press 4 to see province wise votes");
        System.out.println("Press 5 to positions of candidates");
        System.out.println("Press 6 to exit");
        int ch=sc.nextInt();
        switch(ch)
        {
            case 1:
            { System.out.println("Winner is: ");
                 displayWinner();
                 break;
            }
            case 2:
            {Scanner s=new Scanner(System.in);
                System.out.println("enter name of candidate");
                String name=s.nextLine();
                displayVotesOfCandidate(name);
                break;
            }
            case 3:
            {   Scanner s=new Scanner(System.in);
                System.out.println("enter cnic of voter to see his vote");
                String voterCNIC=s.nextLine();
                displayVote(voterCNIC);
                break;
            }
            case 4:
            {
                sortProvince();
                break;
            }
            case 5:
            {
                 sortCandidates();
                 break;
            }
            case 6:
            {
                loop=false;
                break;
            }
            default:
            {

                System.out.println("invalid data");
                break;
            }
            
        }
    }
    }
    public void displayWinner() {
        
          BufferedReader reader;
         String path=System.getProperty("user.dir")+ "C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting1\\Vote Stored.txt";
        
         try 
        
         { 
           // String path=System.getProperty("user.dir")+ "\\EVS\\VoteStored.txt";
           // System.out.println(path);
            reader = new BufferedReader(new FileReader("C:\\Users\\thinkpad\\OneDrive\\Documents\\NetBeansProjects\\evoting1\\Vote Stored.txt"));
            System.out.println("After reading");
            String line;
            String winner = null;
            int maxVotes = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { // Ensure there is at least a candidate name in the entry
                    
                    String candidateName = parts[1].trim();

                    // Count votes for each candidate
                    int candidateVotes = countVotes(candidateName);

                    // Update the winner if the current candidate has more votes
                    if (candidateVotes > maxVotes) {
                        maxVotes = candidateVotes;
                        winner = candidateName;
                    }
                }
            }

            if (winner != null) {
                System.out.println("Winner: " + winner);
            } else {
                System.out.println("No votes found in the file.");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private int countVotes(String candidateName) {
        int voteCount = 0;
         BufferedReader reader;
         String path=System.getProperty("user.dir")+ "\\evoting\\Vote Stored.txt";
        try  {
              
             reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(candidateName)) {
                    voteCount++;
                }
            }
         reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        
           
        

        return voteCount;
    }

    public void displayVote(String voterCNIC) {
          BufferedReader reader;
         String path=System.getProperty("user.dir")+ "\\evoting\\Vote Stored.txt";
        try  {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(voterCNIC)) {
                    System.out.println("Vote Entry for CNIC " + voterCNIC + ": " + line);
                    return; // Exit after finding the first entry
                }
            }
            System.out.println("No vote found for CNIC " + voterCNIC);
        reader.close();
        
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
         
    }

    public void displayVotesOfCandidate(String candidateName) {
        int voteCount = 0;
         BufferedReader reader;
         String path=System.getProperty("user.dir")+ "\\evoting\\Vote Stored.txt";
        try{
             reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(candidateName)) {
                    voteCount++;
                }
            }
        reader.close();
        
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("Votes for candidate " + candidateName + ": " + voteCount);
    }

    /*public void sortCandidates() {
        Map<String, Integer> candidateVotes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_STORED_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // Ensure there is at least a candidate name in the entry
                    String candidateName = parts[2];
                    candidateVotes.put(candidateName, candidateVotes.getOrDefault(candidateName, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        if (!candidateVotes.isEmpty()) {
            System.out.println("Candidates sorted by votes:");

            candidateVotes.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " votes"));
        } else {
            System.out.println("No votes found in the file.");
        }
    }*/ 
        public void sortProvince() {
        List<String> provinces = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();
            BufferedReader reader;
         String path=System.getProperty("user.dir")+ "\\evoting\\Vote Stored.txt";
        try  {
            String line;
            reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { // Ensure there is at least a candidate name in the entry
                    String province = parts[2];

                    // Check if the candidate is already in the list
                    int index = provinces.indexOf(province);

                    if (index == -1) {
                        // Candidate is not in the list, add them
                        provinces.add(province);
                        votes.add(1);
                    } else {
                        // Candidate is in the list, update their vote count
                        votes.set(index, votes.get(index) + 1);
                    }
                }
            }

            if (!provinces.isEmpty()) {
                // Sort candidates based on votes
                for (int i = 0; i < provinces.size() - 1; i++) {
                    for (int j = i + 1; j < provinces.size(); j++) {
                        if (votes.get(j) > votes.get(i)) {
                            // Swap candidates and votes
                            Collections.swap(provinces, i, j);
                            Collections.swap(votes, i, j);
                        }
                    }
                }

                // Display sorted candidates
                System.out.println("Candidates sorted by votes:");

                for (int i = 0; i < provinces.size(); i++) {
                    System.out.println(provinces.get(i) + ": " + votes.get(i) + " votes");
                }
            } else {
                System.out.println("No votes found in the file.");
            }

        
         reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public void sortCandidates() {
        List<String> candidates = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();
        BufferedReader reader;
         String path=System.getProperty("user.dir")+ "\\evoting\\Vote Stored.txt";
        try  {
            String line;
          reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { // Ensure there is at least a candidate name in the entry
                    String candidateName = parts[1];

                    // Check if the candidate is already in the list
                    int index = candidates.indexOf(candidateName);

                    if (index == -1) {
                        // Candidate is not in the list, add them
                        candidates.add(candidateName);
                        votes.add(1);
                    } else {
                        // Candidate is in the list, update their vote count
                        votes.set(index, votes.get(index) + 1);
                    }
                }
            }

            if (!candidates.isEmpty()) {
                // Sort candidates based on votes
                for (int i = 0; i < candidates.size() - 1; i++) {
                    for (int j = i + 1; j < candidates.size(); j++) {
                        if (votes.get(j) > votes.get(i)) {
                            // Swap candidates and votes
                            Collections.swap(candidates, i, j);
                            Collections.swap(votes, i, j);
                        }
                    }
                }

                // Display sorted candidates
                System.out.println("Candidates sorted by votes:");

                for (int i = 0; i < candidates.size(); i++) {
                    System.out.println(candidates.get(i) + ": " + votes.get(i) + " votes");
                }
            } else {
                System.out.println("No votes found in the file.");
            }
             reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    } 
}

