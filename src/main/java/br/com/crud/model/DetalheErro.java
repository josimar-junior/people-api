package br.com.crud.model;

public class DetalheErro {

	private String title;
	private Long status;
	private Long timestamp;

	public DetalheErro(String title, Long status, Long timestamp) {
		this.title = title;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public Long getStatus() {
		return status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
