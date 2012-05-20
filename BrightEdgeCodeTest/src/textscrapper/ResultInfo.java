/**
 * 
 */
package textscrapper;

/**
 * @author Rahul Jain = rjain2908 AT gmail DOT com
 *
 */
class ResultInfo {
	private String title;
	private String description;
	private String vendors;
	protected ResultInfo(String t, String des, String ven) {
		this.title = t;
		this.description = des;
		this.vendors = ven;
	}
	/**
	 * @return the title
	 */
	protected String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	protected void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	protected String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the vendors
	 */
	protected String getVendors() {
		return vendors;
	}
	/**
	 * @param vendors the vendors to set
	 */
	protected void setVendors(String vendors) {
		this.vendors = vendors;
	}
	
}
