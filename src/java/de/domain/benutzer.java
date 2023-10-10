package de.unidue.inf.is.domain;

public class benutzer {
	private int bid;
	private String name;
	private String email;
        private boolean isAdmin=false;
	
	public benutzer() {
		this.bid = 1;
		this.name = "jack";
		this.email = "jack@jack.com";
	}
	
	public benutzer(int bid, String name, String email) {
		this.bid = bid;
		this.name = name;
		this.email = email;
	}
	
	public benutzer(int id,String email) {
		this.email = email;
                this.bid=id;
	}
	
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
        public boolean getIsAdmin(){
            return this.isAdmin;
        }
        public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}

}
