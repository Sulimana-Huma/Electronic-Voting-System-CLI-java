/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.evoting;

/**
 *
 * @author thinkpad
 */
public interface VoterInterface {
    void InputVoter();
    void CasteVote();
    boolean verifyLogin();
    void StoreVote();
    boolean alreadyCasted();
    
}
