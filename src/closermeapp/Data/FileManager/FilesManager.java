/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Data.FileManager;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Data.Interfaces.FileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FilesManager implements FileManager {


    @Override
    public void saveMemberIntoFile(Member member) {
        String memberFormatString =
                member.getName() + "|" +
                        member.getPhone() + "|" +
                        member.getAddress() + "|" +
                        member.getCellphone() + "|" +
                        member.getMembership().getType() + "|" +
                        member.getMembership().getDiscount() + "|";

        saveToFile(memberFormatString);

    }

    private void saveToFile(String data) {
        try {
            // PrintWriter output = new PrintWriter(new FileWriter("data.txt"),false);
            FileWriter outputFile = new FileWriter("data.txt", true);
            PrintWriter output = new PrintWriter(new BufferedWriter(outputFile));
            output.println(data);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(FilesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}   
