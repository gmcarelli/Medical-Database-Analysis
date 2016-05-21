package br.edu.ifsp.dao;

import java.util.List;

public interface IDAO<E> {
	
	public boolean insert(E object);
	
	public boolean update(E object);
	
	public boolean delete(int objectId);
	
	public E search(int objectId) throws Exception;
	
	public List<E> list() throws Exception;	
	
}
