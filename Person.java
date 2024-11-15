import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;

	public Person() {
		name = null;
		age = 0;
	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}