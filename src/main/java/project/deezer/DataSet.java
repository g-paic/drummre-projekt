package project.deezer;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSet {
	private Data[] data;
	private int total;
	private String next;

	public Data[] getData() {
		return this.data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getNext() {
		return this.next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "data=" + Arrays.toString(this.data) + ", total=" + this.total + ", next=" + this.next;
	}
}
