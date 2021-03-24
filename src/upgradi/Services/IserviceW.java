/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Services;

import java.util.List;
import upgradi.Entities.Panier;

/**
 *
 * @author Fedy
 * @param <T>
 */
public interface IserviceW<T> {

    public void insert(Panier o);

    public void delete(int id);

    public void deleteAll();

    public List<Panier> displayAll(Panier p);

    public T displayById(int id);

}
