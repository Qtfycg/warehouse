package com.qtfycg.back.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class admin implements Serializable {


    private Integer id;

    private String name;

    private String password;

    private String tel;

    private String description;

    /**
    * 
    */
    private void setId(Integer id){
    this.id = id;
    }

    /**
    * 
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 
    */
    private void setPassword(String password){
    this.password = password;
    }

    /**
    * 
    */
    private void setTel(String tel){
    this.tel = tel;
    }

    /**
    * 
    */
    private void setDescription(String description){
    this.description = description;
    }


    /**
    * 
    */
    private Integer getId(){
    return this.id;
    }

    /**
    * 
    */
    private String getName(){
    return this.name;
    }

    /**
    * 
    */
    private String getPassword(){
    return this.password;
    }

    /**
    * 
    */
    private String getTel(){
    return this.tel;
    }

    /**
    * 
    */
    private String getDescription(){
    return this.description;
    }

}
