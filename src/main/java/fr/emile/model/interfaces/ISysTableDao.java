package fr.emile.model.interfaces;

import java.util.List;
import java.util.Map;

import fr.emile.entity.SysTable;

public interface ISysTableDao {
	
	SysTable create(SysTable sysTable) throws Exception;
	SysTable read(int id) throws Exception;
	SysTable readByFunctionCode(int functionCode) throws Exception;
	int update(SysTable sysTable)throws Exception;
	int delete (int id)throws Exception;
}
