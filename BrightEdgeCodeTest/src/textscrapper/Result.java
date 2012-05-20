package textscrapper;

import java.util.ArrayList;

class Result {
	private ArrayList<ResultInfo> results;
	protected Result()
	{
		this.results = new ArrayList<ResultInfo>();
	}
	/**
	 * @return the results
	 */
	protected ArrayList<ResultInfo> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	protected void setResults(ArrayList<ResultInfo> results) {
		this.results = results;
	}
	
	protected void addResults(ResultInfo result)
	{
		this.results.add(result);
	}
}
