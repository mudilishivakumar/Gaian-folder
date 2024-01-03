package enums;

public enum Criteria {
	//TV
	Does_the_TV_APK_install_without_issues(39), 
	Can_We_Login_without_issues_in_TV(38),
	Is_Navigation_happening_properly_in_TV_App(37),
	Is_Video_playing_from_HomeScreen_in_TV_App(36),
	Is_Video_playing_from_Live_Screen_in_TV_App(35), 
	Is_Video_playing_from_VOD_Screen_in_TV_App(34),
	Is_User_Able_to_Logout_from_TV_App(33);

	int value;

	Criteria(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
