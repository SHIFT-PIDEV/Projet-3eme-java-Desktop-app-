/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Services;
import java.util.List;

/**
 *
 * @author Fedy
 * @param <T>
 */

    
    public interface Iservice<T>{
    public void insert (T o);
    public void delete(int id);
    public void deleteAll();
    public List<T> displayAll();
    public T displayById(int id);
   // public boolean update(T os);
        
        
    }
