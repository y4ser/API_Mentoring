package pojos.pet_store;

public class TagsItem{
	private String name;
	private Integer id;

	public String getName(){
		return name;
	}

	public Integer getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"TagsItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
