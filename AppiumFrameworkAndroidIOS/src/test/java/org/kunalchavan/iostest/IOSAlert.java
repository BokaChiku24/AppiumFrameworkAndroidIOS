package org.kunalchavan.iostest;

import org.kunalchavan.pageobjectmodel.ios.AlertPage;
import org.kunalchavan.testutils.IOSBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSAlert extends IOSBaseTest {

	@Test(groups = {"Smoke"})
	public void iOSBasic() {
		AlertPage alert = page.goToAlert();
		alert.textEntryAlert("Kunal Chavan");
		alert.confirmAlert();
		String alertMessage = alert.ConfirmAlertMessage();
		Assert.assertEquals(alertMessage, "A message should be a short, complete sentence.");
		alert.confirmAlertAccept();
	}

}
