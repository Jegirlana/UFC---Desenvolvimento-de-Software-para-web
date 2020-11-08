package dao;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import model.User;
 
public class UserDAO {
 
    private static final Map<Integer, User> userMap = new HashMap<Integer, User>();
    private static int i = 5;
 
    static {
        initUsers();
    }
 
    private static void initUsers() {
        User user1 = new User(1, "Maria", "supervisor","15/06/1999","10/11/2012");
        User user2 = new User(2, "João", "diretor","17/10/1995","12/03/2018");
        User user3 = new User(3, "Marcos", "estagiario","08/02/2000","14/06/2006");
        User user4 = new User(4, "Beatriz", "estagiario","08/02/2000","14/06/2006");
        
        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);
        userMap.put(user4.getId(), user4);
    }
 
    public static User getUser(int id) {
        return userMap.get(id);
    }
 
    public static User getUserBylogin(String nome) {
        List<User> list = getAllUsers();
 
        for (User user : list) {
            if (user.getNome().equals(nome)) {
                return user;
            }
        }
 
        return null;
    }
    
    public static List <User> getUserByCargo(String cargo) {
        List<User> list = getAllUsers();
    	List<User> list2 = null;
    	
        for (User user : list) {
            if (user.getCargo().equals(cargo)) {
            	if(list2 == null) {
            		list2 = new ArrayList<User>();
            	}
            	list2.add(user);
            }
        }
        	return list2;
    }
    
    public static List <User> getUserByQuantidade(int quantidade) {
        List<User> list = getAllUsers();
    	List<User> list2 = null;
    	int i=0;
        while ( i<quantidade) {
           
            	if(list2 == null) {
            		list2 = new ArrayList<User>();
            	}
            	list2.add(list.get(i));
            	i++;
        }
        	return list2;
    }
     
    public static User addUser(String nome, String cargo, String datanascimento, String dataentrada){
        User user = new User(i, nome, cargo,datanascimento,dataentrada);
        userMap.put(user.getId(), user);
        i++;
        return user;
    }
 
    public static User updateUser(int id, String nome, String cargo, String datanascimento,String dataentrada) {
        User user = new User(id, nome, cargo,datanascimento,dataentrada);
        userMap.put(user.getId(), user);
        return user;
    }
 
    public static void deleteUser(int id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
        }
    }
 
    public static List<User> getAllUsers() {
        return new ArrayList<User>(userMap.values());
    }
}