package dao;

import java.util.ArrayList;

import entity.DataBaoCao;



public class BaoCaoDao {
	ArrayList<DataBaoCao> ds;
	public BaoCaoDao() {
		ds = new ArrayList<DataBaoCao>();
		
	}
	public boolean add(DataBaoCao bc) {
		
		ds.add(bc);
		return true;
	}
	public ArrayList<DataBaoCao> getAll(){
		return ds;
	}
	public int size() {
		
		return ds.size();
	}
	public DataBaoCao get(int i) {
		
		return ds.get(i);
	}
}
