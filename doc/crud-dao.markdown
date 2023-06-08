# Crud Dao
![Schéma de principe](./images/CrudDao-Poo.png) 
# Pourquoi
1. nombre impotante de DAO
1. mutualisation du code
1. moins de code = moins de bug = moins de maintenance

# Comment ca marche

## classe  CrudDao
1. Cet object manipule les instances de la class : ClassDao
1. les prototype des méthodes disponibles sont dans l'interface ICrudDao
1. Pour utiliser la class CrudDao, il faut hériter de la classe : ClassDao.


## Classe : ClassDao

1. c'est une classe abstraite qu'il faut hériter si l'on veux utiliser CrudDao

## interface : ICrudDao

1. cette interface expose les méthodes utilisable de CrudDao


## exemple d'utilisation 

1. héritage de ClassDao  (voir exemple dans la class `User.java`)

l'héritage nécessite l'implémentation des 2 méthodes abstraites.  
- public void postRead();  appellé après chaque lecture
- public void preWrite();  appellé avant toutes écritures



	```java 
	public class User extends ClassDao   {   
	.... TODO here  
	}
	```

 

1. Utilisation de CrudDao  (dans un main())


```java 
public static void main(String[] args) {

	ICrudDao costumerDao = new CrudDao();
	try {
		costumer = (Costumer) 
		costumerDao.read(userId,costumer.getClass());
	} catch (Exception e) {
	
		e.printStackTrace();
	}finally{
		costumerDao.close()
	}

}
```





