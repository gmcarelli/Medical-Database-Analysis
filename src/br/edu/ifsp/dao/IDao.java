package br.edu.ifsp.dao;

import java.util.List;

public interface IDao {
	
	public boolean insert(Object object);
	
	public boolean update(Object object);
	
	public boolean delete(Object object);
	
	public Object search(int objectId);
	
	public List<Object> list();	
	
}
