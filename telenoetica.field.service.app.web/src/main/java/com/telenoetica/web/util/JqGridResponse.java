/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.util;

import java.io.Serializable;
import java.util.List;

import com.telenoetica.jpa.entities.BaseEntity;

/**
 * A POJO representing a jQgrid's jsonReader property.
 *
 * @param <T> the generic type
 * @see <a
 * href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON
 * Data</a>
 */
public class JqGridResponse<T extends Serializable> {

	/** Current page. */
	private String page;

	/** Total pages. */
	private String total;

	/** Total number of records. */
	private String records;

	/** Contains the actual data. */
	private List<Object> rows;

	/**
	 * Instantiates a new jq grid response.
	 */
	public JqGridResponse() {
	}

	/**
	 * Instantiates a new jq grid response.
	 *
	 * @param page the page
	 * @param total the total
	 * @param records the records
	 * @param rows the rows
	 */
	public JqGridResponse(String page, String total, String records,
			List<Object> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	public String getRecords() {
		return records;
	}

	/**
	 * Sets the records.
	 *
	 * @param records the new records
	 */
	public void setRecords(String records) {
		this.records = records;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public List<Object> getRows() {
		return rows;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JqgridResponse [page=" + page + ", total=" + total
				+ ", records=" + records + "]";
	}
}
