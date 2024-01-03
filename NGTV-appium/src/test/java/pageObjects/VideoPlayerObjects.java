package pageObjects;

import baseLibrary.BaseLibrary;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class VideoPlayerObjects {
	public VideoPlayerObjects(AndroidDriver<AndroidElement> driver) {
		BaseLibrary.driver = driver;
	}

	public By VideoPlayerElement                    = By.id("exo_subtitles");
	public By VideoPlayerTitle                      = By.id("tvContentTitle");
	public By VideoPlayerRewindButton               = By.id("btnRewind");
	public By VideoPlayerForwardButton              = By.id("btnForward");
	public By VideoPlayerPlayPauseButton            = By.id("ivToggle");
	public By VideoPlayerProgressBar                = By.id("playerProgress");
	public By VideoPlayerSettingsIcon               = By.id("ivTrackSelector");
	public By VideoPlayerNonPlayingVideoOverlay     = By.id("exo_shutter");
	public By VideoPlayerWkarIcon                   = By.id("logoImg");
}
