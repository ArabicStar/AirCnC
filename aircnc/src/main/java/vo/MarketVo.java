package vo;

import po.MarketPo;

/**
 *
 *
 * @author ParanoiaSun
 */
public class MarketVo {

	private String id;
	private String username;
	private String password;

	public MarketVo(){
		super();
	}

	public MarketVo(MarketPo marketPo){
		super();
		this.id = marketPo.getId();
		this.username = marketPo.getUsername();
		this.password = marketPo.getPassword();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}