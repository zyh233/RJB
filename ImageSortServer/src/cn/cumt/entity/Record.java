package cn.cumt.entity;

public class Record {
	private int uid;
	private int id;
	private String name;
	private String tags;
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Record(int uid, int id, String name, String tags) {
		this.uid = uid;
		this.id = id;
		this.name = name;
		this.tags = tags;
	}


	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Record [name=" + name + ", tags=" + tags + "]";
	}
	
	
}
