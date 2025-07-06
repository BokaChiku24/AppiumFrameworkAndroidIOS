package org.kunalchavan.iosTest;

import org.kunalchavan.pageObjects.ios.AlertPage;
import org.kunalchavan.testUtils.IOSBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSAlert extends IOSBaseTest {

	@Test
	public void iOSBasic() {
		AlertPage alert = page.goToAlert();
		alert.textEntryAlert("Kunal Chavan");
		alert.confirmAlert();
		String alertMessage = alert.ConfirmAlertMessage();
		Assert.assertEquals(alertMessage, "A message should be a short, complete sentence.");
		alert.confirmAlertAccept();
	}

}
