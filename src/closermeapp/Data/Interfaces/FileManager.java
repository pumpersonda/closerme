/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Data.Interfaces;


import closermeapp.Bussiness.Entities.Member;

public interface FileManager {
    void saveMemberIntoFile(Member member);
}
