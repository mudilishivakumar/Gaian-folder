package enums;

public enum Criteria {
	//App
	Does_the_APP_install_without_issues(25), 
	Can_We_Login_without_issues(26),
	Is_User_Able_to_Logout_from_App(31), 
	Is_Video_playing_from_VOD_Screen_in_App(30),
	Is_Video_playing_from_Live_Screen_in_App(29), 
	Is_Video_playing_from_HomeScreen_in_App(28),
	Is_Video_playing_from_NextGenAppsScreen_in_App(24),
	Is_Navigation_happenning_properly_in_App(27),
	Has_Page_Loaded_Properly_On_Time(40);

	int value;

	Criteria(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
