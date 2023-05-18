package fr.emile.model.interfaces;

import java.util.List;
import java.util.Map;

import fr.emile.entity.User;

public interface IUserDao {
	
	User create(User user) throws Exception;
	User read(int id) throws Exception;
	User read(String string) throws Exception;
	List<User>  readByCity(String email) throws Exception;
	Map<String,List<User>> readByCity() throws Exception;
	List<User> get() throws Exception;
	int update(User user)throws Exception;
	int delete (int id,boolean isDeleted)throws Exception;
	int delete (User user,boolean isDeleted)throws Exception;
	int delete (int id)throws Exception;
	int delete (User user)throws Exception;
}
