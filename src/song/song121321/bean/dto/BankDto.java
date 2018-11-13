package song.song121321.bean.dto;

public class BankDto extends BaseModelDto {
	private static final long serialVersionUID = 1L;
	private AccountListDto account;
	private double je;
	private long accounttype;
	private long status;

	public BankDto() {
	}

	public BankDto(BaseModelDto base) {
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

	public AccountListDto getAccount() {
		return account;
	}



	public void setAccount(AccountListDto account) {
		this.account = account;
	}



	public double getJe() {
		return je;
	}

	public void setJe(double je) {
		this.je = je;
	}

	public long getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(long accounttype) {
		this.accounttype = accounttype;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}
