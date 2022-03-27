package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class TreballadorDAO {
	private TreeMap<Integer, Treballador> treballador = new TreeMap<Integer,Treballador>();
	
	public boolean save(Treballador t){
		treballador.put(t.getIdTreballador(), t);
		return true;
	}

	public boolean delete(Integer id){

		if (treballador.containsKey(id)){
			treballador.remove(id);
			return true;
		}

		return false;
	}
	
	public Treballador find(Integer id){

		if (id == null || id == 0){
			return null;
		}

		return treballador.get(id);
	}
	
	public void saveAll(){

		try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("treballador.dat"))) {
			oo.writeObject(treballador);
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero");
		}

	}

	@SuppressWarnings("unchecked")
	public void openAll(){

		File file = new File("treballador.dat");
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
				treballador = (TreeMap<Integer, Treballador>) ois.readObject();
			} catch (Exception e) {
				System.out.println("Error leyendo fichero");
			}
		}
	}

	public void showAll(){
		
		for (Treballador treballador : treballador.values()) {
			treballador.toString();
		    System.out.println("-------------------");
		}
	}
	
	public TreeMap<Integer, Treballador> getMap() {
        return treballador;
    }

}
