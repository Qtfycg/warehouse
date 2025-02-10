package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName admin
*/
public class admin implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer id;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String name;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 6,message="编码长度不能超过6")
    @ApiModelProperty("")
    @Length(max= 6,message="编码长度不能超过6")
    private String password;
    /**
    * 
    */
    @Size(max= 11,message="编码长度不能超过11")
    @ApiModelProperty("")
    @Length(max= 11,message="编码长度不能超过11")
    private String tel;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
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
