/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author mahdi
 */
public interface Service<T> {
    public void insert(T o);
    public void delete(int id);
    public List<T> displayAll();
    public List<T> displayAllByDate();
    
    public T displayById(int id);
    
    public void update(T os);
    
}
