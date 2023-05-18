/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kp.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Rohan
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccount 
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int userTypeId;
    private String phone;
    private String email;
    private String password;
}
