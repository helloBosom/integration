package fun.peri.utils.jpacustomquery;

public class Condition {

	private String name;

	private String sopt;

	private String value;
	
	public Condition(String name, String sopt, String value) {
		super();
		this.name = name;
		this.sopt = sopt;
		this.value = value;
	}

	public SimpleExpression getExpresssion() {
		if ("eq".equals(this.sopt)) {
            return Restrictions.eq(this.name,this.value,true);
		}
		if ("ne".equals(this.sopt)) {
			return Restrictions.ne(this.name,this.value,true);
		}
		if ("cn".equals(this.sopt)) {
			return Restrictions.like(this.name,this.value,true);
		}
		if ("lt".equals(this.sopt)) {
			return Restrictions.lt(this.name,this.value,true);
		}
		return Restrictions.eq(this.name,this.value,true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSopt() {
		return sopt;
	}

	public void setSopt(String sopt) {
		this.sopt = sopt;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
