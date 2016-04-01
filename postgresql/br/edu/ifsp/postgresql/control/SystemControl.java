package br.edu.ifsp.postgresql.control;

import br.edu.ifsp.postgresql.dao.MyImageDAO;

public class SystemControl {

	public static MyImageDAO myImageControl() {
		return new MyImageDAO();
	}

}
