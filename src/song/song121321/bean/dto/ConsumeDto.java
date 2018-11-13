/**
 * 
 */
package song.song121321.bean.dto;

/**
 * @author Ryan
 * 
 */
public class ConsumeDto extends BaseModelDto {
	private static final long serialVersionUID = 1L;
	private BankDto bank;
	private long bank_id;
	private String bank_descp;

	private long budget_id;
	private String budget_descp;


	private long account_id;
	private String account_nickname;

	private long currency_id;
	private String currency_icontext;
	private String currency_icon;

	private BudgetDto budget;
	private AccountListDto account;
	private CurrencyDto currency;
	private ConsumeTypeDto consumetype;
	private double je;
	private long status;

	public ConsumeDto() {
	}

	public ConsumeTypeDto getConsumetype() {
		return consumetype;
	}

	public void setConsumetype(ConsumeTypeDto consumetype) {
		this.consumetype = consumetype;
	}

	public BankDto getBank() {
		return bank;
	}

	public void setBank(BankDto bank) {
		this.bank = bank;
	}

	public BudgetDto getBudget() {
		return budget;
	}

	public void setBudget(BudgetDto budget) {
		this.budget = budget;
	}

	public AccountListDto getAccount() {
		return account;
	}

	public void setAccount(AccountListDto account) {
		this.account = account;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getBank_id() {
		return bank_id;
	}

	public void setBank_id(long bank_id) {
		this.bank_id = bank_id;
	}

	public String getBank_descp() {
		return bank_descp;
	}

	public void setBank_descp(String bank_descp) {
		this.bank_descp = bank_descp;
	}

	public long getBudget_id() {
		return budget_id;
	}

	public void setBudget_id(long budget_id) {
		this.budget_id = budget_id;
	}

	public String getBudget_descp() {
		return budget_descp;
	}

	public void setBudget_descp(String budget_descp) {
		this.budget_descp = budget_descp;
	}


	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public String getAccount_nickname() {
		return account_nickname;
	}

	public void setAccount_nickname(String account_nickname) {
		this.account_nickname = account_nickname;
	}

	public long getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(long currency_id) {
		this.currency_id = currency_id;
	}

	public String getCurrency_icontext() {
		return currency_icontext;
	}

	public void setCurrency_icontext(String currency_icontext) {
		this.currency_icontext = currency_icontext;
	}

	public String getCurrency_icon() {
		return currency_icon;
	}

	public void setCurrency_icon(String currency_icon) {
		this.currency_icon = currency_icon;
	}

}
