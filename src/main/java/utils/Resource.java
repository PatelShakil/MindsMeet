/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author M.SHAKIL PATEL
 */
public class Resource<T> implements Serializable {
    
    private T obj;            // Corrected to use the generic type T
    private String message;
    private Boolean status;

    public Resource(){
        this.obj = null;
        this.message = "";
        this.status = false;
    }
    // Constructor to initialize the values
    public Resource(T obj, String message, Boolean status) {
        this.obj = obj;
        this.message = message;
        this.status = status;
    }

    // Getters for accessing private fields
    public T getObj() {
        return obj;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    // Setters for modifying the fields
    public void setObj(T obj) {
        this.obj = obj;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Resource{" + "obj="+obj.toString()  + ", message=" + message + ", status=" + status + '}';
    }
    
    
    
}

