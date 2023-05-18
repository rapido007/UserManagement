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
public class UserType 
{
    public final static int TYPE_CUSTOMER=1;
    public final static int TYPE_SUPPORT=2;
    public final static int TYPE_ADMIN=3;
    private int id;
    private String name;
    private String description;
}
