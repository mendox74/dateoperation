package prototype.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DateOperation {
	
	/*
	 * ID */
    private int id;
    /*
     * 演算式ID*/
	@NotBlank
    private String operationId;
    /*
     * 演算式名前*/
	@NotBlank
    private String operationName;
    /*
     * 加減値（年）　*/
	@NotNull
    private int operationYear;
    /*
     * 加減値（月）　*/
	@NotNull
    private int operationMonth;
    /*
     * 加減値（日） */
	@NotNull
    private int operationDay;	
    /*
     * 計算結果 */
    private String result;
    /*
     * 基準日*/
    @Size()
    private String criteria;
    /*
     * 月末日取得判定*/
    private int monthEnd;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOperationId() {
        return operationId;
    }
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    public int getOperationYear() {
        return operationYear;
    }
    public void setOperationYear(int operationYear) {
        this.operationYear = operationYear;
    }
    public int getOperationMonth() {
        return operationMonth;
    }
    public void setOperationMonth(int operationMonth) {
        this.operationMonth = operationMonth;
    }
    public int getOperationDay() {
        return operationDay;
    }
    public void setOperationDay(int operationDay) {
        this.operationDay = operationDay;
    }
    public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public int getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(int monthEnd) {
		this.monthEnd = monthEnd;
	}
	@Override
    public String toString() {
        return "DateOperation [id=" + id + ", operationId=" + operationId + ", operationName=" + operationName + ", operationYear=" + operationYear + ", operationMonth=" + operationMonth + ", operationDay=" + operationDay + "]";
    }

}
