package br.edu.ifsp.dao;

import java.util.List;

public interface IDAO<E> {
	
	public boolean insert(E object) throws Exception;
	
	public boolean update(E object) throws Exception;
	
	public boolean delete(E object) throws Exception;
	
	public E search(int objectId) throws Exception;
	
	public List<E> list() throws Exception;	
	
}
