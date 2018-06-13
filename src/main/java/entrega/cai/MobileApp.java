package entrega.cai;

import java.util.List;

import org.springframework.data.annotation.Id;

public class MobileApp {
	@Id
	private String id;
	private String name;
	private List<String> classification;
	private String description;
	private String platform;

	public MobileApp(String name, List<String> classification, String description,
			String platform) {
		super();
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.platform = platform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getClassification() {
		return classification;
	}

	public void setClassification(List<String> classification) {
		this.classification = classification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String toString() {
		return getName() + " - " + getClassification();
	}
}