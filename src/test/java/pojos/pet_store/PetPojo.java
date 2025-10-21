package pojos.pet_store;

import java.util.List;

public class PetPojo {
	private List<String> photoUrls;
	private String name;
	private Integer id;
	private Category category;
	private List<TagsItem> tags;
	private String status;

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public String getName(){
		return name;
	}

	public Integer getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"photoUrls = '" + photoUrls + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",tags = '" + tags + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}