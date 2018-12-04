package song.song121321.bean.dto;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class BaseModelDto extends LitePalSupport implements Serializable {

	private static final long serialVersionUID = -7674269980281525320L;
	@Column(unique = true)
	private long id;

	private String descp;

	private String ctime;

	private String mtime;

	private double n1;

	private double n2;

	private String c1;

	private String c2;

	private String d1;

	private String d2;

	private String detail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public double getN1() {
		return n1;
	}

	public void setN1(double n1) {
		this.n1 = n1;
	}

	public double getN2() {
		return n2;
	}

	public void setN2(double n2) {
		this.n2 = n2;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getD1() {
		return d1;
	}

	public void setD1(String d1) {
		this.d1 = d1;
	}

	public String getD2() {
		return d2;
	}

	public void setD2(String d2) {
		this.d2 = d2;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BaseModelDto(){}
	
	public BaseModelDto(long id, String descp, String ctime, String mtime,
			double n1, double n2, String c1, String c2, String d1, String d2,
			String detail) {
		super();
		this.id = id;
		this.descp = descp;
		this.ctime = ctime;
		this.mtime = mtime;
		this.n1 = n1;
		this.n2 = n2;
		this.c1 = c1;
		this.c2 = c2;
		this.d1 = d1;
		this.d2 = d2;
		this.detail = detail;
	}

	public BaseModelDto(String descp, String ctime, String mtime, double n1,
			double n2, String c1, String c2, String d1, String d2, String detail) {
		super();
		this.descp = descp;
		this.ctime = ctime;
		this.mtime = mtime;
		this.n1 = n1;
		this.n2 = n2;
		this.c1 = c1;
		this.c2 = c2;
		this.d1 = d1;
		this.d2 = d2;
		this.detail = detail;
	}

	
	
	
}
