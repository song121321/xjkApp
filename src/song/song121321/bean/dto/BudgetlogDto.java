package song.song121321.bean.dto;

public class BudgetlogDto {
	private long id;
	private double accountid;
	private String account_nickname;
	private String descp;
	private double je;
	private long status;
	private String ctime;
	private double leftje;
	private long budgetid;
	private long consumeid;

	public BudgetlogDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAccountid() {
		return accountid;
	}

	public void setAccountid(double accountid) {
		this.accountid = accountid;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public double getJe() {
		return je;
	}

	public void setJe(double je) {
		this.je = je;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public double getLeftje() {
		return leftje;
	}

	public void setLeftje(double leftje) {
		this.leftje = leftje;
	}

	public long getBudgetid() {
		return budgetid;
	}

	public void setBudgetid(long budgetid) {
		this.budgetid = budgetid;
	}

	public String getAccount_nickname() {
		return account_nickname;
	}

	public void setAccount_nickname(String account_nickname) {
		this.account_nickname = account_nickname;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public long getConsumeid() {
		return consumeid;
	}

	public void setConsumeid(long consumeid) {
		this.consumeid = consumeid;
	}

}
