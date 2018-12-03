package song.song121321.bean.dto;

public class BudgetDto extends BaseModelDto {
	private static final long serialVersionUID = 1L;

	private double je;
	private double useJe;
	private double leftje;
	private int consumeNumber;
	private long year;
	private long month;
	private long status;

	public BudgetDto() {
	}

	public double getUseJe() {
		return useJe;
	}

	public void setUseJe(double useJe) {
		this.useJe = useJe;
	}

	public int getConsumeNumber() {
		return consumeNumber;
	}

	public void setConsumeNumber(int consumeNumber) {
		this.consumeNumber = consumeNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getJe() {
		return je;
	}

	public void setJe(double je) {
		this.je = je;
	}

	public double getLeftje() {
		return leftje;
	}

	public void setLeftje(double leftje) {
		this.leftje = leftje;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}
