/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Rohan
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel
{
    private UserAccount user;
    private UserStatus status;  
}
