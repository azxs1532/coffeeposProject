package orange;

public class orderVO {
    String coffee;
	String menulist;
	int daliy;
	int daliymoney;
	int money;
	int idx;
	static String menu;
    static String hotice;
    static String ICE;
    static String SHOT;
    static String SIZE;
    static String takeOut;
    static int won;
    static int count;
    static int ea;
    static int ea2;
    static int sum;
    static int totalsum;
	public static int getTotalsum() {
		return totalsum;
	}
	public static void setTotalsum(int totalsum) {
		orderVO.totalsum = totalsum;
	}
	public static int getTotalea2() {
		return totalea2;
	}
	public static void setTotalea2(int totalea2) {
		orderVO.totalea2 = totalea2;
	}
	static int totalea2;
    
	public static int getSum() {
		return sum;
	}
	public static void setSum(int sum) {
		orderVO.sum = sum;
	}
	public static int getEa2() {
		return ea2;
	}
	public static void setEa2(int ea2) {
		orderVO.ea2 = ea2;
	}
	public String getCoffee() {
		return coffee;
	}
	public void setCoffee(String coffee) {
		this.coffee = coffee;
	}

	public int getDaliy() {
		return daliy;
	}
	public void setDaliy(int daliy) {
		this.daliy = daliy;
	}
	public int getDaliymoney() {
		return daliymoney;
	}
	public void setDaliymoney(int daliymoney) {
		this.daliymoney = daliymoney;
	}
	public String getMenulist() {
		return menulist;
	}
	public void setMenulist(String menulist) {
		this.menulist = menulist;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public static String getMenu() {
		return menu;
	}
	public static void setMenu(String menu) {
		orderVO.menu = menu;
	}
	public static String getHotice() {
		return hotice;
	}
	public static void setHotice(String hotice) {
		orderVO.hotice = hotice;
	}
	public static String getICE() {
		return ICE;
	}
	public static void setICE(String iCE) {
		ICE = iCE;
	}
	public static String getSHOT() {
		return SHOT;
	}
	public static void setSHOT(String sHOT) {
		SHOT = sHOT;
	}
	public static String getSIZE() {
		return SIZE;
	}
	public static void setSIZE(String sIZE) {
		SIZE = sIZE;
	}
	public static String getTakeOut() {
		return takeOut;
	}
	public static void setTakeOut(String takeOut) {
		orderVO.takeOut = takeOut;
	}
	public static int getWon() {
		return won;
	}
	public static void setWon(int won) {
		orderVO.won = won;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		orderVO.count = count;
	}
	public static int getEa() {
		return ea;
	}
	public static void setEa(int ea) {
		orderVO.ea = ea;
	}
	
	
	
}