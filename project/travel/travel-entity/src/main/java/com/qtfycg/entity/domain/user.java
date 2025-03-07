package com.qtfycg.entity.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class user implements Serializable {


    private Integer id;
    private String name;
    private String password;
    private String tel;
    private String email;


    private void setId(Integer id){
    this.id = id;
    }

    private void setName(String name){
    this.name = name;
    }

    private void setPassword(String password){
    this.password = password;
    }

    private void setTel(String tel){
    this.tel = tel;
    }

    private void setEmail(String email){
    this.email = email;
    }

    private Integer getId(){
    return this.id;
    }

    private String getName(){
    return this.name;
    }

    private String getPassword(){
    return this.password;
    }

    private String getTel(){
    return this.tel;
    }

    private String getEmail(){
    return this.email;
    }

}
