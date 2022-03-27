package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
/**
 * Clase per gestionar la persistència de les dades de les persones
 * @author manuel
 *
 */
public class ProductsDAO {

	private TreeMap<Integer, Products> products = new TreeMap<Integer,Products>();

	public boolean save(Products product){
		products.put(product.getIdProduct(), product);
		return true;
	}

	public boolean delete(Integer id){

		if (products.containsKey(id)){
			products.remove(id);
			return true;
		}

		return false;
	}

	public Products find(Integer id){

		if (id == null || id == 0){
			return null;
		}

		return products.get(id);
	}

	public void saveAll(){

		try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("products.dat"))) {
			oo.writeObject(products);
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero");
		}

	}

	@SuppressWarnings("unchecked")
	public void openAll(){

		File file = new File("products.dat");
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
				products = (TreeMap<Integer, Products>) ois.readObject();
			} catch (Exception e) {
				System.out.println("Error leyendo fichero");
			}
		}
	}

	public void showAll(){
		
		for (Products products : products.values()) {
			products.toString();
		    System.out.println("-------------------");
		}
	}
	
	public TreeMap<Integer, Products> getMap() {
        return products;
    }
}