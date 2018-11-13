package song.song121321.bean.dto;

public class ConsumeTypeDto extends BaseModelDto {
	private static final long serialVersionUID = 1L;
	private String icon;
	private long muser;

	public ConsumeTypeDto() {
	}
	
	public ConsumeTypeDto(BaseModelDto base) {
		this.setId(base.getId());
		this.setC1(base.getC1());
		this.setC2(base.getC2());
		this.setD1(base.getD1());
		this.setD2(base.getD2());
		this.setN1(base.getN1());
		this.setN2(base.getN2());
		this.setCtime(base.getCtime());
		this.setMtime(base.getMtime());
		this.setDescp(base.getDescp());
		this.setDetail(base.getDetail());
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public long getMuser() {
		return muser;
	}

	public void setMuser(long muser) {
		this.muser = muser;
	}
}
