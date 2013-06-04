package com.telenoetica.web.util;

import java.io.Serializable;
import java.util.List;

import com.telenoetica.jpa.entities.BaseEntity;

/**
 * A POJO representing a jQgrid's jsonReader property. 
 * @see <a href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON Data</a>
 */
public class JqgridResponse<T extends Serializable> {

	/**
	 * Current page
	 */
	private String page;
	
	/**
	 * Total pages
	 */
	private String total;
	
	/**
	 * Total number of records
	 */
	private String records;
	
	/**
	 * Contains the actual data
	 */
	private List<BaseEntity> rows;

	public JqgridResponse() {}
	
	public JqgridResponse(String page, String total, String records,
			List<BaseEntity> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	public List<BaseEntity> getRows() {
		return rows;
	}
	public void setRows(List<BaseEntity> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "JqgridResponse [page=" + page + ", total=" + total
				+ ", records=" + records + "]";
	}
}
