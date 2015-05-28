package xmlparser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Category {
	private String id;
	private String name;
	private Set<String> subCategory = new HashSet<String>();
	private String parentID;
	private List<String> parentIDs = new ArrayList<String>();
	private List<Vod> vods = new ArrayList<Vod>();
	private int totalSize = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<String> subCategory) {
		this.subCategory = subCategory;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public List<String> getParentIDs() {
		return parentIDs;
	}

	public void setParentIDs(List<String> parentIDs) {
		this.parentIDs = parentIDs;
	}

	public List<Vod> getVods() {
		return vods;
	}

	public void setVods(List<Vod> vods) {
		this.vods = vods;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void addTotalSize() {
		totalSize++;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", subCategory="
				+ subCategory + ", parentID=" + parentID + ", parentIDs="
				+ parentIDs + ", vods=" + vods + ", totalSize=" + totalSize
				+ "]";
	}

}
