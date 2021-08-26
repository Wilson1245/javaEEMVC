/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.forms;

import lombok.Data;
import com.example.eemvc2.pojo.Users;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author User Reference:
 * https://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html_single/#table-spec-constraints
 */
@Data
public class UsersForm {

    @NotBlank
    @Length(max = 20)
    private String name;
    @NotBlank
    @Size(min = 8, max = 20)
    //8 ~ 20個字，必須有1個字母以及1個數字
    @Pattern(regexp = "^(?=.+[A-Za-z])(?=.+\\d)[A-Za-z\\d]{8,20}$", message = "必須有1個字母以及1個數字")
    private String password;
    @NotBlank
    @Size(min = 8, max = 20)
    private String confirmPassword;
    @NotBlank
    @Email
    private String email;

    /**
     * 檢查密碼與認密碼是否相符
     *
     * @return true相符,false不相符
     */
    public boolean matchPassword() {
        return password.equals(confirmPassword);
    }

    public Users convertToUsers() {
        Users users = new UsersFormConvert().convertFor(this);
        return users;
    }

    /**
     *
     */
    private static class UsersFormConvert implements IFormConvert<UsersForm, Users> {

        @Override
        public Users convertFor(UsersForm s) {
            Users users = new Users();
            BeanUtils.copyProperties(s, users);
            return users;
        }
    }
}
