package br.edu.ifsp.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import br.edu.ifsp.database.Database;

public abstract class DAO<E> {
	
	protected Database database;
	
	public abstract boolean insert(E object) throws Exception;
	
	public abstract boolean insert(List<E> list) throws Exception;
	
	public abstract boolean update(E object) throws Exception;
	
	public abstract boolean update(List<E> list) throws Exception;
	
	public abstract boolean delete(Integer id) throws Exception;
	
	public abstract boolean delete(List<Integer> id) throws Exception;
	
	public boolean delete(Integer[] ids) throws Exception {
		return delete(Arrays.asList(ids));
	}
	
	public abstract boolean deleteAll();

	public abstract E searchById(Integer id) throws Exception;
	
	public abstract List<E> search(Set<Integer> ids) throws Exception;
	
	public abstract List<E> listAll() throws Exception;
	
	public Database getDatabase() {
		return this.database;
	}
	
}
